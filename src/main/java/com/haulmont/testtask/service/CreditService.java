package com.haulmont.testtask.service;

import com.haulmont.testtask.dto.CreditWeb;
import com.haulmont.testtask.model.Bank;
import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.repository.BankRepository;
import com.haulmont.testtask.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreditService {
    @Autowired
    CreditRepository creditRepository;
    @Autowired
    BankService bankService;
    @Autowired
    BankRepository bankRepository;

    public List<CreditWeb> getCredits() {
        List<CreditWeb> creditWebs = new ArrayList<>();
        List<Credit> credits = creditRepository.findAll();
        for (int i = 0; i < credits.size(); i++) {
            CreditWeb creditWeb = new CreditWeb();
            creditWeb.setId(credits.get(i).getId());
            creditWeb.setInterestRate(credits.get(i).getInterestRate());
            creditWeb.setCreditLimit(credits.get(i).getCreditLimit());
            Long id = bankRepository.findIdBankByCredits_id(credits.get(i).getId());
            creditWeb.setBankName(bankRepository.findById(id).get().getName());
            creditWebs.add(creditWeb);
        }
        return creditWebs;
    }

    public void saveCredit(CreditWeb credit) {
        Credit saveCredit = new Credit();
        saveCredit.setId(credit.getId());
        saveCredit.setCreditLimit(credit.getCreditLimit());
        saveCredit.setInterestRate(credit.getInterestRate());
        saveCredit = creditRepository.save(saveCredit);
        Bank bank = bankService.getBankById(credit.getIdBank());
        bank.getCredits().add(saveCredit);
        bankService.saveBank(bank);

    }

    public void deleteCreditById(Long id) {
        creditRepository.deleteById(id);
    }

    public CreditWeb getCreditById(Long id) {
        Credit credit = creditRepository.findById(id).orElse(new Credit());//TODO Сделать тут ElseThrow и выкидыватьк какую-нибудь ошибку, если объект null;
        CreditWeb creditWeb = new CreditWeb();
        creditWeb.setCreditLimit(credit.getCreditLimit());
        creditWeb.setInterestRate(credit.getInterestRate());
        return creditWeb;
    }
}
