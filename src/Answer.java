public class Answer {
    private final char index;
    private final String answer;
    private final boolean isCorrect;

    Answer(char index, String answer, boolean isCorrect) {
        this.index = index;
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public char getIndex() {
        return this.index;
    }

    public String getAnswer() {
        return this.answer;
    }

    public boolean isCorrect() {
        return this.isCorrect;
    }

    public void printAnswer() {
        System.out.println(this.answer);
    }
}
