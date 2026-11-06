package co.edu.unicauca.question.domain.state;

import co.edu.unicauca.question.domain.model.Question;

/**
 * Concrete GoF State representing the Pending Review state (PENDING_REVIEW).
 * Rejects duplicate submissions directly via polymorphism without conditionals.
 */
public class PendingReviewState implements IQuestionState {

    public static final String NAME = "PENDING_REVIEW";

    @Override
    public void submitForReview(Question question) {
        throw new IllegalStateException("Question is already in PENDING_REVIEW state.");
    }

    @Override
    public String getStateName() {
        return NAME;
    }
}
