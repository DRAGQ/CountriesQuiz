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
}
