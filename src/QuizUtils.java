import java.util.Collections;
import java.util.List;

public class QuizUtils {

    public static void createAnswers(List<Country> choiceAnswers, List<Country> countries, int numberOfAnswers) {
        for (int i = 0; i < numberOfAnswers; i++) {
            chooseRandomCountry(choiceAnswers, countries);
        }
        Collections.shuffle(choiceAnswers);
    }

    public static void chooseRandomCountry(List<Country> choiceAnswers, List<Country> countries) {
        while (true) {
            int randomNumber = (int) (Math.random() * countries.size());
            Country country = countries.get(randomNumber);
            if (addRandomCountry(choiceAnswers, country)) {
                break;
            }
        }
    }

    public static boolean addRandomCountry(List<Country> choiceAnswers, Country country) {
        if (!choiceAnswers.contains(country)) {
            choiceAnswers.add(country);
            return true;
        }
        return false;
    }

    public static void conclusion(int score, int rounds) {
        System.out.println("\n===---The score is " + score + "/" + rounds + "---===\n");
        if (score < 2) {
            System.out.println("You should review your geography a bit.");
        } else if (score < rounds) {
            System.out.println("Your knowledge isn't bad, but you could use some geography practice..");
        } else {
            System.out.println("Congratulations, you got full points !");
        }
    }


}
