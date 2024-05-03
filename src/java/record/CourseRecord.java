package record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRecord {

    private int id;
    private String name;
    private String description;
    private int trainingId;
    private String trainingName;

    public CourseRecord(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("course_id");
        this.name = resultSet.getString("course_name");
        this.description = resultSet.getString("course_description");
        this.trainingId = resultSet.getInt("training_id");
        this.trainingName = resultSet.getString("training_name");
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getTrainingId() {
        return this.trainingId;
    }

    public String getTrainingName() {
        return this.trainingName;
    }

    @Override
    public String toString() {
        return String.format("{ID: %d | NAME: %s | DESCRIPTION: %s | TRAINING: %s}", id, name, description, trainingName);
    }
}
