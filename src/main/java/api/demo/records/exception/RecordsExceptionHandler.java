package api.demo.records.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * A global exception handler class for handling exceptions thrown by records operations.
 */
@ControllerAdvice
public class RecordsExceptionHandler {

    /**
     * Handles the `RecordsFieldValueMissingException` exception and returns a response with the
     * corresponding HTTP status and error message.
     *
     * @param recordsFieldValueMissingException the `RecordsFieldValueMissingException` to handle
     * @return a response with the corresponding HTTP status and error message
     */

    @ExceptionHandler(value = {RecordsFieldValueMissingException.class})
    public ResponseEntity<Object> handleRecordsFieldValueMissingException
            (RecordsFieldValueMissingException recordsFieldValueMissingException) {
        RecordsException recordsException = new RecordsException(
                recordsFieldValueMissingException.getMessage(),
                recordsFieldValueMissingException.getCause(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(recordsException, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the `RecordsFieldValueIsNullException` exception and returns a response with the
     * corresponding HTTP status and error message.
     *
     * @param handleRecordsFieldValueIsNullException the `RecordsFieldValueIsNullException` to handle
     * @return a response with the corresponding HTTP status and error message
     */

    @ExceptionHandler(value = {RecordsFieldValueIsNullException.class})
    public ResponseEntity<Object> handleRecordsFieldValueIsNullException
            (RecordsFieldValueIsNullException handleRecordsFieldValueIsNullException) {
        RecordsException recordsException = new RecordsException(
                handleRecordsFieldValueIsNullException.getMessage(),
                handleRecordsFieldValueIsNullException.getCause(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(recordsException, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the RecordsEmailAlreadyExistsException by creating a RecordsException with the
     * message and cause of the original exception, and returning a response with the
     * HTTP status code BAD_REQUEST.
     *
     * @param recordsEmailAlreadyExistsException The exception to handle
     * @return A response containing the RecordsException and the HTTP status code BAD_REQUEST
     */

    @ExceptionHandler(value = {RecordsEmailAlreadyExistsException.class})
    public ResponseEntity<Object> handleRecordsEmailAlreadyExistsException
            (RecordsEmailAlreadyExistsException recordsEmailAlreadyExistsException) {
        RecordsException recordsException = new RecordsException(
                recordsEmailAlreadyExistsException.getMessage(),
                recordsEmailAlreadyExistsException.getCause(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(recordsException, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the RecordsEmployeeNotFoundException by creating a RecordsException with the
     * message and cause of the original exception, and returning a response with the
     * HTTP status code NOT_FOUND.
     *
     * @param recordsEmployeeNotFoundException The exception to handle
     * @return A response containing the RecordsException and the HTTP status code NOT_FOUND
     */
    @ExceptionHandler(value = {RecordsEmployeeNotFoundException.class})
    public ResponseEntity<Object> handleRecordsEmployeeNotFoundException
            (RecordsEmployeeNotFoundException recordsEmployeeNotFoundException) {
        RecordsException recordsException = new RecordsException(
                recordsEmployeeNotFoundException.getMessage(),
                recordsEmployeeNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(recordsException, HttpStatus.NOT_FOUND);
    }

}
