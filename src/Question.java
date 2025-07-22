import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    private final List<Country> countries;
    private final int numberOfAnswers;
    private int numberOfCorrectAnswers;
    private int randomNumberOfPopulation;
    private int randomNumber;
    private final List<Country> usedCountries;
    private final List<Country> choiceOptions;


    Question(List<Country> countries, int numberOfAnswers) {
        this.countries = countries;
        this.numberOfAnswers = numberOfAnswers;
        this.usedCountries = new ArrayList<>();
        this.choiceOptions = new  ArrayList<>();
    }

    public Country findCorrectCapital() {
        System.out.println("\n---| Select correct capital city. |---");
        Country choosedCountry = generateRandomCountry();
        System.out.println("\n--What is the capital city of " + choosedCountry.getName() + "?--\n");
        createAnswers();
        printAnswers(false);
        return choosedCountry;
    }

    public void findCorrectPopulation() {
        System.out.println("\n---| Select multiple countries with a larger or smaller population. |---");
        this.randomNumber = (int) (Math.random() * 2);
        this.randomNumberOfPopulation = (int) (Math.random() * (200 - 15) + 15);
        System.out.println("\n--Select countries that have " +
                (this.randomNumber == 1 ? "more" : "less") +
                " inhabitants than " + this.randomNumberOfPopulation +
                " milions--\n"
        );
        generateChoiceAnswersPopulationQuiz();
        printAnswers(true);
    }

    private void generateChoiceAnswersPopulationQuiz() {
        do {
            this.choiceOptions.clear();
            createAnswers();
            this.numberOfCorrectAnswers = 0;
            for (Country country : this.choiceOptions) {
                if (this.randomNumber == 1 && country.getPopulation() > this.randomNumberOfPopulation) {
                    this.numberOfCorrectAnswers++;
                } else if (this.randomNumber == 0 && country.getPopulation() < this.randomNumberOfPopulation) {
                    this.numberOfCorrectAnswers++;
                }
            }
        } while (this.numberOfCorrectAnswers <= 1);
    }

    private Country generateRandomCountry() {
        while (true) {
            chooseRandomCountry();
            Country country = this.choiceOptions.get(this.choiceOptions.size() - 1);
            if (!this.usedCountries.contains(country)) {
                this.usedCountries.add(country);
                return country;
            }
        }
    }

    private void chooseRandomCountry() {
        while (true) {
            int randomNumber = (int) (Math.random() * this.countries.size());
            Country country = this.countries.get(randomNumber);
            if (addRandomCountry(country)) {
                break;
            }
        }
    }

    public boolean addRandomCountry(Country country) {
        if (!this.choiceOptions.contains(country)) {
            this.choiceOptions.add(country);
            return true;
        }
        return false;
    }

    public void createAnswers() {
        for (int i = 0; i < this.numberOfAnswers; i++) {
            chooseRandomCountry();
        }
        Collections.shuffle(this.choiceOptions);
    }

    private void printAnswers(boolean isCountry) {
        for (int i = 0; i < this.choiceOptions.size(); i++) {
            if (isCountry) {
                System.out.println((i + 1) + ". " + this.choiceOptions.get(i).getName() + " ");
            } else {
                System.out.println((i + 1) + ". " + this.choiceOptions.get(i).getCapitalCity() + " ");
            }
        }
        System.out.println();
    }

    public List<Country> getChoiceOptions() {
        return this.choiceOptions;
    }

    public int getNumberOfCorrectAnswers() {
        return this.numberOfCorrectAnswers;
    }

    public int getRandomNumber() {
        return this.randomNumber;
    }

    public int getRandomNumberOfPopulation() {
        return this.randomNumberOfPopulation;
    }

    public void clearChoiceOptions() {
        this.choiceOptions.clear();
    }
}
