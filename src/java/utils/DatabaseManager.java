package utils;

import java.sql.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

public class DatabaseManager {
    private static DatabaseManager instance;
    private Connection derbyConnection;
    private Connection mysqlConnection;

    private DatabaseManager(ServletContext context) {
        try {
            // Connect to Derby Database
            Class.forName(context.getInitParameter("derbyJdbcClassName"));
            String derbyUsername = context.getInitParameter("derbyDbUserName");
            String derbyPassword = context.getInitParameter("derbyDbPassword");
            String derbyUrl = context.getInitParameter("derbyJdbcDriverURL") +
                    "://" +
                    context.getInitParameter("derbyDbHostName") +
                    ":" +
                    context.getInitParameter("derbyDbPort") +
                    "/" +
                    context.getInitParameter("derbyDatabaseName");
            derbyConnection = DriverManager.getConnection(derbyUrl, derbyUsername, derbyPassword);

            System.out.println("Derby connection established...");

            // Connect to MySQL Database
            Class.forName(context.getInitParameter("mysqlJdbcClassName"));
            String mysqlUsername = context.getInitParameter("mysqlDbUserName");
            String mysqlPassword = context.getInitParameter("mysqlDbPassword");
            String mysqlUrl = "jdbc:mysql://" +
                    context.getInitParameter("mysqlDbHostName") +
                    ":" +
                    context.getInitParameter("mysqlDbPort") +
                    "/" +
                    context.getInitParameter("mysqlDatabaseName") +
                    "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            mysqlConnection = DriverManager.getConnection(mysqlUrl, mysqlUsername, mysqlPassword);

            System.out.println("MySQL connection established...");
        } catch (SQLException sqle) {
            System.out.println("SQLException error occurred - " + sqle.getMessage());
        } catch (ClassNotFoundException nfe) {
            System.out.println("ClassNotFoundException error occurred - " + nfe.getMessage());
        }
    }

    public static DatabaseManager getInstance(ServletContext context) {
        if (instance == null) {
            instance = new DatabaseManager(context);
        }
        return instance;
    }

    public Connection getDerbyConnection() {
        return derbyConnection;
    }

    public Connection getMysqlConnection() {
        return mysqlConnection;
    }
}
