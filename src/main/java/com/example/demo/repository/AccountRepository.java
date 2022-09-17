package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Account;



/**
 * ユーザー情報 Repository
 */
@Repository

public interface AccountRepository extends JpaRepository<Account, Long> {

}
