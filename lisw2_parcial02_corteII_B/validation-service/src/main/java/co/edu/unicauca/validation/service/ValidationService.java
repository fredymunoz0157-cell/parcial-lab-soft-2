package co.edu.unicauca.validation.service;

import co.edu.unicauca.validation.dto.ValidationRequestDTO;
import co.edu.unicauca.validation.dto.ValidationResultDTO;
import org.springframework.stereotype.Service;


@Service
public class ValidationService {

    private static final int MIN_JUSTIFICATION_LENGTH = 15;

    
    public ValidationResultDTO validate(ValidationRequestDTO request) {
        if (request == null || request.getJustification() == null) {
            return new ValidationResultDTO(false, "Justification is required.");
        }
        String trimmedJustification = request.getJustification().trim();
        if (trimmedJustification.length() <= MIN_JUSTIFICATION_LENGTH) {
            return new ValidationResultDTO(
                    false,
                    "Invalid justification: length must be strictly greater than 15 characters."
            );
        }
        return new ValidationResultDTO(true, "Question justification is valid.");
    }
}
