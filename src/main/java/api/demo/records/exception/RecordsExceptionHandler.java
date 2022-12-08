package api.demo.records.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RecordsExceptionHandler {

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

}
