package com.kent.hisdemo.utils;

import com.kent.hisdemo.common.exception.HisDemoException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 断言工具
 */
public class AssertUtil {

    /**
     * 条件断言
     *
     * @param condition
     * @param msg
     */
    public static void isTrue(boolean condition, String msg) {
        if (condition) {
            throw new HisDemoException(msg);
        }
    }

    /**
     * 非空断言
     *
     * @param o
     * @param msg
     */
    public static void notNull(Object o, String msg) {
        if (o == null) {
            throw new HisDemoException(msg);
        }
    }

    /**
     * 非空非0断言
     *
     * @param n
     * @param msg
     */
    public static void notNullOrZero(Integer n, String msg) {
        if (n == null || n == 0) {
            throw new HisDemoException(msg);
        }
    }

    /**
     * 非空非0断言
     *
     * @param n
     * @param msg
     */
    public static void notNullOrZero(BigDecimal n, String msg) {
        if (n == null || n.compareTo(BigDecimal.ZERO) == 0) {
            throw new HisDemoException(msg);
        }
    }

    /**
     * 非空非0断言
     *
     * @param n
     * @param msg
     */
    public static void notNullOrZero(Long n, String msg) {
        if (n == null || n == 0L) {
            throw new HisDemoException(msg);
        }
    }

    /**
     * 集合非空断言
     *
     * @param collection
     * @param msg
     */
    public static void notEmpty(Collection collection, String msg) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new HisDemoException(msg);
        }
    }

    /**
     * 字符串非空断言
     *
     * @param str
     * @param msg
     */
    public static void notEmpty(String str, String msg) {
        if (StringUtils.isEmpty(str)) {
            throw new HisDemoException(msg);
        }
    }

    /**
     * 逗号分割的id字符串 每项都是整数断言,
     *
     * @param Ids 逗号分割的Id
     * @param msg
     * @return
     */
    public static void notFormatIds(String Ids, String msg) {
        if (StringUtils.isEmpty(Ids)) {
            throw new HisDemoException(msg);
        }
        String POSITIVE_INT_NUM = "^[1-9]\\d*$";//正整数
        for (String Id : Ids.split(",")) {
            if (!Id.matches(POSITIVE_INT_NUM)) {
                throw new HisDemoException(msg);
            }
        }
    }

    /**
     * 前端传参,防sql注入
     * 逗号分割的id字符串 只有数字和逗号断言,
     *
     * @param Ids 逗号分割的Id
     * @param msg
     * @return
     */
    public static void notFormatFrontIds(String Ids, String msg) {
        //只有数字和逗号
        String PATTERN = "^[,0-9]+$";
        if (!Ids.matches(PATTERN)) {
            throw new HisDemoException(msg);
        }
    }

    /**
     * 排序断言
     *
     * @param sort
     */
    public static void sortCheck(Integer sort) {
        if (sort == null) {
            throw new HisDemoException("排序不能为空,请重试!");
        }
        if (sort < 0 || sort > 255) {
            throw new HisDemoException("排序值需在0～255内，请重试！");
        }
    }

    /**
     * 邮箱断言
     *
     * @param email
     * @return
     */
    public static void emailCheck(String email) {
        String check = "^([a-zA-Z0-9]+[-_.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[-_.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,6}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        if (!matcher.matches()) {
            throw new HisDemoException("请输入正确的邮箱");
        }
    }

    /**
     * 手机号断言
     *
     * @param mobile
     * @return
     */
    public static void mobileCheck(String mobile) {
        String check = "^1[3-9]\\d{9}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(mobile);
        if (!matcher.matches()) {
            throw new HisDemoException("请输入正确的手机号");
        }
    }

    /**
     * 身份证号断言
     *
     * @param IDNumber
     * @return
     */
    public static void IDNumberCheck(String IDNumber) {
        //18位 正则表达式
        String id_18 = "^[1-9][0-9]{5}(18|19|20)[0-9]{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)[0-9]{3}([0-9]|(X|x))";
        //15位 正则表达式
        String id_15 = "^[1-9][0-9]{5}[0-9]{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)[0-9]{2}[0-9]";
        //或者关系校验
        String check = "(" + id_18 + ")" + "|" + "(" + id_15 + ")";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(IDNumber);
        if (!matcher.matches()) {
            throw new HisDemoException("请输入正确的身份证号");
        }
    }

    /**
     * 数字断言
     *
     * @param number
     * @return
     */
    public static void numberCheck(String number) {
        //验证必须为数字（整数或小数）
        String check = "[0-9]+(.[0-9]+)?";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(number);
        if (!matcher.matches()) {
            throw new HisDemoException("请输入正确的数字");
        }
    }
}
