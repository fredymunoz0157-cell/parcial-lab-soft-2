package co.edu.unicauca.lifecycle.dto;

import java.io.Serializable;


public class SubmitQuestionForReview implements Serializable {

    private Long questionId;
    private String operationId;

    public SubmitQuestionForReview() {
    }

    public SubmitQuestionForReview(Long questionId, String operationId) {
        this.questionId = questionId;
        this.operationId = operationId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
}
