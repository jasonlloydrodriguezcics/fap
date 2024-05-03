package record;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginRecord {

    private final String username;
    private String password;
    private final String role;
    private boolean isEncrypted;

    public LoginRecord(ResultSet resultSet) throws SQLException {
        this.username = resultSet.getString("USERNAME");
        this.password = resultSet.getString("PASSWORD");
        this.role = resultSet.getString("ROLE");
        this.isEncrypted = resultSet.getBoolean("IS_ENCRYPTED");
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return this.role;
    }

    public boolean getEncrypted() {
        return this.isEncrypted;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof LoginRecord)) {
            return false;
        }

        LoginRecord otherRecord = (LoginRecord) other;

        if (this == otherRecord) {
            return true;
        }

        return this.username.equals(otherRecord.getUsername());
    }

    @Override
    public int hashCode() {
        return 73 * 5 + Objects.hashCode(this.username);
    }
}
