package org.goldlike.generate_code.model.db;

import java.util.Set;

/**
 * 表对象
 *
 * @author Jin.Nie
 * @since 2020-12-29
 */
public class Table {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表备注
     */
    private String tableRemark;

    /**
     * 列
     */
    private Set<Column> columns;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableRemark() {
        return tableRemark;
    }

    public void setTableRemark(String tableRemark) {
        this.tableRemark = tableRemark;
    }

    public Set<Column> getColumns() {
        return columns;
    }

    public void setColumns(Set<Column> columns) {
        this.columns = columns;
    }

    /**
     * 列
     */
    public static class Column {

        /**
         * 列名
         */
        public String columnName;

        /**
         * 类型
         */
        public String columnType;

        /**
         * 备注
         */
        public String columnRemark;

        /**
         * 是否为主键
         */
        public boolean primary;

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getColumnType() {
            return columnType;
        }

        public void setColumnType(String columnType) {
            this.columnType = columnType;
        }

        public String getColumnRemark() {
            return columnRemark;
        }

        public void setColumnRemark(String columnRemark) {
            this.columnRemark = columnRemark;
        }

        public boolean isPrimary() {
            return primary;
        }

        public void setPrimary(boolean primary) {
            this.primary = primary;
        }
    }

}
