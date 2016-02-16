package com.shivandev;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Modified by ShIvan on 14.02.2016.
 * Created by duxing on 2015.07.26 23:38.
 */
public class SqlInfo {
    private String tableName;
    private String primaryKey;
    private List<Column> columnList;
    private List<Column> optionsColumnList;

    public SqlInfo() {
    }

    public SqlInfo(String tableName, String primaryKey, List<Column> columnList, List<Column> optionsColumnList) {
        this.tableName = tableName;
        this.primaryKey = primaryKey;
        this.columnList = columnList;
        this.optionsColumnList = optionsColumnList;
    }

    public String getTableName() {
        return tableName;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public List<Column> getOptionsColumnList() {
        return optionsColumnList;
    }

    public boolean isValid() {
        return this.columnList!=null && this.columnList.size()>0 && StringUtils.isNotBlank(tableName);
    }
}
