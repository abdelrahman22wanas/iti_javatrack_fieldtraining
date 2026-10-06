package chapter10;

public class Country {

    private String countryCode;
    private String name;
    private String continent;

    public Country(String countryCode, String name, String continent) {
        this.countryCode = countryCode;
        this.name = name;
        this.continent = continent;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    @Override
    public String toString() {
        return name + " (" + countryCode + ", " + continent + ")";
    }
}
