package record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRecord {

    private int id;
    private String username;
    private int progress;
    private String startDate;
    private String endDate;
    private int trainingId;
    private String trainingName;

    public StudentRecord(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("student_id");
        this.username = resultSet.getString("student_username");
        this.progress = resultSet.getInt("progress");
        this.startDate = resultSet.getString("start_date");
        this.endDate = resultSet.getString("end_date");
        this.trainingId = resultSet.getInt("training_id");
        this.trainingName = resultSet.getString("training_name");
    }

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public int getProgress() {
        return this.progress;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public int getTrainingId() {
        return this.trainingId;
    }

    public String getTrainingName() {
        return this.trainingName;
    }

    @Override
    public String toString() {
        return String.format("{ID: %d | USERNAME: %s | PROGRESS: %d | START-DATE: %s | END-DATE: %s | TRAINING: %s}", id, username, progress, startDate, endDate, trainingName);
    }

}
