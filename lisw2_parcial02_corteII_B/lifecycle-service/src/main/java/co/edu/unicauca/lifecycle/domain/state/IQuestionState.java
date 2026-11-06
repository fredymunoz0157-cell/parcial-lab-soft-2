package co.edu.unicauca.lifecycle.domain.state;

import co.edu.unicauca.lifecycle.domain.model.QuestionLifecycle;


public interface IQuestionState {

    void submitForReview(QuestionLifecycle question);

    String getStateName();
}
