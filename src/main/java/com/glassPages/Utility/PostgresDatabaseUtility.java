package com.glassPages.Utility;

import com.glassPages.Constants.FilePaths;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.glassPages.Constants.FilePaths.DB_PROPERTIES_PATH;

/**
 * This Utility enables us to setup the connection with PostgresDatabase.
 */
public class PostgresDatabaseUtility extends DatabaseUtility {
    private static PostgresDatabaseUtility postgresDatabaseUtility;
    private String connectionURL;
    private String driverClass;


    private PostgresDatabaseUtility() {
        connectionURL = CommonUtility.readPropertyFile(DB_PROPERTIES_PATH).getProperty("postgresAlpDBConnectionURL");
        driverClass = CommonUtility.readPropertyFile(DB_PROPERTIES_PATH).getProperty("sqlDriverClass");
    }
    /**
     * getInstance() - This function creates the instance of PostgresDB
     * @return PostgresDatabaseUtility
     * @author - Vanshika Chauhan
     * @version - 1.0
     */
    public static PostgresDatabaseUtility getInstance() {
        if (postgresDatabaseUtility == null)
            postgresDatabaseUtility = new PostgresDatabaseUtility();
        return postgresDatabaseUtility;
    }

    /**
     * setUpPostgresDBConnection() - This function creates the setup to connect to the Postgres DataBase.
     * @author - Vanshika Chauhan
     * @version - 1.0
     */
    public void setUpPostgresDBConnection() {

        try {
            Connection connection = DriverManager.getConnection(connectionURL, CommonUtility.readPropertyFile(FilePaths.DB_PROPERTIES_PATH).getProperty("pgUserName"), CommonUtility.readPropertyFile(FilePaths.DB_PROPERTIES_PATH).getProperty("pgPassword"));
            setUpDBConnection(driverClass, connectionURL);
            System.out.println("Postgres DB Connection established");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}