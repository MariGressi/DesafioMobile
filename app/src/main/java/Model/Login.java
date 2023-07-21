package Model;

import com.google.gson.annotations.SerializedName;

public class Login {
    private String uuid;
    private String password;

    public Login(String uuid, String password) {
        this.uuid = uuid;
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public String getPassword() {
        return password;
    }
}
