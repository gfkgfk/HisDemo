package com.kent.hisdemo.common.exception;

/**
 * 自定义异常
 */
public class HisDemoException2 extends RuntimeException {
    public HisDemoException2() {
    }

    public HisDemoException2(String message) {
        super(message);
    }

    /**
     * 抛出异常
     */
    public static void fail(String message) {
        throw new HisDemoException2(message);
    }
}
