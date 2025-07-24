import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Question {
    private final String question;
    private final boolean isSingleQuestion;
    private final List<Answer> answers;

    Question(String question, boolean isSingleQuestion) {
        this.question = question;
        this.isSingleQuestion = isSingleQuestion;
        this.answers = new ArrayList<>();
    }

    public void printQuestion(int questionIndex) {
        System.out.println(questionIndex + ". What is " + this.question + "? " +
                (this.isSingleQuestion ? "(Question with single answer)" : "(Question with multiple answers)"));

        for (Answer answer : this.answers) {
            System.out.print(answer.getIndex() + ". ");
            answer.printAnswer();
        }
    }

    public void createAnswers(char index, String answer, boolean isCorrect) {
        this.answers.add(new Answer(index, answer, isCorrect));
    }

    public boolean checkAnswer(Scanner scanner) {
        while (true) {
            String myAnswer = scanner.nextLine();

            if (!checkValidIndex(myAnswer)) {
                System.out.println("Choose correct indexes");
                continue;
            }
            if (!checkRepetition(myAnswer)) {
                System.out.println("Every index must be used just once");
                continue;
            }

            for (Answer answer : this.answers) {
                int counter = 0;
                for (int i = 0; i < myAnswer.length(); i++) {
                    if (answer.getIndex() == myAnswer.charAt(i) && !answer.isCorrect()) {
                        return false;
                    }
                    if (answer.isCorrect() && answer.getIndex() != myAnswer.charAt(i)) {
                        counter++;
                    }
                }
                if (counter == myAnswer.length()) {
                    return false;
                }
            }
            return true;
        }
    }

    private boolean checkValidIndex(String myAnswer) {
        for (int i = 0; i < myAnswer.length(); i++) {
            int counter = 0;
            for (Answer answer : this.answers) {
                if (myAnswer.charAt(i) == answer.getIndex()) {
                    counter++;
                }
            }
            if (counter != 1) {
                return false;
            }
        }
        return true;
    }

    private boolean checkRepetition(String myAnswer) {
        for (int i = 0; i < myAnswer.length() - 1; i++) {
            for (int j = 1 + i; j < myAnswer.length(); j++) {
                if (myAnswer.charAt(i) == myAnswer.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
