package co.edu.unicauca.question.controller;

import co.edu.unicauca.question.dto.CreateQuestionRequestDTO;
import co.edu.unicauca.question.dto.QuestionResponseDTO;
import co.edu.unicauca.question.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * REST Controller for Question Service (:8083)
 */
@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    
    @PostMapping
    public ResponseEntity<QuestionResponseDTO> createQuestion(@RequestBody CreateQuestionRequestDTO request) {
        QuestionResponseDTO created = questionService.createQuestion(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

   
    @PostMapping("/{id}/submit")
    public ResponseEntity<Map<String, Object>> submitForReview(@PathVariable("id") Long questionId) {
        String operationId = questionService.submitQuestionForReview(questionId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Map.of(
                "message", "Solicitud de envío a revisión aceptada para procesamiento.",
                "questionId", questionId,
                "operationId", operationId,
                "status", "PROCESSING"
        ));
    }

   
    @PostMapping("/{id}/transition-pending-review")
    public ResponseEntity<QuestionResponseDTO> applyPendingReviewTransition(@PathVariable("id") Long questionId) {
        QuestionResponseDTO updated = questionService.transitionToPendingReview(questionId);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponseDTO> getById(@PathVariable("id") Long questionId) {
        return questionService.findById(questionId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<QuestionResponseDTO>> listAll() {
        return ResponseEntity.ok(questionService.findAll());
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Map<String, Object>> handleBadRequest(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "valid", false,
                "error", ex.getMessage()
        ));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "error", ex.getMessage()
        ));
    }
}
