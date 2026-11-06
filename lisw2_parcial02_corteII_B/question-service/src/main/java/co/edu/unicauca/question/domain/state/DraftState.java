package co.edu.unicauca.question.domain.state;

import co.edu.unicauca.question.domain.model.Question;


public class DraftState implements IQuestionState {

    public static final String NAME = "DRAFT";

    @Override
    public void submitForReview(Question question) {
        question.setState(new PendingReviewState());
    }

    @Override
    public String getStateName() {
        return NAME;
    }
}
