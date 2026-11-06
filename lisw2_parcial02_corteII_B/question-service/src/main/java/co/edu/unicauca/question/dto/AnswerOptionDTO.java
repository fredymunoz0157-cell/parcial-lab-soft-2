package co.edu.unicauca.question.dto;

/**
 * DTO for receiving and returning an AnswerOption in HTTP requests.
 */
public class AnswerOptionDTO {

    private String text;
    private boolean correct;

    public AnswerOptionDTO() {
    }

    public AnswerOptionDTO(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
