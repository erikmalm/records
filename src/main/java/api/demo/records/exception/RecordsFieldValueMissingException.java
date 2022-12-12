package api.demo.records.exception;

/**
 * Exception thrown when a required field value is missing.
 */
public class RecordsFieldValueMissingException extends RuntimeException {

    /**
     * Constructs a new records field value missing exception with the specified detail message.
     *
     * @param message the error message
     */
    public RecordsFieldValueMissingException(String message) {
        super(message);
    }

    /**
     * Constructs a new records field value missing exception with the specified detail message and cause.
     *
     * @param message the error message
     * @param cause the cause of this exception
     */
    public RecordsFieldValueMissingException(String message, Throwable cause) {
        super(message, cause);
    }
}
