package com.haulmont.testtask.service;

import com.haulmont.testtask.model.Bank;
import com.haulmont.testtask.repository.BankRepository;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
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

    /***
     * fix lazy hibernateLazyInitializer  :(
     * @return
     */
    public List<Bank> getBanksHibernateFix() {
        List<Bank> all = bankRepository.findAll();
//        all.forEach(bank -> {
//            bank.getCredits().forEach(BankService::initializeAndUnproxy);
//            bank.getClients().forEach(BankService::initializeAndUnproxy);
//        });
        return all;
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

    public static <T> T initializeAndUnproxy(T entity) {
        if (entity == null) {
            throw new
                    NullPointerException("Entity passed for initialization is null");
        }

        Hibernate.initialize(entity);
        if (entity instanceof HibernateProxy) {
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
                    .getImplementation();
        }
        return entity;
    }
}
