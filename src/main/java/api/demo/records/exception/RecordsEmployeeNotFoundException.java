package api.demo.records.exception;

public class RecordsEmployeeNotFoundException extends IllegalArgumentException {

    public RecordsEmployeeNotFoundException(String message) {
        super(message);
    }

    public RecordsEmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
