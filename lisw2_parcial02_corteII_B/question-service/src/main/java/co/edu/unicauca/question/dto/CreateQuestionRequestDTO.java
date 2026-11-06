package co.edu.unicauca.question.dto;

import java.util.List;

/**
 * Request DTO for POST /questions.
 */
public class CreateQuestionRequestDTO {

    private String context;
    private String statement;
    private String justification;
    private List<AnswerOptionDTO> options;

    public CreateQuestionRequestDTO() {
    }

    public CreateQuestionRequestDTO(String context, String statement, String justification, List<AnswerOptionDTO> options) {
        this.context = context;
        this.statement = statement;
        this.justification = justification;
        this.options = options;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public List<AnswerOptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<AnswerOptionDTO> options) {
        this.options = options;
    }
}
