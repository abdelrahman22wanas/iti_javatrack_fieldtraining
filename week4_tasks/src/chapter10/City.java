package chapter10;

public class City {

    private String name;
    private String countryCode;
    private long population;
    private boolean capital;

    public City(String name, String countryCode, long population, boolean capital) {
        this.name = name;
        this.countryCode = countryCode;
        this.population = population;
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public long getPopulation() {
        return population;
    }

    public boolean isCapital() {
        return capital;
    }

    @Override
    public String toString() {
        return name + " (population: " + population + (capital ? ", capital" : "") + ")";
    }
}
