package co.edu.unicauca.question.dto;


public class ValidationRequestDTO {

    private String context;
    private String statement;
    private String justification;

    public ValidationRequestDTO() {
    }

    public ValidationRequestDTO(String context, String statement, String justification) {
        this.context = context;
        this.statement = statement;
        this.justification = justification;
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
}
