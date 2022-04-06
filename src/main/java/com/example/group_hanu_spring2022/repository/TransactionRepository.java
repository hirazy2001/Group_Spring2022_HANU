package com.example.group_hanu_spring2022.repository;

import com.example.group_hanu_spring2022.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("select t from Transaction t where t.fromAccount.id = :id OR t.toAccount.id = :id")
    public List<Transaction> getTransactionRelation(@Param("id") Long id);
}
