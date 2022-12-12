package api.demo.records.exception;

/**
 * This exception is thrown when an employee record is not found in the records system.
 *
 */
public class RecordsEmployeeNotFoundException extends IllegalArgumentException {

    /**
     * Constructs a new RecordsEmployeeNotFoundException with the specified error message.
     *
     * @param message the error message.
     */
    public RecordsEmployeeNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new RecordsEmployeeNotFoundException with the specified error message and cause.
     *
     * @param message the error message.
     * @param cause the cause of the exception.
     */
    public RecordsEmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
