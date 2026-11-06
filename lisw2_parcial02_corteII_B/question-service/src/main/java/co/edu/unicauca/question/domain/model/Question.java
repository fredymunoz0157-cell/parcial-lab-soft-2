package co.edu.unicauca.question.domain.model;

import co.edu.unicauca.question.domain.state.DraftState;
import co.edu.unicauca.question.domain.state.IQuestionState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Question {

    private Long id;
    private String context;
    private String statement;
    private String justification;
    private List<AnswerOption> options;
    private IQuestionState state;
    
    public Question(Long id, String context, String statement, String justification, List<AnswerOption> options) {
        this.id = id;
        this.context = context;
        this.statement = statement;
        this.justification = justification;
        this.options = new ArrayList<>(options);
        this.state = new DraftState();
    }

    public void submitForReview() {
        this.state.submitForReview(this);
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

    public String getStatement() {
        return statement;
    }

    public String getJustification() {
        return justification;
    }

    public List<AnswerOption> getOptions() {
        return Collections.unmodifiableList(options);
    }

    public IQuestionState getState() {
        return state;
    }

    public void setState(IQuestionState state) {
        this.state = state;
    }

    public String getStatus() {
        return state.getStateName();
    }
}
