import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    private List<Question> questions;
    private int score;

    Quiz() {
        this.questions = new ArrayList<>();
        this.score = 0;
        introduction();
        createQuestions();
        startQuiz();
        printResult();

    }

    private void introduction() {
        System.out.println("\nWelcome to quiz Math Quiz.");
        System.out.println("This quiz has 3 questions.\n");
    }

    private void createQuestions() {
        Question question1 = new Question( "2 + 2", true);
        question1.createAnswers('a', "5", false);
        question1.createAnswers('b',"4", true);

        Question question2 = new Question("2 + 5", true);
        question2.createAnswers('a', "4", false);
        question2.createAnswers('b', "7", true);

        Question question3 = new Question("4 = ?", false);
        question3.createAnswers('a', "2^2", true);
        question3.createAnswers('b', "-2^2", true);
        question3.createAnswers('c', "1 + 3", true);
        question3.createAnswers('d', "4^1", true);

        this.questions.add(question1);
        this.questions.add(question2);
        this.questions.add(question3);
    }

    private void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < this.questions.size(); i++) {
            this.questions.get(i).printQuestion(i+1);
                System.out.println("Choose correct answer.");
                if(this.questions.get(i).checkAnswer(scanner)) {
                    this.score++;
                }
        }
        scanner.close();
    }

    private void printResult() {
        System.out.println("You had " + this.score + "/" +  this.questions.size() + " answers correct");
    }
}