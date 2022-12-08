package api.demo.records.exception;

public class RecordsFieldValueIsNullException extends RuntimeException {

    public RecordsFieldValueIsNullException(String message) {
        super(message);
    }

    public RecordsFieldValueIsNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
