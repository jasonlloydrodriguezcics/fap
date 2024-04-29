package record;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginRecord {

    private final String user;
    private String pass;
    private final String role;
    private boolean isEncrypted;

    public LoginRecord(String user, String pass, String role, boolean isEncrypted) {
        this.user = user;
        this.pass = pass;
        this.role = role;
        this.isEncrypted = isEncrypted;
    }

    public LoginRecord(ResultSet resultSet) throws SQLException {
        this.user = resultSet.getString("USERNAME");
        this.pass = resultSet.getString("PASSWORD");
        this.role = resultSet.getString("ROLE");
        this.isEncrypted = resultSet.getBoolean("IS_ENCRYPTED");
    }
    
    public String getUser() {
        return this.user;
    }

    public String getPass() {
        return this.pass;
    }

    public String getRole() {
        return this.role;
    }

    public boolean getEncrypted() {
        return this.isEncrypted;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEncrypted(boolean isEncrypted) {
        this.isEncrypted = isEncrypted;
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

        return this.user.equals(otherRecord.getUser());
    }

    @Override
    public int hashCode() {
        return 73 * 5 + Objects.hashCode(this.user);
    }
}
