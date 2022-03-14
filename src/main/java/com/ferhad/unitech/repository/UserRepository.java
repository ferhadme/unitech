package com.ferhad.unitech.repository;

import com.ferhad.unitech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u from User u WHERE u.pin = ?1")
    Optional<User> findByPin(String pin);

    @Query("SELECT COUNT(u) > 0 from User u WHERE u.pin = ?1")
    Boolean existsByPin(String pin);
}
