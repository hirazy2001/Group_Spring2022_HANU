package com.example.group_hanu_spring2022.repository;

import com.example.group_hanu_spring2022.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Account a set a.balance = ?2 where a.id = ?1")
    void saveBalanceByAcctID(Long accountID, BigDecimal balance);

    @Query("select a from Account a where a.uId.id = ?1")
    List<Account> findAccountByUId(@Param("uId") Long uId);


}
