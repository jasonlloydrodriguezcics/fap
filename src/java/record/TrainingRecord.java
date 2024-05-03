package record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainingRecord {

    private int id;
    private String name;
    private int trainorId;
    private String trainorName;

    public TrainingRecord(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("training_id");
        this.name = resultSet.getString("training_name");
        this.trainorId = resultSet.getInt("trainor_id");
        this.trainorName = resultSet.getString("trainor_username");
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getMaxProgress() {
        return this.name;
    }

    public int getTrainorId() {
        return this.trainorId;
    }

    public String getTrainorName() {
        return this.trainorName;
    }

    @Override
    public String toString() {
        return String.format("{ID: %d | NAME: %s | TRAINOR: %s}", id, name, trainorName);
    }
}
