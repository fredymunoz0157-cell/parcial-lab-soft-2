package co.edu.unicauca.question;

import co.edu.unicauca.question.domain.builder.IQuestionBuilder;
import co.edu.unicauca.question.domain.builder.QuestionBuilder;
import co.edu.unicauca.question.domain.model.Question;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests verifying Point 1.1 (GoF Builder) and Point 1.2 (GoF State without conditionals).
 */
class QuestionPatternsTest {

    @Test
    void shouldBuildValidQuestionInDraftState() {
        IQuestionBuilder builder = new QuestionBuilder();
        Question question = builder
                .withId(1L)
                .withContext("En un sistema distribuido basado en microservicios...")
                .withStatement("¿Qué patrón desacopla el emisor del receptor mediante colas de mensajes?")
                .withJustification("La mensajería asíncrona con AMQP permite desacoplar temporal y espacialmente los servicios.")
                .addOption("Comunicación Asíncrona por Mensajería (AMQP)", true)
                .addOption("Acoplamiento directo de base de datos", false)
                .addOption("Monolito compartido en memoria", false)
                .addOption("Llamada a procedimiento local", false)
                .build();

        assertNotNull(question);
        assertEquals(4, question.getOptions().size());
        assertEquals("DRAFT", question.getStatus());
    }

    @Test
    void shouldFailBuildWhenNotExactlyFourOptions() {
        IQuestionBuilder builder = new QuestionBuilder()
                .withId(2L)
                .withContext("Contexto de prueba")
                .withStatement("Enunciado de prueba")
                .withJustification("Justificación válida con más de quince caracteres.")
                .addOption("Opción A", true)
                .addOption("Opción B", false)
                .addOption("Opción C", false);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, builder::build);
        assertTrue(ex.getMessage().contains("4"));
    }

    @Test
    void shouldFailBuildWhenZeroOrMultipleCorrectAnswers() {
        IQuestionBuilder builder = new QuestionBuilder()
                .withId(3L)
                .withContext("Contexto de prueba")
                .withStatement("Enunciado de prueba")
                .withJustification("Justificación válida con más de quince caracteres.")
                .addOption("Opción A", true)
                .addOption("Opción B", true)
                .addOption("Opción C", false)
                .addOption("Opción D", false);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, builder::build);
        assertTrue(ex.getMessage().contains("1 correct answer"));
    }

    @Test
    void shouldTransitionFromDraftToPendingReviewWithoutConditionals() {
        Question question = new QuestionBuilder()
                .withId(4L)
                .withContext("Contexto de prueba")
                .withStatement("Enunciado de prueba")
                .withJustification("Justificación válida con más de quince caracteres.")
                .addOption("Opción A", true)
                .addOption("Opción B", false)
                .addOption("Opción C", false)
                .addOption("Opción D", false)
                .build();

        assertEquals("DRAFT", question.getStatus());

        // Transition DRAFT -> PENDING_REVIEW via State Pattern
        question.submitForReview();
        assertEquals("PENDING_REVIEW", question.getStatus());

        // Attempting to submit again when already in PENDING_REVIEW throws IllegalStateException
        assertThrows(IllegalStateException.class, question::submitForReview);
    }
}
