package com.ferhad.unitech.controller;

import com.ferhad.unitech.dto.AccountGetDto;
import com.ferhad.unitech.dto.TransferDto;
import com.ferhad.unitech.payload.response.MessageResponse;
import com.ferhad.unitech.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/transfer")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MessageResponse> makeTransfer(Principal principal, @RequestBody TransferDto transferDto) {
        accountService.makeTransfer(principal, transferDto);
        MessageResponse messageResponse = new MessageResponse("Transaction has been done successfully");
        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<AccountGetDto>> getAccounts(Principal principal) {
        return ResponseEntity.ok(
                accountService.getAccounts(principal)
        );
    }

}
