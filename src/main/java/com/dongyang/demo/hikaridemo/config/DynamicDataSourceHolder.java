package com.dongyang.demo.hikaridemo.config;

/**
 * Datasource Holder
 *
 * @author dongyang.hu
 * @date 2019/10/28 11:46
 */
public class DynamicDataSourceHolder {

    private static final ThreadLocal<String> TARGET_DATASOURCE = new ThreadLocal<>();

    public static void put(String datasourceName) {
        TARGET_DATASOURCE.set(datasourceName);
    }

    public static String get() {
        return TARGET_DATASOURCE.get();
    }

    public static void remove() {
        TARGET_DATASOURCE.remove();
    }
}
