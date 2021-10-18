package com.haulmont.testtask.repository;


import com.haulmont.testtask.model.Bank;
import com.haulmont.testtask.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BankRepository extends JpaRepository<Bank, Long> {
    Bank findBankByName(String name);
    @Query(value = "select id from bank inner join bank_credits on bank_credits.credits_id=:credit_id and bank_credits.bank_id=bank.id",nativeQuery = true)
    Long findIdBankByCredits_id(@Param("credit_id") Long creditId);


}
