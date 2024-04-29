package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import record.LoginRecord;

public class Connector {

    private static final String GET_ALL_RECORDS = "SELECT * FROM USER_INFO";
    private static final String GET_RECORD = "SELECT * FROM USER_INFO WHERE USERNAME = ?";

    private static Connection getConnection(ServletContext context) {
        Connection connection = null;

        try {
            Class.forName(context.getInitParameter("driver"));
            connection = DriverManager.getConnection(context.getInitParameter("url"), context.getInitParameter("user"), context.getInitParameter("pass"));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }

        return connection;
    }

    public static ArrayList<LoginRecord> getAllLoginRecords(ServletContext context) {
        ArrayList<LoginRecord> records = new ArrayList<>();

        try (Connection connection = getConnection(context); Statement statement = connection.createStatement()) {
            statement.execute(GET_ALL_RECORDS);

            try (ResultSet resultSet = statement.getResultSet()) {
                while (resultSet.next()) {
                    records.add(new LoginRecord(resultSet));
                }
            }
        } catch (SQLException exception) {
            System.err.println(Arrays.toString(exception.getStackTrace()));
        }

        return records;
    }

    public static LoginRecord getLoginRecord(ServletContext context, String key) {
        LoginRecord record = null;

        try (Connection connection = getConnection(context); PreparedStatement preparedStatement = connection.prepareStatement(GET_RECORD, 1004, 1007)) {
            preparedStatement.setString(1, key);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    record = new LoginRecord(resultSet);
                }
            }
        } catch (SQLException exception) {
            System.err.println(Arrays.toString(exception.getStackTrace()));
        }

        return record;
    }
}
