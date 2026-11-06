package co.edu.unicauca.lifecycle.messaging;

import co.edu.unicauca.lifecycle.config.RabbitMQConfig;
import co.edu.unicauca.lifecycle.domain.model.QuestionLifecycle;
import co.edu.unicauca.lifecycle.dto.SubmitQuestionForReview;
import co.edu.unicauca.lifecycle.service.LifecycleService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class CommandListener {

    private final LifecycleService lifecycleService;

    public CommandListener(LifecycleService lifecycleService) {
        this.lifecycleService = lifecycleService;
    }

    
    @RabbitListener(queues = RabbitMQConfig.QUEUE_SUBMIT_REVIEW)
    public void handleSubmitForReview(SubmitQuestionForReview command) {
        System.out.println("[LifecycleService] Received SubmitQuestionForReview command for questionId="
                + command.getQuestionId() + ", operationId=" + command.getOperationId());

        QuestionLifecycle updated = lifecycleService.processTransitionToPendingReview(
                command.getQuestionId(),
                command.getOperationId()
        );

        System.out.println("[LifecycleService] Question " + updated.getQuestionId()
                + " transitioned to state: " + updated.getStatus());
    }
}
