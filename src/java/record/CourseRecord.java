package record;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRecord {
    private String training;
    private String trainor;
    private String description;
    private String course;

    public CourseRecord(String course, String description, String trainor, String training) {
        this.course = course;
        this.description = description;
        this.trainor = trainor;
        this.training = training;
    }
    
    public CourseRecord(ResultSet resultSet) throws SQLException {
        this.course = resultSet.getString("course");
        this.description = resultSet.getString("description");
        this.trainor = resultSet.getString("trainor");
        this.training = resultSet.getString("training");
    }

    public String getCourse() {
        return course;
    }

    public String getTraining() {
        return training;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setTraining(String training) {
        this.training = training;
    }
    
    public String getTrainor() {
        return trainor;
    }

    public void setTrainor(String trainor) {
        this.trainor = trainor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{"
                + "\"training\":\"" + training + "\","
                + "\"course\":\"" + course + "\","
                + "\"description\":\"" + description + "\","
                + "\"trainor\":\"" + trainor + "\","
                + "}";
    }

}
