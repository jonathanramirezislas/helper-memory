package jonas.com.helpermemory.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import jonas.com.helpermemory.models.responses.ErrorMessage;


@ControllerAdvice
public class AppExceptionHandler {

    //EXPECTION WHEN A EMAIL EXIST IN THE DATABASAE
    @ExceptionHandler(value = { EmailExistsException.class })
    public ResponseEntity<Object> handleEmailExistsException(EmailExistsException ex, WebRequest webRequest) {
        //Create a new object of errorMessage
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        //Return the response that we will show to the client  //object, headers, status 500
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

//The rest of expection that we dont handle will be cacth here 
    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleException(Exception ex, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
