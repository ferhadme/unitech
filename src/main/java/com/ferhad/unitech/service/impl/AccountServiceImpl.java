package com.ferhad.unitech.service.impl;

import com.ferhad.unitech.dto.AccountGetDto;
import com.ferhad.unitech.dto.TransferDto;
import com.ferhad.unitech.exception.BadCredentials;
import com.ferhad.unitech.exception.NotEnoughMoney;
import com.ferhad.unitech.exception.TransferFromDeactiveAccount;
import com.ferhad.unitech.exception.TransferToDeactiveAccount;
import com.ferhad.unitech.exception.TransferToNonExistentAccount;
import com.ferhad.unitech.model.Account;
import com.ferhad.unitech.model.User;
import com.ferhad.unitech.repository.AccountRepository;
import com.ferhad.unitech.repository.UserRepository;
import com.ferhad.unitech.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    @Override
    public void makeTransfer(Principal principal, TransferDto transferDto) {
        Account accountFrom = accountRepository.getAccountByAccountNumber(transferDto.getAccountNumberFrom())
                .orElseThrow();
        User user = userRepository.findByPin(principal.getName())
                .orElseThrow();

        if (!user.getAccounts().contains(accountFrom)) {
            throw new BadCredentials("Account isn't owned by User with PIN " + principal.getName());
        }
        if (transferDto.getAmount().compareTo(accountFrom.getAmount()) > 0) {
            throw new NotEnoughMoney();
        }
        if (!accountFrom.getActive()) {
            throw new TransferFromDeactiveAccount();
        }

        Account accountTo = accountRepository.getAccountByAccountNumber(transferDto.getAccountNumberTo())
                .orElseThrow(TransferToNonExistentAccount::new);

        if (!accountTo.getActive()) {
            throw new TransferToDeactiveAccount();
        }

        transaction(accountFrom, accountTo, transferDto.getAmount());
    }

    @Transactional
    protected void transaction(Account accountFrom, Account accountTo, BigDecimal amount) {
        accountFrom.setAmount(accountFrom.getAmount().subtract(amount));
        accountRepository.save(accountFrom);
        accountTo.setAmount(accountTo.getAmount().add(amount));
        accountRepository.save(accountTo);
    }

    @Override
    public List<AccountGetDto> getAccounts(Principal principal) {
        User user = userRepository.findByPin(principal.getName())
                .orElseThrow();
        List<Account> accounts = accountRepository.getAccountsOfUserByActive(user);

        return accounts.stream()
                .map(entity -> modelMapper.map(entity, AccountGetDto.class))
                .collect(Collectors.toList());
    }
    
}
