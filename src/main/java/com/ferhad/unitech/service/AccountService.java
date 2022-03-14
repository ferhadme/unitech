package com.ferhad.unitech.service;

import com.ferhad.unitech.dto.AccountGetDto;
import com.ferhad.unitech.dto.TransferDto;

import java.util.List;

public interface AccountService {
    void makeTransfer(TransferDto transferDto);
    List<AccountGetDto> getAccounts();
}
