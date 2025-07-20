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

    public String getName() {
        return this.name;
    }
}
