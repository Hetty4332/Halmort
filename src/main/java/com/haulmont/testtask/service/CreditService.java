package com.haulmont.testtask.service;

import com.haulmont.testtask.model.Client;
import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.repository.ClientRepository;
import com.haulmont.testtask.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {
    @Autowired
    CreditRepository creditRepository;

    public List<Credit> getCredits() {
        return creditRepository.findAll();
    }
    public void saveCredit(Credit credit) {
        creditRepository.save(credit);
    }
    public void deleteCreditById(Long id) {
        creditRepository.deleteById(id);
    }
    public Credit getCreditById(Long id) {
        return creditRepository.findById(id).orElse(new Credit());//TODO Сделать тут ElseThrow и выкидыватьк какую-нибудь ошибку, если объект null;
    }
}
