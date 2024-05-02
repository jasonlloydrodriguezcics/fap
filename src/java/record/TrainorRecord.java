package record;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainorRecord {
    private String training;
    private String username;

    public TrainorRecord(String username, String training) {
        this.username = username;
        this.training = training;
    }
    
    public TrainorRecord(ResultSet resultSet) throws SQLException {
        this.username = resultSet.getString("username");
        this.training = resultSet.getString("training");
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "{"
                + "\"training\":\"" + getTraining() + "\","
                + "\"trainor\":\"" + username + "\","
                + "}";
    }

}
