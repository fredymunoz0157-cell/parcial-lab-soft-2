package co.edu.unicauca.lifecycle.domain.state;

import co.edu.unicauca.lifecycle.domain.model.QuestionLifecycle;


public class PendingReviewState implements IQuestionState {

    public static final String NAME = "PENDING_REVIEW";

    @Override
    public void submitForReview(QuestionLifecycle question) {
        throw new IllegalStateException("Question is already in PENDING_REVIEW state.");
    }

    @Override
    public String getStateName() {
        return NAME;
    }
}
