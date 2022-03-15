package com.ferhad.unitech.service;

import com.ferhad.unitech.dto.AccountGetDto;
import com.ferhad.unitech.dto.TransferDto;

import java.security.Principal;
import java.util.List;

public interface AccountService {
    void makeTransfer(Principal principal, TransferDto transferDto);
    List<AccountGetDto> getAccounts(Principal principal);
}
