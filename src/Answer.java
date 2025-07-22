import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Answer {
    private final Question question;
    private int score;

    Answer(Question question) {
        this.question = question;
        score = 0;
    }

    public void findAnswerCapitalQuiz(Country choosedCountry) {
        System.out.println("Choose number between 1 and " + this.question.getChoiceOptions().size());
        int answer = Integer.parseInt(chooseAnswers(true));
        if (checkAnswerCapitalQuiz(choosedCountry, answer)) {
            score++;
        }
    }

    public void findAnswerPopulationQuiz() {
        System.out.println("\nChoose multiple numbers between 2 and " + question.getChoiceOptions().size());
        String answer = chooseAnswers(false);
        if (checkAnswerPopulationQuiz(answer)) {
            this.score++;
        }
    }

    private String chooseAnswers(boolean isOneAnswer) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String choice = scanner.nextLine();
            if (!checkRange(choice)) {
                continue;
            }
            if (!checkValidNumbers(choice)) {
                continue;
            }
            if (isOneAnswer && choice.length() != 1) {
                System.out.println("You can only use one number !");
                continue;
            } else if (isOneAnswer) {
                return choice;
            }
            if (!checkRepetition(choice)) {
                continue;
            }
            return choice;
        }
    }

    private boolean checkRange(String choice) {
        if (choice.isEmpty() || choice.length() > this.question.getChoiceOptions().size()) {
            System.out.println("Out of bounds! The maximum range is " + this.question.getChoiceOptions().size());
            return false;
        }
        return true;
    }

    private boolean checkValidNumbers(String choice) {
        boolean isCorrectChar;
        for (int i = 0; i < choice.length(); i++) {
            isCorrectChar = false;
            for (int j = 0; j < this.question.getChoiceOptions().size(); j++) {
                if (Character.getNumericValue(choice.charAt(i)) == j + 1) {
                    isCorrectChar = true;
                }
            }
            if (!isCorrectChar) {
                System.out.println("You can only use numbers between 1 and " + this.question.getChoiceOptions().size() + " !");
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

    private boolean checkAnswerCapitalQuiz(Country choosedCountry, int answer) {
        return choosedCountry.equals(this.question.getChoiceOptions().get(answer - 1));
    }

    public boolean checkAnswerPopulationQuiz(String answer) {
        if (question.getNumberOfCorrectAnswers() != answer.length()) {
            System.out.println("***Number of correct answers was: " + question.getNumberOfCorrectAnswers() + "***");
            return false;
        }
        int counter = 0;
        for (int i = 0; i < answer.length(); i++) {
            int choice = Character.getNumericValue(answer.charAt(i)) - 1;
            if (question.getRandomNumber() == 1 && question.getChoiceOptions().get(choice).getPopulation() > question.getRandomNumberOfPopulation()) {
                counter++;
            } else if (question.getRandomNumber() == 0 && question.getChoiceOptions().get(choice).getPopulation() < question.getRandomNumberOfPopulation()) {
                counter++;
            }
        }
        System.out.println("Number of correct answers was: " + question.getNumberOfCorrectAnswers());
        return question.getNumberOfCorrectAnswers() == counter;
    }

    public int getScore() {
        return this.score;
    }
}
