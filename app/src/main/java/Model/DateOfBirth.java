package Model;

import com.google.gson.annotations.SerializedName;

public class DateOfBirth {
    private String date;

    public DateOfBirth(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
