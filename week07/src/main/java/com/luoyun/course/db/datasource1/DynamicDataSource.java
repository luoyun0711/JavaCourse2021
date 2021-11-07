package com.luoyun.course.db.datasource1;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * DynamicDataSource
 *
 * @author luoyun
 * @data 2021/11/7
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> datasourceContext = new ThreadLocal<>();

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    public static void switchDataSource(String datasource) {
        datasourceContext.set(datasource);
    }

    public static String getDataSource() {
        return datasourceContext.get();
    }

    public static void clear() {
        datasourceContext.remove();
    }
}
