package record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainorRecord {

    private int id;
    private String username;

    public TrainorRecord(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("trainor_id");
        this.username = resultSet.getString("trainor_username");
    }

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return String.format("{ID: %d | USERNAME: %s}", id, username);
    }
}
