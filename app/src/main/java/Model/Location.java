package Model;

public class Location {
    private Street street;
    private String city;
    private String state;
    private String country; // Adicionamos o atributo country
    private String postcode;
    private Coordinates coordinates;
    private Timezone timezone;

    public Location(Street street, String city, String state, String country, Coordinates coordinates, Timezone timezone) {

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Location(Street street, String city, String state, String country, String postcode, Coordinates coordinates, Timezone timezone) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country; // Atribuímos o valor do país
        this.postcode = postcode;
        this.coordinates = coordinates;
        this.timezone = timezone;
    }

    public Street getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostcode() {
        return postcode;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public String getFullAddress() {
        return street.getNumber() + " " + street.getName() + ", " + city + ", " + state + " " + postcode;
    }
}




