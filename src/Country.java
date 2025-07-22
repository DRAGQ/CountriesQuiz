public class Country {
    private String name;
    private String capitalCity;
    private double population;

    Country(String name, String capitalCity, double population) {
        this.name = name;
        this.capitalCity = capitalCity;
        this.population = population;
    }

    public String getName() {
        return this.name;
    }

    public String getCapitalCity() {
        return this.capitalCity;
    }

    public double getPopulation() {
        return this.population;
    }
}
