package co.edu.unicauca.lifecycle.domain.state;

import co.edu.unicauca.lifecycle.domain.model.QuestionLifecycle;


public class DraftState implements IQuestionState {

    public static final String NAME = "DRAFT";

    @Override
    public void submitForReview(QuestionLifecycle question) {
        question.setState(new PendingReviewState());
    }

    @Override
    public String getStateName() {
        return NAME;
    }
}
