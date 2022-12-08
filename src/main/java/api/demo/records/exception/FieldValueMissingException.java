package api.demo.records.exception;

public class FieldValueMissingException extends RuntimeException {

    public FieldValueMissingException(String message) {
        super(message);
    }

    public FieldValueMissingException(String message, Throwable cause) {
        super(message, cause);
    }
}
