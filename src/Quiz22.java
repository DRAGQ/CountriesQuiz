import java.util.ArrayList;
import java.util.List;

public abstract class Quiz {
    private final String name;
    protected final List<Country> countries;
    protected List<Country> choiceOptions;
    protected final int numberOfAnswers;
    protected final int rounds;
    protected int score;

    public Quiz(String name, int numberOfAnswers, int rounds, List<Country> countries) {
        this.name = name;
        this.countries = new ArrayList<>(countries);
        this.choiceOptions = new ArrayList<>();
        this.numberOfAnswers = numberOfAnswers;
        this.rounds = rounds;
    }

    public abstract void startQuiz();

    public abstract void prepareBoard();

    public abstract void introduction();

    public abstract void generateChoiceAnswers();

    public abstract void printAnswers();

    public abstract String chooseAnswers();

    public abstract boolean checkAnswer(String answer);

    public void conclusion(int score, int rounds) {
        System.out.println("\n===---The score is " + score + "/" + rounds + "---===\n");
        if (score < 2) {
            System.out.println("You should review your geography a bit.");
        } else if (score < rounds) {
            System.out.println("Your knowledge isn't bad, but you could use some geography practice..");
        } else {
            System.out.println("Congratulations, you got full points !");
        }
    }

    public String getName() {
        return this.name;
    }
}
