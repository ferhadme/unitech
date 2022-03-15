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
        MessageResponse messageResponse = new MessageResponse("Wrong credentials for login");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageResponse);
    }

    @ExceptionHandler(DuplicatePinException.class)
    protected ResponseEntity<MessageResponse> handle(DuplicatePinException ex) {
        MessageResponse messageResponse = new MessageResponse("Registering with already registered PIN");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageResponse);
    }

    @ExceptionHandler(NotEnoughMoney.class)
    protected ResponseEntity<MessageResponse> handle(NotEnoughMoney ex) {
        MessageResponse messageResponse = new MessageResponse("No enough money in account for transaction");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageResponse);
    }

    @ExceptionHandler(TransferFromDeactiveAccount.class)
    protected ResponseEntity<MessageResponse> handle(TransferFromDeactiveAccount ex) {
        MessageResponse messageResponse = new MessageResponse("Transfer from deactivated account");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageResponse);
    }

    @ExceptionHandler(TransferToDeactiveAccount.class)
    protected ResponseEntity<MessageResponse> handle(TransferToDeactiveAccount ex) {
        MessageResponse messageResponse = new MessageResponse("Transfer to deactivated account");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageResponse);
    }

    @ExceptionHandler(TransferToNonExistentAccount.class)
    protected ResponseEntity<MessageResponse> handle(TransferToNonExistentAccount ex) {
        MessageResponse messageResponse = new MessageResponse("Transfer to non existent account");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageResponse);
    }

    @ExceptionHandler(TransferToSameAccount.class)
    protected ResponseEntity<MessageResponse> handle(TransferToSameAccount ex) {
        MessageResponse messageResponse = new MessageResponse("Transfer to the same account");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<MessageResponse> handle(MethodArgumentNotValidException ex) {
        MessageResponse messageResponse = new MessageResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageResponse);
    }

}
