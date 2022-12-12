package api.demo.records.exception;

/**
 * This exception is thrown when a field value in a record is null.
 */

public class RecordsFieldValueIsNullException extends RuntimeException {

    /**
     * Constructs a new RecordsFieldValueIsNullException with the specified message.
     *
     * @param message the error message.
     */
    public RecordsFieldValueIsNullException(String message) {
        super(message);
    }

    /**
     * Constructs a new RecordsFieldValueIsNullException with the specified message and cause.
     *
     * @param message the error message.
     * @param cause the cause of the exception.
     */
    public RecordsFieldValueIsNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
