package api.demo.records.exception;

/**
 * This exception is thrown when an employee email already exists in the records system.
 *
 */

public class RecordsEmailAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new RecordsEmailAlreadyExistsException with the specified error message.
     *
     * @param message the error message
     */

    public RecordsEmailAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructs a new RecordsEmailAlreadyExistsException with the specified error message and cause.
     *
     * @param message the error message.
     * @param cause the cause of the exception.
     */

    public RecordsEmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
