package record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRecord {
    private String username;
    private String training;
    private int progressTracker;

    public StudentRecord(String username, String training, int progressTracker) {
        this.username = username;
        this.training = training;
        this.progressTracker =progressTracker;
    }
    
    public StudentRecord(ResultSet resultSet) throws SQLException {
        this.username = resultSet.getString("username");
        this.training = resultSet.getString("training");
        this.progressTracker = resultSet.getInt("progressTracker");
    }

    public String getUsername() {
        return username;
    }

    public String getTraining() {
        return training;
    }

    public int getProgressTracker() {
        return progressTracker;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public void setProgressTracker(int progressTracker) {
        this.progressTracker = progressTracker;
    }
    

    @Override
    public String toString() {
        return "{"
                + "\"username\":\"" + username + "\","
                + "\"training\":\"" + training + "\","
                + "\"progressTracker\":\"" + progressTracker + "\""
                + "}";
    }
}
