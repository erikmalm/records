package api.demo.records.exception;

public class RecordsFieldValueMissingException extends RuntimeException {

    public RecordsFieldValueMissingException(String message) {
        super(message);
    }

    public RecordsFieldValueMissingException(String message, Throwable cause) {
        super(message, cause);
    }
}
