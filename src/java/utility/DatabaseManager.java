package utility;

import app.LoginServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import record.LoginRecord;
import record.StudentRecord;
import record.CourseRecord;
import record.TrainingRecord;

public class DatabaseManager {

    private static final String GET_ALL_LOGIN_RECORDS = "SELECT * FROM USER_INFO";
    private static final String GET_LOGIN_RECORD = "SELECT * FROM USER_INFO WHERE USERNAME = ?";
    private static final String ENCRYPT_LOGIN_PASSWORD = "UPDATE USER_INFO SET PASSWORD = ?, IS_ENCRYPTED = TRUE WHERE USERNAME = ?";
    private static final String GET_ALL_STUDENT_RECORDS = "SELECT * FROM StudentDetails JOIN TrainingDetails ON StudentDetails.training_id = TrainingDetails.training_id JOIN TrainorDetails ON TrainingDetails.trainor_id = TrainorDetails.trainor_id";
    private static final String GET_STUDENT_RECORD = "SELECT * FROM StudentDetails JOIN TrainingDetails ON StudentDetails.training_id = TrainingDetails.training_id JOIN TrainorDetails ON TrainingDetails.trainor_id = TrainorDetails.trainor_id WHERE StudentDetails.student_username = ?";
    private static final String GET_ALL_TRAINING_RECORDS = "SELECT * FROM TrainingDetails JOIN TrainorDetails ON TrainingDetails.trainor_id = TrainorDetails.trainor_id";
    private static final String GET_TRAINING_RECORD = "SELECT * FROM TrainingDetails JOIN TrainorDetails ON TrainingDetails.trainor_id = TrainorDetails.trainor_id WHERE training_id = ?";
    private static final String GET_ALL_COURSE_RECORDS = "SELECT * FROM CourseDetails JOIN TrainingDetails ON CourseDetails.training_id = TrainingDetails.training_id WHERE CourseDetails.training_id = ?";
    private static final String GET_COURSE_RECORD = "SELECT * FROM CourseDetails JOIN TrainingDetails ON CourseDetails.training_id = TrainingDetails.training_id WHERE CourseDetails.course_id = ?";

    private static DatabaseManager instance;
    private Connection derbyConnection;
    private Connection mysqlConnection;

    private DatabaseManager(ServletContext context) {
        try {
            // Connect to Derby Database
            Class.forName(context.getInitParameter("derbyJdbcClassName"));
            String derbyUsername = context.getInitParameter("derbyDbUsername");
            String derbyPassword = context.getInitParameter("derbyDbPassword");
            String derbyUrl = context.getInitParameter("derbyJdbcDriverUrl");
            this.derbyConnection = DriverManager.getConnection(derbyUrl, derbyUsername, derbyPassword);

            System.out.println("Derby connection established...");

            // Connect to MySQL Database
            Class.forName(context.getInitParameter("mysqlJdbcClassName"));
            String mysqlUsername = context.getInitParameter("mysqlDbUsername");
            String mysqlPassword = context.getInitParameter("mysqlDbPassword");
            String mysqlUrl = context.getInitParameter("mysqlJdbcDriverUrl") + "?useSSL=false&allowPublicKeyRetrieval=true";
            this.mysqlConnection = DriverManager.getConnection(mysqlUrl, mysqlUsername, mysqlPassword);

            System.out.println("MySQL connection established...");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static DatabaseManager getInstance(ServletContext context) {
        return (instance != null) ? instance : (instance = new DatabaseManager(context));
    }

    private Connection getDerbyConnection() {
        return derbyConnection;
    }

    private Connection getMysqlConnection() {
        return mysqlConnection;
    }

    public static void encryptAllLoginPasswords(ServletContext context) {
        ArrayList<LoginRecord> records = DatabaseManager.getAllLoginRecords(context);

        try {
            DatabaseManager manager = DatabaseManager.getInstance(context);
            Connection connection = manager.getDerbyConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ENCRYPT_LOGIN_PASSWORD);

            for (LoginRecord record : records) {
                if (record.getEncrypted()) {
                    continue;
                }

                preparedStatement.setString(1, Cryptographer.encrypt(context, record.getPassword()));
                preparedStatement.setString(2, record.getUsername());
                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<LoginRecord> getAllLoginRecords(ServletContext context) {
        ArrayList<LoginRecord> records = new ArrayList<>();

        try {
            DatabaseManager manager = DatabaseManager.getInstance(context);
            Connection connection = manager.getDerbyConnection();
            Statement statement = connection.createStatement();
            statement.execute(GET_ALL_LOGIN_RECORDS);

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                records.add(new LoginRecord(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return records;
    }

    public static LoginRecord getLoginRecord(ServletContext context, String key) {
        LoginRecord record = null;

        try {
            DatabaseManager manager = DatabaseManager.getInstance(context);
            Connection connection = manager.getDerbyConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_LOGIN_RECORD, 1004, 1007);
            preparedStatement.setString(1, key);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                record = new LoginRecord(resultSet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return record;
    }

    public static ArrayList<StudentRecord> getAllStudentRecords(ServletContext context) {
        ArrayList<StudentRecord> records = new ArrayList<>();

        try {
            DatabaseManager manager = DatabaseManager.getInstance(context);
            Connection connection = manager.getMysqlConnection();
            Statement statement = connection.createStatement();
            statement.execute(GET_ALL_STUDENT_RECORDS);

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                records.add(new StudentRecord(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return records;
    }

    public static StudentRecord getStudentRecord(ServletContext context, String key) {
        StudentRecord record = null;

        try {
            DatabaseManager manager = DatabaseManager.getInstance(context);
            Connection connection = manager.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_RECORD, 1004, 1007);
            preparedStatement.setString(1, key);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                record = new StudentRecord(resultSet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return record;
    }

    public static ArrayList<TrainingRecord> getTrainingRecords(ServletContext context) {
        ArrayList<TrainingRecord> records = new ArrayList<>();

        try {
            DatabaseManager manager = DatabaseManager.getInstance(context);
            Connection connection = manager.getMysqlConnection();
            Statement statement = connection.createStatement();
            statement.execute(GET_ALL_TRAINING_RECORDS);

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                records.add(new TrainingRecord(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return records;
    }

    public static TrainingRecord getTrainingRecord(ServletContext context, String key) {
        TrainingRecord record = null;

        try {
            DatabaseManager manager = DatabaseManager.getInstance(context);
            Connection connection = manager.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_TRAINING_RECORD, 1004, 1007);
            preparedStatement.setString(1, key);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                record = new TrainingRecord(resultSet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return record;
    }

    public static ArrayList<CourseRecord> getCourseRecords(ServletContext context, String key) {
        ArrayList<CourseRecord> records = new ArrayList<>();

        try {
            DatabaseManager manager = DatabaseManager.getInstance(context);
            Connection connection = manager.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_COURSE_RECORDS, 1004, 1007);
            preparedStatement.setString(1, key);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                records.add(new CourseRecord(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return records;
    }

    public static ArrayList<TrainingRecord> getCourseRecord(ServletContext context, String key) {
        ArrayList<TrainingRecord> courseList = new ArrayList<>();

        try {
            DatabaseManager manager = DatabaseManager.getInstance(context);
            Connection connection = manager.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_COURSE_RECORD, 1004, 1007);
            preparedStatement.setString(1, key);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courseList.add(new TrainingRecord(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courseList;
    }
}
