package com.kent.hisdemo.utils;

public class TokenGenerator {

    /**
     * 获取token值
     *
     * @param timeStr
     * @param userId
     * @return
     */
    public static String getNewToken(String timeStr, Long userId) {
        String src = timeStr + userId + NumberUtil.genRandomNum(4);
        return SystemUtil.genToken(src);
    }
}
