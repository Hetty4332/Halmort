package com.haulmont.testtask.service;

import com.haulmont.testtask.model.Bank;
import com.haulmont.testtask.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
    @Autowired
    BankRepository bankRepository;

    public List<Bank> getBanks() {
        return bankRepository.findAll();
    }

    public void saveBank(Bank bank) {
        bankRepository.save(bank);
    }

    public void deleteBankById(Long id) {
        bankRepository.deleteById(id);
    }

    public Bank getBankById(Long id) {
        return bankRepository.findById(id).orElse(new Bank());//TODO Сделать тут ElseThrow и выкидыватьк какую-нибудь ошибку, если объект null;
    }

}
