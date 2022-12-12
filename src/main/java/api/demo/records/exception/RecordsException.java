package api.demo.records.exception;

import org.springframework.http.HttpStatus;

/**
 * A custom exception class for handling errors related to records operations.
 */

public class RecordsException extends RuntimeException {

    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;

    /**
     * Constructs a new `RecordsException` with the specified message, cause, and HTTP status.
     *
     * @param message the detail message
     * @param throwable the cause of the exception
     * @param httpStatus the HTTP status associated with this exception
     */

    public RecordsException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

    /**
     * Returns the detail message of this exception.
     *
     * @return the detail message of this exception
     */
    public String getMessage() {
        return message;
    }


    /**
     * Returns the cause of this exception.
     *
     * @return the cause of this exception
     */
    public Throwable getThrowable() {
        return throwable;
    }

    /**
     * Returns the HTTP status associated with this exception.
     *
     * @return the HTTP status associated with this exception
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
