import java.util.*;

public class CountriesQuiz {
    private Scanner scanner;
    private List<Country> countries;
    private List<Country> usedCountries;
    private List<Country> choiceOptions;
    private int numberOfcorrectAnswers;
    private int score;
    private final int rounds;

    CountriesQuiz() {
        this.scanner = new Scanner(System.in);
        this.countries = new ArrayList<>();
        this.usedCountries = new ArrayList<>();
        this.choiceOptions = new ArrayList<>();
        this.score = 0;
        this.rounds = 4;
        createCountriesList();
        startGame();
    }

    private void startGame() {
        introduction();
        capitalsQuiz();
        populationQuiz();
        conclusion();
        restartGame();
    }

    private void introduction() {
        System.out.println("\n===--- Welcome to Capital Cities Quiz! ---===\n");
        System.out.println("---This quiz is about choosing the right capitals and their population!---\n");
        System.out.println("---If you want to start write any key and press enter.---");
        scanner.nextLine();
    }

    private void capitalsQuiz() {
        System.out.println("\n---| Select correct capital city. |---");
        for (int i = 0; i < (rounds / 2); i++) {
            Country choosedCountry = generateRandomCountry();
            System.out.println("\n--What is the capital city of " + choosedCountry.getName() + "?--\n");
            createOptions(3);
            printOptions(false);
            System.out.println("Choose number between 1 and " + choiceOptions.size());
            int answer = Integer.parseInt(chooseAnswers(true));
            if (checkAnswerCapitalQuiz(choosedCountry, answer)) {
                score++;
            }
            choiceOptions.clear();
        }
    }

    private void populationQuiz() {
        System.out.println("\n---| Select a country or multiple countries with a larger or smaller population. |---");
        for (int i = 0; i < (rounds / 2); i++) {
            int randomNumber = (int) (Math.random() * 2);
            int randomNumberForPopulation = (int) (Math.random() * (200 - 15) + 15);

            if (randomNumber == 1) {
                System.out.println("\n--Select countries that have more inhabitants than " + randomNumberForPopulation + " milions--\n");
            } else {
                System.out.println("\n--Select countries that have less inhabitants than " + randomNumberForPopulation + " milions--\n");
            }
            createValidOptionsForPopulationQuiz(randomNumber, randomNumberForPopulation);
            printOptions(true);
            System.out.println("Choose multiple numbers between 1 and " + choiceOptions.size());
            String answer = chooseAnswers(false);
            if (checkAnswerPopulationQuiz(randomNumberForPopulation, randomNumber, answer)) {
                score++;
            }
        }
    }

    private void conclusion() {
        System.out.println("\n===---The score is " + score + "/" + rounds + "---===\n");
        if (score < 2) {
            System.out.println("You should review your geography a bit.");
        } else if (score < rounds) {
            System.out.println("Your knowledge isn't bad, but you could use some geography practice..");
        } else {
            System.out.println("Congratulations, you got full points!");
        }
    }

    private Country generateRandomCountry() {
        while (true) {
            chooseRandomCountry();
            Country country = choiceOptions.get(choiceOptions.size() - 1);
            if (!usedCountries.contains(country)) {
                usedCountries.add(country);
                return country;
            }
        }
    }

    private void createOptions(int numberOfAnswers) {
        for (int i = 0; i < numberOfAnswers; i++) {
            chooseRandomCountry();
        }
        Collections.shuffle(choiceOptions);
    }

    private void printOptions(boolean isCountry) {
        for (int i = 0; i < choiceOptions.size(); i++) {
            if (isCountry) {
                System.out.println((i + 1) + ". " + choiceOptions.get(i).getName() + " ");
            } else {
                System.out.println((i + 1) + ". " + choiceOptions.get(i).getCapitalCity() + " ");
            }
        }
        System.out.println();
    }

    private String chooseAnswers(boolean isOneAnswer) {
        while (true) {
            boolean invalidInput = false;
            String choice = scanner.nextLine();
            if (choice.isEmpty() || choice.length() > choiceOptions.size()) {
                System.out.println("Out of bounds!");
                continue;
            }

            for (int i = 0; i < choice.length(); i++) {
                boolean isCorrectChar = false;
                for (int j = 0; j < choiceOptions.size(); j++) {
                    if (Character.getNumericValue(choice.charAt(i)) == j + 1) {
                        isCorrectChar = true;
                    }
                }
                if (!isCorrectChar) {
                    System.out.println("You can only use chars between 1 and " + choiceOptions.size() + " !");
                    invalidInput = true;
                    break;
                }
            }
            if (invalidInput) {
                continue;
            }

            if (isOneAnswer && choice.length() != 1) {
                System.out.println("You can only use one number !");
                continue;
            } else if (isOneAnswer) {
                return choice;
            }

            for (int i = 0; i < choice.length() - 1; i++) {
                char a = choice.charAt(i);
                for (int j = i + 1; j < choice.length(); j++) {
                    if (a == choice.charAt(j)) {
                        System.out.println("Each character must be used only once !");
                        invalidInput = true;
                        break;
                    }
                }
                if (invalidInput) {
                    break;
                }
            }
            if (!invalidInput) {
                return choice;
            }
        }
    }

    private void createValidOptionsForPopulationQuiz(int randomNumber, int randomNumberForPopulation) {
        do {
            choiceOptions.clear();
            createOptions(5);
            numberOfcorrectAnswers = 0;
            for (Country option : choiceOptions) {
                if (randomNumber == 1 && option.getPopulation() > randomNumberForPopulation) {
                    numberOfcorrectAnswers++;
                } else if (randomNumber == 0 && option.getPopulation() < randomNumberForPopulation) {
                    numberOfcorrectAnswers++;
                }
            }
        } while (numberOfcorrectAnswers <= 1);
    }

    private boolean checkAnswerCapitalQuiz(Country choosedCountry, int answer) {
        return choosedCountry.equals(choiceOptions.get(answer - 1));
    }

    private boolean checkAnswerPopulationQuiz(int randomNumberForPopulation, int randomNumber, String answer) {
        if (numberOfcorrectAnswers != answer.length()) {
            System.out.println("Number of correct answers was: " + numberOfcorrectAnswers);
            return false;
        }
        int counter = 0;
        for (int i = 0; i < answer.length(); i++) {
            int choice = Character.getNumericValue(answer.charAt(i)) - 1;
            if (randomNumber == 1 && choiceOptions.get(choice).getPopulation() > randomNumberForPopulation) {
                counter++;
            } else if (randomNumber == 0 && choiceOptions.get(choice).getPopulation() < randomNumberForPopulation) {
                counter++;
            }
        }
        System.out.println("Number of correct answers was: " + numberOfcorrectAnswers);
        return numberOfcorrectAnswers == counter;
    }

    private void chooseRandomCountry() {
        while (true) {
            int randomNumber = (int) (Math.random() * countries.size());
            Country country = countries.get(randomNumber);
            if (addRandomCountry(country)) {
                break;
            }
        }
    }

    private boolean addRandomCountry(Country country) {
        if (!choiceOptions.contains(country)) {
            choiceOptions.add(country);
            return true;
        }
        return false;
    }

    private void restartGame() {
        while (true) {
            System.out.println("\nChoose whether to restart the game y/n");
            String choice = scanner.nextLine();
            if (choice.equals("y")) {
                clearBoard();
                startGame();
                return;
            } else if (choice.equals("n")) {
                scanner.close();
                System.out.println("Thank you for playing !");
                return;
            }
        }
    }

    private void clearBoard() {
        score = 0;
        usedCountries.clear();
        choiceOptions.clear();

    }

    private void createCountriesList() {
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
    }
}
