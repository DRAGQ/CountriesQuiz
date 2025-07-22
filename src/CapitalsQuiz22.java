import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CapitalsQuiz extends Quiz{
    private final Scanner scanner;
    private Country choosedCountry;
    private final List<Country> usedCountries;

    public CapitalsQuiz(String name, int numberOfAnswers, int rounds, List<Country> countries) {
        super(name, numberOfAnswers, rounds, countries);
        this.scanner = new Scanner(System.in);
        this.usedCountries = new ArrayList<>();
    }

    @Override
    public void startQuiz() {
        prepareBoard();
        introduction();
        System.out.println("\n---| Select correct capital city. |---");
        for (int i = 0; i < (super.rounds); i++) {
            this.choosedCountry = generateRandomCountry();
            System.out.println("\n--What is the capital city of " + this.choosedCountry.getName() + "?--\n");
            generateChoiceAnswers();
            printAnswers();
            System.out.println("\nChoose number between 1 and " + super.choiceOptions.size());
            if (checkAnswer(chooseAnswers())) {
                super.score++;
            }
            super.choiceOptions.clear();
        }
        conclusion(super.score, super.rounds);
    }

    @Override
    public void prepareBoard() {
        score = 0;
        usedCountries.clear();
        choiceOptions.clear();
    }

    @Override
    public void introduction() {
        System.out.println("\n--->>>> Welcome to Capital Cities Quiz ! ---<<<\n");
        System.out.println("---This quiz is about choosing the right capitals !---\n");
        System.out.println("---If you want to start press enter.---");
        this.scanner.nextLine();
        System.out.println("---------------------------------------------------------");
    }

    @Override
    public void generateChoiceAnswers() {
        QuizUtils.createAnswers(super.choiceOptions, super.countries, super.numberOfAnswers);
    }

    @Override
    public void printAnswers() {
        for (int i = 0; i < super.choiceOptions.size(); i++) {
            System.out.println((i + 1) + ". " + super.choiceOptions.get(i).getCapitalCity() + " ");
        }
    }

    @Override
    public String chooseAnswers() {
        while (true) {
            try {
                int choice = this.scanner.nextInt();
                if (choice < 1 || choice > super.choiceOptions.size()) {
                    System.out.println("Out of bounds! Only numbers between 1 and " + (super.choiceOptions.size() + " are allowed!"));
                } else {
                    return String.valueOf(choice);
                }
            } catch(InputMismatchException e) {
                System.out.println("Only numbers are allowed !");
                scanner.next();
            }
        }
    }

    @Override
    public boolean checkAnswer(String answer) {
        return this.choosedCountry.equals(super.choiceOptions.get(Integer.parseInt(answer) - 1));
    }

    private Country generateRandomCountry() {
        while (true) {
            QuizUtils.chooseRandomCountry(super.choiceOptions, super.countries);
            Country country = super.choiceOptions.get(super.choiceOptions.size() - 1);
            if (!this.usedCountries.contains(country)) {
                this.usedCountries.add(country);
                return country;
            }
        }
    }
}
