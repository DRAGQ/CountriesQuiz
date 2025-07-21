import java.util.List;
import java.util.Scanner;

public class PopulationQuiz extends Quiz {
    private final Scanner scanner;
    private int randomNumber;
    private int randomNumberOfPopulation;
    private int numberOfCorrectAnswers;

    public PopulationQuiz(String name, int numberOfAnswers, int rounds, List<Country> countries) {
        super(name, numberOfAnswers, rounds, countries);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void startQuiz() {
        prepareBoard();
        System.out.println("\n---| Select multiple countries with a larger or smaller population. |---");
        for (int i = 0; i < (super.rounds); i++) {
            this.randomNumber = (int) (Math.random() * 2);
            this.randomNumberOfPopulation = (int) (Math.random() * (200 - 15) + 15);
            System.out.println("\n--Select countries that have " +
                    (this.randomNumber == 1 ? "more" : "less") +
                    " inhabitants than " + this.randomNumberOfPopulation +
                    " milions--\n"
            );
            generateChoiceAnswers();
            printAnswers();
            System.out.println("\nChoose multiple numbers between 1 and " + super.choiceOptions.size());
            String answer = chooseAnswers();
            if (checkAnswer(answer)) {
                super.score++;
            }
        }
        conclusion(super.score, super.rounds);
    }

    @Override
    public void prepareBoard() {
        score = 0;
        choiceOptions.clear();
    }

    @Override
    public void introduction() {
        System.out.println("\n--->>>> Welcome to Population Quiz ! ---<<<\n");
        System.out.println("---This quiz is about choosing which countries have more or less population !---\n");
        System.out.println("---If you want to start press enter.---");
        scanner.nextLine();
    }

    @Override
    public void generateChoiceAnswers() {
        do {
            super.choiceOptions.clear();
            QuizUtils.createAnswers(super.choiceOptions, super.countries, 5);
            this.numberOfCorrectAnswers = 0;
            for (Country country : super.choiceOptions) {
                if (this.randomNumber == 1 && country.getPopulation() > this.randomNumberOfPopulation) {
                    this.numberOfCorrectAnswers++;
                } else if (this.randomNumber == 0 && country.getPopulation() < this.randomNumberOfPopulation) {
                    this.numberOfCorrectAnswers++;
                }
            }
        } while (this.numberOfCorrectAnswers <= 1);
    }

    @Override
    public void printAnswers() {
        for (int i = 0; i < super.choiceOptions.size(); i++) {
            System.out.println((i + 1) + ". " + super.choiceOptions.get(i).getName() + " ");
        }
    }

    @Override
    public String chooseAnswers() {
        while (true) {
            String choice = scanner.nextLine();
            if (!checkRange(choice)) {
                continue;
            }
            if (!checkValidNumbers(choice)) {
                continue;
            }
            if (!checkRepetition(choice)) {
                continue;
            }
            return choice;

        }
    }

    @Override
    public boolean checkAnswer(String answer) {
        if (this.numberOfCorrectAnswers != answer.length()) {
            System.out.println("***Number of correct answers was: " + this.numberOfCorrectAnswers + "***");
            return false;
        }
        int counter = 0;
        for (int i = 0; i < answer.length(); i++) {
            int choice = Character.getNumericValue(answer.charAt(i)) - 1;
            if (this.randomNumber == 1 && super.choiceOptions.get(choice).getPopulation() > this.randomNumberOfPopulation) {
                counter++;
            } else if (this.randomNumber == 0 && super.choiceOptions.get(choice).getPopulation() < this.randomNumberOfPopulation) {
                counter++;
            }
        }
        System.out.println("Number of correct answers was: " + this.numberOfCorrectAnswers);
        return this.numberOfCorrectAnswers == counter;
    }

    private boolean checkRange(String choice) {
        if (choice.isEmpty() || choice.length() > super.choiceOptions.size()) {
            System.out.println("Out of bounds! The maximum range is " + super.choiceOptions.size());
            return false;
        }
        return true;
    }

    private boolean checkValidNumbers(String choice) {
        boolean isCorrectChar;
        for (int i = 0; i < choice.length(); i++) {
            isCorrectChar = false;
            for (int j = 0; j < super.choiceOptions.size(); j++) {
                if (Character.getNumericValue(choice.charAt(i)) == j + 1) {
                    isCorrectChar = true;
                }
            }
            if (!isCorrectChar) {
                System.out.println("You can only use numbers between 1 and " + super.choiceOptions.size() + " !");
                return false;
            }
        }
        return true;
    }

    private boolean checkRepetition(String choice) {
        for (int i = 0; i < choice.length() - 1; i++) {
            char a = choice.charAt(i);
            for (int j = i + 1; j < choice.length(); j++) {
                if (a == choice.charAt(j)) {
                    System.out.println("Each character must be used only once !");
                    return false;
                }
            }
        }
        return true;
    }
}
