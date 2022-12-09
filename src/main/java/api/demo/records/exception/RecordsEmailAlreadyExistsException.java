package api.demo.records.exception;

public class RecordsEmailAlreadyExistsException extends RuntimeException {

    public RecordsEmailAlreadyExistsException(String message) {
        super(message);
    }

    public RecordsEmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
