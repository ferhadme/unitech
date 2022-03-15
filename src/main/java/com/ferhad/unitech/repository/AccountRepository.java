package com.ferhad.unitech.repository;

import com.ferhad.unitech.model.Account;
import com.ferhad.unitech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.user = ?1 AND a.active = TRUE")
    List<Account> getAccountsOfUserByActive(User user);

    Optional<Account> getAccountByAccountNumber(String accountNumber);
}
