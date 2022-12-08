package api.demo.records.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RecordsExceptionHandler {

    public ResponseEntity<Object> handleRecordsFieldValueMissingException
            (FieldValueMissingException fieldValueMissingException) {
        RecordsException recordsException = new RecordsException(
                fieldValueMissingException.getMessage(),
                fieldValueMissingException.getCause(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(recordsException, HttpStatus.BAD_REQUEST);
    }

}
