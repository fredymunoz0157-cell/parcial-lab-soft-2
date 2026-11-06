package co.edu.unicauca.question.domain.builder;

import co.edu.unicauca.question.domain.model.AnswerOption;
import co.edu.unicauca.question.domain.model.Question;

import java.util.List;


public interface IQuestionBuilder {

    IQuestionBuilder withId(Long id);

    IQuestionBuilder withContext(String context);

    IQuestionBuilder withStatement(String statement);

    IQuestionBuilder withJustification(String justification);

    IQuestionBuilder addOption(String text, boolean isCorrect);

    IQuestionBuilder withOptions(List<AnswerOption> options);

   
    Question build();
}
