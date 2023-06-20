package com.kent.hisdemo;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


//#mvn clean compile -q dependency:resolve -DoutputFile="文件存放目录地址\文件名字" -DappendOutput=true
//以下命令:用mvn输出依赖树并输出文件到D:\tree.dot   (如果只在MVN中查看可用mvn dependency:tree)
//mvn clean compile -q dependency:resolve -DoutputFile="D:\tree.dot" -DappendOutput=true
//这里简单提供一个解析上面txt的文件并转成excel的代码
public class ExcelUtil {

    public static void main(String[] args) {
        // 前面获取的tree文件
        File file = new File("D:\\tree.dot");
        // 读取文件
        List<String> results = readTxt(file);
        Collections.sort(results);
        List<String[]> rowList = results.stream().sorted().map(result -> result.split(":")).collect(Collectors.toList());
        String[] headerArray = new String[]{"groupId", "artifact_id", "type", "version", "scope"};
        rowList.add(0, headerArray);
        // 将内容写到指定的文件路径
        writeToExcel(rowList,"D:\\tree.xls");
    }

    public static List<String> readTxt(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file));) {
            //读文件
            List<String> dataList = new ArrayList<>();
            String str;
            while ((str = reader.readLine()) != null) {
                String[] results = str.split(":", 5);
                if (results.length != 5) {
                    continue;
                }
                dataList.add(str);
            }
            return dataList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static void writeToExcel(List<String[]> rowList,String excelPath) {
        //1、创建Workbook
        try (Workbook workbook = new HSSFWorkbook();
             FileOutputStream outputStream = new FileOutputStream(excelPath);) {
            Sheet sheet = workbook.createSheet();
            // 行号
            int lineNum = 0;
            for (String[] cells : rowList) {
                Row row = sheet.createRow(lineNum);
                int columnNum = 2;
                for (String cell : cells) {
                    row.createCell(columnNum++).setCellValue(cell.trim());
                }
                lineNum++;
            }
            //向文件中写入数据，将文件持久化到磁盘
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}