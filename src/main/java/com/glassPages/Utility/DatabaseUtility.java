
package com.glassPages.Utility;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

public abstract class DatabaseUtility {
    private Connection con;
    private Statement stmt;
    private ResultSet resultSet;
    private String query;
    private int affectedRowCount;


    public Connection setUpDBConnection(String driverClass, String connectionURL) {
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(connectionURL);

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return con;
    }

    /**
     * This functions concatenate the DBEnums with the value provided by user in map and add it in the SQL query
     *
     * @param query
     * @param values
     * @return
     */

    private static String fetchQuery(String query, Map<Object, Object> values) {

        for (Object key : values.keySet()) {

            query = query.replaceAll("\\b" + key.toString() + "\\b", values.get(key).toString()).replace("$", "");
        }
        System.out.println(query);
        return query;
    }

    /**
     * Use this function to run a dynamic sql query. This query calls the fetchQuery() method internally to form the sql query based on the values provided by user in the database's enums.
     * After forming a complete sql query it executes the query.
     *
     * @param sqlQuery
     * @param values
     * @return
     */
    public ResultSet executeSelectQuery(String sqlQuery, Map<Object, Object> values) {
        query = fetchQuery(sqlQuery, values);

        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * Use this function to execute a static sql query.
     *
     * @param sqlQuery
     * @return
     */
    public ResultSet executeSelectQuery(String sqlQuery) {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(sqlQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * Use this function to execute dynamic sql query. Map store the DBEnums and values
     *
     * @param sqlQuery
     * @param values
     * @return
     */
    public int executeDDLQuery(String sqlQuery, Map<Object, Object> values) {
        query = fetchQuery(sqlQuery, values);
        try {
            stmt = con.createStatement();
            affectedRowCount = stmt.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return affectedRowCount;
    }

    public int executeDDLQuery(String sqlQuery) {
        try {
            stmt = con.createStatement();
            affectedRowCount = stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return affectedRowCount;
    }

    @SneakyThrows
    public String fetchValueFromDbCol(String columnName,Integer  ... rowNumber) {
        int index = 1;
        if(rowNumber.length!=0)
            index = rowNumber[0];
        if(resultSet.isBeforeFirst()){
            while (index>0){
                resultSet.next();
                index--;
            }
        }
        return resultSet.getString(columnName);
    }

    @SneakyThrows
    public String fetchValuesFromDbCol(ResultSet resultSet,String columnName, Integer  ... rowNumber) {
        int index =1;
        if(rowNumber.length!=0)
            index = rowNumber[0];
        if(resultSet.isBeforeFirst()){
            while (index>0){
                resultSet.next();
                index--;
            }
        }
        return resultSet.getString(columnName);
    }

    @SneakyThrows
    public String fetchValuesFromDbColumn(ResultSet resultSet,String columnName, Integer  ... rowNumber) {
        int index =1;
        if(rowNumber.length!=0)
            index = rowNumber[0];
        if(resultSet.isBeforeFirst()){
            while (index>0){
                resultSet.next();
                index--;
            }
        }
        return resultSet.getString(columnName);
    }

    @SneakyThrows
    public Boolean isRowPresent(ResultSet resultSet) {
        ResultSet dummyResultset = resultSet;
        return dummyResultset.next();
    }
}

