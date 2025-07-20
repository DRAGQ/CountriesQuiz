import java.util.ArrayList;
import java.util.List;

public class CountryTemplate {
    public static List<Country> getAllCountries() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("Australia", "Canberra", 26.66));
        countries.add(new Country("Brazil", "Brasilia", 211.1));
        countries.add(new Country("China", "Beijing", 1415.94));
        countries.add(new Country("Czech Republic", "Prague", 10.60));
        countries.add(new Country("France", "Paris", 66.66));
        countries.add(new Country("Germany", "Berlin", 84.05));
        countries.add(new Country("Italy", "Rome", 59.14));
        countries.add(new Country("Japan", "Tokyo", 123.07));
        countries.add(new Country("Panama", "Panama City", 4.46));
        countries.add(new Country("Poland", "Warsaw", 38.12));
        countries.add(new Country("Portugal", "Lisbon", 10.41));
        countries.add(new Country("Romania", "Bucharest", 18.90));
        countries.add(new Country("Russia", "Moscow", 143.96));
        countries.add(new Country("San Marino", "San Marino", 0.03));
        countries.add(new Country("Saudi Arabia", "Riyadh", 34.6));
        countries.add(new Country("Serbia", "Belgrade", 6.69));
        countries.add(new Country("Slovakia", "Bratislava", 5.47));
        countries.add(new Country("Slovenia", "Ljubljana", 2.11));
        countries.add(new Country("Spain", "Madrid", 47.89));
        countries.add(new Country("Ukraine", "Kyiv", 39.03));
        countries.add(new Country("United States", "Washington", 387.63));
        return countries;
    }
}
