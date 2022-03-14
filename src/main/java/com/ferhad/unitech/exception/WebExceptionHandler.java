package com.ferhad.unitech.exception;

import com.ferhad.unitech.payload.response.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class WebExceptionHandler {

    @ExceptionHandler(BadCredentials.class)
    protected ResponseEntity<MessageResponse> handle(BadCredentials ex) {
        log.error("Error happened: {}", ex.getMessage());
        MessageResponse messageResponse = new MessageResponse("Wrong credentials for login");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageResponse);
    }

    @ExceptionHandler(DuplicatePinException.class)
    protected ResponseEntity<MessageResponse> handle(DuplicatePinException ex) {
        log.error("Error happened: {}", ex.getMessage());
        MessageResponse messageResponse = new MessageResponse("Registering with already registered PIN");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageResponse);
    }

    @ExceptionHandler(NotEnoughMoney.class)
    protected ResponseEntity<MessageResponse> handle(NotEnoughMoney ex) {
        log.error("Error happened: {}", ex.getMessage());
        MessageResponse messageResponse = new MessageResponse("No enough money in account for transaction");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageResponse);
    }

    @ExceptionHandler(TransferToDeactiveAccount.class)
    protected ResponseEntity<MessageResponse> handle(TransferToDeactiveAccount ex) {
        log.error("Error happened: {}", ex.getMessage());
        MessageResponse messageResponse = new MessageResponse("Transfer to deactivated account");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageResponse);
    }

    @ExceptionHandler(TransferToNonExistentAccount.class)
    protected ResponseEntity<MessageResponse> handle(TransferToNonExistentAccount ex) {
        log.error("Error happened: {}", ex.getMessage());
        MessageResponse messageResponse = new MessageResponse("Transfer to non existent account");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageResponse);
    }

    @ExceptionHandler(TransferToSameAccount.class)
    protected ResponseEntity<MessageResponse> handle(TransferToSameAccount ex) {
        log.error("Error happened: {}", ex.getMessage());
        MessageResponse messageResponse = new MessageResponse("Transfer to the same account");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<MessageResponse> handle(MethodArgumentNotValidException ex) {
        log.error("Error happened: {}", ex.getMessage());
        MessageResponse messageResponse = new MessageResponse("Field errors");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageResponse);
    }

}
