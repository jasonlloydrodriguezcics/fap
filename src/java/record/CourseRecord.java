package record;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRecord {
    private String training;
    private String course;

    public CourseRecord(String course, String training) {
        this.course = course;
        this.training = training;
    }
    
    public CourseRecord(ResultSet resultSet) throws SQLException {
        this.course = resultSet.getString("course");
        this.training = resultSet.getString("training");
    }

    public String getCourse() {
        return course;
    }

    public String getTraining() {
        return training;
    }

    public void setUsername(String course) {
        this.course = course;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    @Override
    public String toString() {
        return "{"
                + "\"training\":\"" + training + "\","
                + "\"course\":\"" + course + "\","
                + "}";
    }
}
