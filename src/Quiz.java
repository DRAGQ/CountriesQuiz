import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    private final Scanner scanner;
    private final List<Country> countries;
    private final int numberOfAnswers;
    private final int rounds = 4;
    private final Question question;
    private final Answer answer;

    Quiz() {
        this.scanner = new Scanner(System.in);
        this.countries = new ArrayList<>(createCountries());
        this.numberOfAnswers = 3;
        this.question = new Question(this.countries, numberOfAnswers);
        this.answer = new Answer(this.question);
        startGame();
    }

    private void startGame() {
        introduction();
        startCapitalQuiz();
        startPopulationQuiz();
        conclusion();
        scanner.close();
    }

    private void introduction() {
        System.out.println("\n===--- Welcome to Capital Cities Quiz! ---===\n");
        System.out.println("---This quiz is about choosing the right capitals and their population!---\n");
        System.out.println("---If you want to start press enter.---");
        scanner.nextLine();
    }

    private void startCapitalQuiz() {
        for (int i = 0; i < rounds / 2; i++) {
            Country choosedCountry = this.question.findCorrectCapital();
            this.answer.findAnswerCapitalQuiz(choosedCountry);
            question.clearChoiceOptions();
        }
    }

    private void startPopulationQuiz() {
        for (int i = 0; i < rounds / 2; i++) {
            question.findCorrectPopulation();
            answer.findAnswerPopulationQuiz();
        }
    }

    private void conclusion() {
        System.out.println("\n===---The score is " + answer.getScore() + "/" + rounds + "---===\n");
        if (answer.getScore() < 2) {
            System.out.println("You should review your geography a bit.");
        } else if (answer.getScore() < rounds) {
            System.out.println("Your knowledge isn't bad, but you could use some geography practice..");
        } else {
            System.out.println("Congratulations, you got full points!");
        }
    }


    private List<Country> createCountries() {
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
