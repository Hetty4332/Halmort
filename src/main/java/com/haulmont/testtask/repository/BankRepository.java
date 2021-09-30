package com.haulmont.testtask.repository;


import com.haulmont.testtask.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}
