package record;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainingRecord {
    private String training;
    private String course;

    public TrainingRecord(String course, String training) {
        this.course = course;
        this.training = training;
    }
    
    public TrainingRecord(ResultSet resultSet) throws SQLException {
        this.course = resultSet.getString("course");
        this.training = resultSet.getString("training");
    }
    
       public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public String getcourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "{"
                + "\"training\":\"" + getTraining() + "\","
                + "\"course\":\"" + course + "\","
                + "}";
    }
}
