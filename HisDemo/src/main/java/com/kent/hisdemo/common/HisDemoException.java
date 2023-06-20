package com.kent.hisdemo.common;

/**
 * 自定义异常
 */
public class HisDemoException extends RuntimeException {
    public HisDemoException() {
    }

    public HisDemoException(String message) {
        super(message);
    }

    /**
     * 抛出异常
     */
    public static void fail(String message) {
        throw new HisDemoException(message);
    }
}
