package com.glassPages.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.glassPages.Constants.FilePaths.DB_PROPERTIES_PATH;


/**
 * Utility which contains the required methods to get the instance of DB and to set up connection to to SQL Database
 * Methods - getInstance() , setUpSQLDBConnection(),
 * @author - Vanshika Chauhan
 * @version 1.0
 */
public class SQLDatabaseUtility extends DatabaseUtility {

    private static SQLDatabaseUtility sqlDatabaseUtility;

    private String connectionURL;
    private String driverClass;


    private SQLDatabaseUtility() {
        connectionURL = CommonUtility.readPropertyFile(DB_PROPERTIES_PATH).getProperty("sqlAUXIDbConnectionURL");
        connectionURL = CommonUtility.readPropertyFile(DB_PROPERTIES_PATH).getProperty("sqlPLUTUSHubDbConnectionURL");
        driverClass = CommonUtility.readPropertyFile(DB_PROPERTIES_PATH).getProperty("sqlDriverClass");
    }


    /**
     * getInstance() - This function creates the instance of SQLDatabaseUtility
     * @return sqlDatabaseUtility
     * @author - Vanshika Chauhan
     * @version - 1.0
     */
    public static SQLDatabaseUtility getInstance() {
        if (sqlDatabaseUtility == null)
            sqlDatabaseUtility = new SQLDatabaseUtility();
        return sqlDatabaseUtility;
    }

    /**
     * setUpSQLDBConnection() - This function creates the set up to connect to the SQL DataBase.
     * @author - Vanshika Chauhan
     * @version - 1.0
     */
    public void setUpSQLDBConnection() {

        try {
            Connection connection = DriverManager.getConnection(connectionURL);
            setUpDBConnection(driverClass,connectionURL);
            System.out.println("SQL DB Connection established");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}