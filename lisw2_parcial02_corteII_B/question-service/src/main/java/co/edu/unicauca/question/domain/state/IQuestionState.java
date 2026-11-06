package co.edu.unicauca.question.domain.state;

import co.edu.unicauca.question.domain.model.Question;


public interface IQuestionState {

    void submitForReview(Question question);
    
    String getStateName();
}
