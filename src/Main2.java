import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //startGame();
        new Quiz2();
    }

    /*private static void startGame() {
        Scanner scanner = new Scanner(System.in);
        List<Country> countries = new ArrayList<>(CountryTemplate.getAllCountries());
        List<Quiz> quizzes = new ArrayList<>();
        quizzes.add(new CapitalsQuiz("Capitals Quiz", 3, 4, countries));
        quizzes.add(new PopulationQuiz("Population Quiz", 5, 4, countries));

        System.out.println("\n********** Welcome in my Quiz **********\n");
        System.out.println("#####  Choose which quiz you want to play  #####\n");

        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).getName());
        }
        int chosenQuiz = chooseAnswer(scanner, quizzes.size()) - 1;
        quizzes.get(chosenQuiz).startQuiz();
        restartGame(scanner);
    }

    private static int chooseAnswer(Scanner scanner, int length) {
        while (true) {
            System.out.println("\nChoose a number between 1 and " + (length) + ":");
            try {
                int choice = scanner.nextInt();
                if (choice < 1 || choice > length) {
                    System.out.println("Out of bounds!");
                } else {
                    return choice;
                }
            } catch (InputMismatchException e) {
                System.out.println("Only numbers are allowed !");
                scanner.next();
            }
        }
    }

    private static void restartGame(Scanner scanner) {
        scanner.nextLine();
        while (true) {
            System.out.println("\nChoose whether to restart the game y/n");
            String choice = scanner.nextLine();
            if (choice.equals("y")) {
                startGame();
                return;
            } else if (choice.equals("n")) {
                scanner.close();
                System.out.println("Thank you for playing !");
                return;
            }
        }
    }*/

}