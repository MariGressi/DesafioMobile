package Model;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    @SerializedName("name")
    private Name name;

    @SerializedName("location")
    private Location location;

    @SerializedName("dob")
    private DateOfBirth dateOfBirth;

    @SerializedName("login")
    private Login login;

    @SerializedName("picture")
    private Picture picture;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    public User(Name name, Location location, DateOfBirth dateOfBirth, Login login, Picture picture, String email, String phone) {
        this.name = name;
        this.location = location;
        this.dateOfBirth = dateOfBirth;
        this.login = login;
        this.picture = picture;
        this.email = email;
        this.phone = phone;
    }

    public Name getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public Login getLogin() {
        return login;
    }

    public Picture getPicture() {
        return picture;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    static User fromJsonObject(JSONObject jsonObject) {
        try {
            // Extrai os dados do JSON para criar um objeto User
            // Aqui você deve adaptar de acordo com a estrutura do JSON retornado pela API
            JSONObject nameObject = jsonObject.getJSONObject("name");
            Name name = new Name(
                    nameObject.getString("title"),
                    nameObject.getString("first"),
                    nameObject.getString("last")
            );

            JSONObject locationObject = jsonObject.getJSONObject("location");
            JSONObject streetObject = locationObject.getJSONObject("street");
            Street street = new Street(streetObject.getInt("number"), streetObject.getString("name"));

            JSONObject coordinatesObject = locationObject.getJSONObject("coordinates");
            Coordinates coordinates = new Coordinates(coordinatesObject.getString("latitude"), coordinatesObject.getString("longitude"));

            JSONObject timezoneObject = locationObject.getJSONObject("timezone");
            Timezone timezone = new Timezone(timezoneObject.getString("offset"), timezoneObject.getString("description"));

            Location location = new Location(
                    street,
                    locationObject.getString("city"),
                    locationObject.getString("state"),
                    locationObject.getString("country"),
                    locationObject.getString("postcode"),
                    coordinates,
                    timezone
            );

            JSONObject dobObject = jsonObject.getJSONObject("dob");
            DateOfBirth dateOfBirth = new DateOfBirth(dobObject.getString("date"));

            JSONObject loginObject = jsonObject.getJSONObject("login");
            Login login = new Login(loginObject.getString("uuid"), loginObject.getString("password"));

            JSONObject pictureObject = jsonObject.getJSONObject("picture");
            Picture picture = new Picture(pictureObject.getString("large"), pictureObject.getString("medium"), pictureObject.getString("thumbnail"));

            return new User(name, location, dateOfBirth, login, picture, jsonObject.getString("email"), jsonObject.getString("phone"));
        } catch (JSONException e) {
            Log.e(TAG, "fromJson: Error parsing JSON", e);
            return null;
        }
    }
}
