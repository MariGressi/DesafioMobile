package Model;

import com.google.gson.annotations.SerializedName;

public class Name {
    private String title;
    private String first;
    private String last;

    public Name(String title, String first, String last) {
        this.title = title;
        this.first = first;
        this.last = last;
    }

    public String getTitle() {
        return title;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getFullName() {
        return title + " " + first + " " + last;
    }
}