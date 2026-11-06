package co.edu.unicauca.question.domain.builder;

import co.edu.unicauca.question.domain.model.AnswerOption;
import co.edu.unicauca.question.domain.model.Question;

import java.util.ArrayList;
import java.util.List;


public class QuestionBuilder implements IQuestionBuilder {

    private static final int REQUIRED_OPTIONS_COUNT = 4;
    private static final long REQUIRED_CORRECT_ANSWERS_COUNT = 1L;

    private Long id;
    private String context;
    private String statement;
    private String justification;
    private final List<AnswerOption> options = new ArrayList<>();

    @Override
    public IQuestionBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public IQuestionBuilder withContext(String context) {
        this.context = context;
        return this;
    }

    @Override
    public IQuestionBuilder withStatement(String statement) {
        this.statement = statement;
        return this;
    }

    @Override
    public IQuestionBuilder withJustification(String justification) {
        this.justification = justification;
        return this;
    }

    @Override
    public IQuestionBuilder addOption(String text, boolean isCorrect) {
        this.options.add(new AnswerOption(text, isCorrect));
        return this;
    }

    @Override
    public IQuestionBuilder withOptions(List<AnswerOption> options) {
        this.options.clear();
        if (options != null) {
            this.options.addAll(options);
        }
        return this;
    }

    @Override
    public Question build() {
        if (context == null || context.isBlank()) {
            throw new IllegalArgumentException("Question context is required.");
        }
        if (statement == null || statement.isBlank()) {
            throw new IllegalArgumentException("Question statement is required.");
        }
        if (justification == null || justification.isBlank()) {
            throw new IllegalArgumentException("Question justification is required.");
        }
        if (options.size() != REQUIRED_OPTIONS_COUNT) {
            throw new IllegalArgumentException(
                    "Question must have exactly 4 answer options, but received: " + options.size()
            );
        }
        boolean hasBlankOption = options.stream()
                .anyMatch(opt -> opt == null || opt.getText() == null || opt.getText().isBlank());
        if (hasBlankOption) {
            throw new IllegalArgumentException("All 4 answer options must have non-empty text.");
        }
        long correctCount = options.stream()
                .filter(AnswerOption::isCorrect)
                .count();
        if (correctCount != REQUIRED_CORRECT_ANSWERS_COUNT) {
            throw new IllegalArgumentException(
                    "Question must have exactly 1 correct answer identified, but found: " + correctCount
            );
        }
        return new Question(id, context, statement, justification, options);
    }
}
