package co.edu.unicauca.question.dto;

import java.util.List;


public class QuestionResponseDTO {

    private Long id;
    private String context;
    private String statement;
    private String justification;
    private String status;
    private List<AnswerOptionDTO> options;

    public QuestionResponseDTO() {
    }

    public QuestionResponseDTO(Long id, String context, String statement, String justification, String status, List<AnswerOptionDTO> options) {
        this.id = id;
        this.context = context;
        this.statement = statement;
        this.justification = justification;
        this.status = status;
        this.options = options;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AnswerOptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<AnswerOptionDTO> options) {
        this.options = options;
    }
}
