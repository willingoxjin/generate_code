package org.goldlike.generate_code.utils;

import org.goldlike.generate_code.model.db.DB;
import org.goldlike.generate_code.model.db.Table;

import java.sql.*;
import java.util.*;

/**
 * @author Jin.Nie
 * @since 2020-12-25
 */
public class DBUtils {

    public static Connection connection;

    private static final String driverClassName = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        return connection;
    }

    /**
     * 初始化连接
     *
     * @param db
     * @return
     */
    public static Connection initDB(DB db) throws Exception {
        if (connection == null) {
            Properties props = new Properties();
            props.put("user", db.getUsername());
            props.put("password", db.getPassword());
            // 配置可获取到表备注信息 remarks useInformationSchema
            props.put("remarks", "true");
            props.put("useInformationSchema", "true");
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(db.getUrl(), props);
            return connection;
        }
        return null;
    }

    /**
     * 断开连接
     */
    public static void disConnect() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 获取表名
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    public static List<String> getDBTableNames(Connection conn) throws SQLException {
        if (conn == null) {
            conn = connection;
        }
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet tables = metaData.getTables(conn.getCatalog(), null, null, null);
        List<String> tableNames = new ArrayList<>();
        while (tables.next()) {
            tableNames.add(tables.getString("TABLE_NAME"));
        }
        tables.close();
        return tableNames;
    }

    /**
     * 获取所有表信息
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    public static List<Table> getDBTables(Connection conn) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet tables = metaData.getTables(conn.getCatalog(), null, null, null);
        // 表处理
        List<Table> tableList = new ArrayList<>();
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            String tableRemark = tables.getString("REMARKS");
            ResultSet columns = metaData.getColumns(conn.getCatalog(), null, tableName, null);
            ResultSet primaryKeys = metaData.getPrimaryKeys(conn.getCatalog(), null, tableName);
            String primaryName = "";
            while (primaryKeys.next()) {
                primaryName = primaryKeys.getString("COLUMN_NAME");
            }
            Table tableClass = new Table();
            tableClass.setTableName(tableName);
            tableClass.setTableRemark(tableRemark);
            // 列处理
            Set<Table.Column> columnSet = new LinkedHashSet<>();
            while (columns.next()) {
                String column_name = columns.getString("COLUMN_NAME");
                String type_name = columns.getString("TYPE_NAME");
                String column_remarks = columns.getString("REMARKS");

                Table.Column columnClass = new Table.Column();
                columnClass.setColumnName(column_name);
                columnClass.setColumnType(type_name);
                columnClass.setColumnRemark(column_remarks);
                if (primaryName.equals(column_name)) {
                    columnClass.setPrimary(true);
                }

                columnSet.add(columnClass);
            }

            columns.close();
            tableClass.setColumns(columnSet);
            tableList.add(tableClass);
        }
        tables.close();
        return tableList;
    }




}
