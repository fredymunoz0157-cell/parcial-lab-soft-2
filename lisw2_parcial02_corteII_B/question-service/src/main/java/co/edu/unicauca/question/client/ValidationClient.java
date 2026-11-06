package co.edu.unicauca.question.client;

import co.edu.unicauca.question.dto.ValidationRequestDTO;
import co.edu.unicauca.question.dto.ValidationResultDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Component
public class ValidationClient {

    private final RestTemplate restTemplate;
    private final String validationUrl;

    public ValidationClient(
            RestTemplate restTemplate,
            @Value("${validation.service.url:http://localhost:8082/validate}") String validationUrl
    ) {
        this.restTemplate = restTemplate;
        this.validationUrl = validationUrl;
    }

    public ValidationResultDTO validate(ValidationRequestDTO request) {
        try {
            ResponseEntity<ValidationResultDTO> response = restTemplate.postForEntity(
                    validationUrl,
                    request,
                    ValidationResultDTO.class
            );
            return response.getBody() != null
                    ? response.getBody()
                    : new ValidationResultDTO(false, "Empty response from Validation Service.");
        } catch (HttpClientErrorException.BadRequest ex) {
            ValidationResultDTO body = ex.getResponseBodyAs(ValidationResultDTO.class);
            if (body != null) {
                return body;
            }
            return new ValidationResultDTO(false, "Validation rejected by Validation Service (400 Bad Request).");
        }
    }
}
