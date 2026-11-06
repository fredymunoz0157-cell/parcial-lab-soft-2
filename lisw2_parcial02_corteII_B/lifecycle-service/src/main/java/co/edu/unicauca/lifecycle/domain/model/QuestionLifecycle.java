package co.edu.unicauca.lifecycle.domain.model;

import co.edu.unicauca.lifecycle.domain.state.DraftState;
import co.edu.unicauca.lifecycle.domain.state.IQuestionState;


public class QuestionLifecycle {

    private final Long questionId;
    private IQuestionState state;
    private String lastOperationId;

    public QuestionLifecycle(Long questionId) {
        this.questionId = questionId;
        this.state = new DraftState();
    }

    public void submitForReview(String operationId) {
        this.state.submitForReview(this);
        this.lastOperationId = operationId;
    }

    public Long getQuestionId() {
        return questionId;
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

    public String getLastOperationId() {
        return lastOperationId;
    }
}
