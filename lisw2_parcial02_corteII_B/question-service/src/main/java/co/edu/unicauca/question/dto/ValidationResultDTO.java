package co.edu.unicauca.question.dto;

/**
 * Response received from Validation Service (:8082) at POST /validate.
 */
public class ValidationResultDTO {

    private boolean valid;
    private String message;

    public ValidationResultDTO() {
    }

    public ValidationResultDTO(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
