package chapter10;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class WorldCountriesApp {

    public static void main(String[] args) {
        List<Country> countries = Arrays.asList(
                new Country("EG", "Egypt", "Africa"),
                new Country("US", "United States", "North America"),
                new Country("FR", "France", "Europe"),
                new Country("DE", "Germany", "Europe"),
                new Country("JP", "Japan", "Asia")
        );

        List<City> cities = Arrays.asList(
                new City("Cairo", "EG", 9_500_000, true),
                new City("Alexandria", "EG", 5_200_000, false),
                new City("Washington D.C.", "US", 705_000, true),
                new City("New York", "US", 8_400_000, false),
                new City("Paris", "FR", 2_140_000, true),
                new City("Marseille", "FR", 870_000, false),
                new City("Berlin", "DE", 3_670_000, true),
                new City("Munich", "DE", 1_470_000, false),
                new City("Tokyo", "JP", 13_960_000, true),
                new City("Osaka", "JP", 2_690_000, false)
        );

        Map<String, String> countryCodeToContinent = countries.stream()
                .collect(Collectors.toMap(Country::getCountryCode, Country::getContinent));
        Map<String, String> countryCodeToName = countries.stream()
                .collect(Collectors.toMap(Country::getCountryCode, Country::getName));

        System.out.println("Highest populated city of each country:");
        Map<String, City>highestPerCountry = cities.stream()
                .collect(Collectors.toMap(
                        City::getCountryCode,
                        city -> city,
                        (city1, city2) -> city1.getPopulation() <= city2.getPopulation() ? city1 : city2));
        highestPerCountry.forEach((code, city) ->
                System.out.println(countryCodeToName.get(code) + ": " + city));

        System.out.println();
        System.out.println("Most populated city of each continent:");
        Map<String, City> highestPerContinent = cities.stream()
                .collect(Collectors.toMap(
                        city -> countryCodeToContinent.get(city.getCountryCode()),
                        city -> city,
                        (city1, city2) -> city1.getPopulation() >= city2.getPopulation() ? city1 : city2));
        highestPerContinent.forEach((continent, city) ->
                System.out.println(continent + ": " + city));

        System.out.println();
        System.out.println("Highest populated capital city:");
        Optional<City> highestCapital = cities.stream()
                .filter(City::isCapital)
                .max(Comparator.comparingLong(City::getPopulation));
        highestCapital.ifPresent(city ->
                System.out.println(countryCodeToName.get(city.getCountryCode()) + ": " + city));
    }
}
