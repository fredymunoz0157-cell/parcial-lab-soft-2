package co.edu.unicauca.question.service;

import co.edu.unicauca.question.client.ValidationClient;
import co.edu.unicauca.question.domain.builder.IQuestionBuilder;
import co.edu.unicauca.question.domain.builder.QuestionBuilder;
import co.edu.unicauca.question.domain.model.AnswerOption;
import co.edu.unicauca.question.domain.model.Question;
import co.edu.unicauca.question.dto.*;
import co.edu.unicauca.question.messaging.CommandPublisher;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


@Service
public class QuestionService {

    private final ValidationClient validationClient;
    private final CommandPublisher commandPublisher;
    private final Map<Long, Question> questionStore = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1L);

    public QuestionService(ValidationClient validationClient, CommandPublisher commandPublisher) {
        this.validationClient = validationClient;
        this.commandPublisher = commandPublisher;
    }

    
    public QuestionResponseDTO createQuestion(CreateQuestionRequestDTO request) {
        ValidationRequestDTO validationRequest = new ValidationRequestDTO(
                request.getContext(),
                request.getStatement(),
                request.getJustification()
        );
        ValidationResultDTO validationResult = validationClient.validate(validationRequest);
        if (!validationResult.isValid()) {
            throw new IllegalArgumentException(validationResult.getMessage());
        }

        IQuestionBuilder builder = new QuestionBuilder()
                .withId(idGenerator.getAndIncrement())
                .withContext(request.getContext())
                .withStatement(request.getStatement())
                .withJustification(request.getJustification());

        if (request.getOptions() != null) {
            for (AnswerOptionDTO opt : request.getOptions()) {
                builder.addOption(opt.getText(), opt.isCorrect());
            }
        }

        Question question = builder.build();
        questionStore.put(question.getId(), question);
        return toResponseDTO(question);
    }

    
    public String submitQuestionForReview(Long questionId) {
        String operationId = UUID.randomUUID().toString();
        SubmitQuestionForReview command = new SubmitQuestionForReview(questionId, operationId);
        commandPublisher.publishSubmitForReview(command);
        return operationId;
    }

    
    public QuestionResponseDTO transitionToPendingReview(Long questionId) {
        Question question = questionStore.get(questionId);
        if (question == null) {
            throw new NoSuchElementException("Question not found with id: " + questionId);
        }
        question.submitForReview();
        return toResponseDTO(question);
    }

    public Optional<QuestionResponseDTO> findById(Long id) {
        return Optional.ofNullable(questionStore.get(id)).map(this::toResponseDTO);
    }

    public List<QuestionResponseDTO> findAll() {
        return questionStore.values().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private QuestionResponseDTO toResponseDTO(Question question) {
        List<AnswerOptionDTO> optionDTOs = question.getOptions().stream()
                .map(o -> new AnswerOptionDTO(o.getText(), o.isCorrect()))
                .collect(Collectors.toList());
        return new QuestionResponseDTO(
                question.getId(),
                question.getContext(),
                question.getStatement(),
                question.getJustification(),
                question.getStatus(),
                optionDTOs
        );
    }
}
