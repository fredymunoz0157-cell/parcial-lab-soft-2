package co.edu.unicauca.lifecycle.controller;

import co.edu.unicauca.lifecycle.service.LifecycleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/lifecycle")
public class LifecycleController {

    private final LifecycleService lifecycleService;

    public LifecycleController(LifecycleService lifecycleService) {
        this.lifecycleService = lifecycleService;
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Map<String, Object>> getLifecycleStatus(@PathVariable Long questionId) {
        return lifecycleService.findByQuestionId(questionId)
                .map(q -> ResponseEntity.ok(Map.<String, Object>of(
                        "questionId", q.getQuestionId(),
                        "status", q.getStatus(),
                        "operationId", q.getLastOperationId() != null ? q.getLastOperationId() : ""
                )))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
