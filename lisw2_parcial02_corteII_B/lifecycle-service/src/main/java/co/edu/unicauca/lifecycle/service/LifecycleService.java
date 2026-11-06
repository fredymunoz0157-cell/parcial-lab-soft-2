package co.edu.unicauca.lifecycle.service;

import co.edu.unicauca.lifecycle.domain.model.QuestionLifecycle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service executing the GoF State transition from DraftState to PendingReviewState (PENDING_REVIEW).
 */
@Service
public class LifecycleService {

    private final Map<Long, QuestionLifecycle> lifecycleStore = new ConcurrentHashMap<>();
    private final RestTemplate restTemplate = new RestTemplate();
    private final String questionServiceUrl;

    public LifecycleService(
            @Value("${question.service.url:http://localhost:8083/questions}") String questionServiceUrl
    ) {
        this.questionServiceUrl = questionServiceUrl;
    }

    
    public QuestionLifecycle processTransitionToPendingReview(Long questionId, String operationId) {
        QuestionLifecycle lifecycle = lifecycleStore.computeIfAbsent(questionId, QuestionLifecycle::new);
        lifecycle.submitForReview(operationId);

        try {
            restTemplate.postForLocation(
                    questionServiceUrl + "/" + questionId + "/transition-pending-review",
                    null
            );
        } catch (Exception ignored) {
            
        }
        return lifecycle;
    }

    public Optional<QuestionLifecycle> findByQuestionId(Long questionId) {
        return Optional.ofNullable(lifecycleStore.get(questionId));
    }
}
