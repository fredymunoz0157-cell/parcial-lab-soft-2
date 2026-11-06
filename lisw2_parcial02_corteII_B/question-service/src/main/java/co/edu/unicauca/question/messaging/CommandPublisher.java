package co.edu.unicauca.question.messaging;

import co.edu.unicauca.question.config.RabbitMQConfig;
import co.edu.unicauca.question.dto.SubmitQuestionForReview;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * Publishes asynchronous commands to RabbitMQ
 */
@Component
public class CommandPublisher {

    private final RabbitTemplate rabbitTemplate;

    public CommandPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Publishes the SubmitQuestionForReview command to 'saberpro.direct.exchange'
     * using routing key 'question.submit'.
     *
     * @param command the command containing questionId and operationId
     */
    public void publishSubmitForReview(SubmitQuestionForReview command) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY_SUBMIT,
                command
        );
    }
}
