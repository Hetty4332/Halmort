package com.haulmont.testtask.controller;

import com.haulmont.testtask.model.Bank;
import com.haulmont.testtask.model.Client;
import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.repository.BankRepository;
import com.haulmont.testtask.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class CreditController {
    @Autowired
    private CreditRepository creditRepository;
    @Autowired
    private BankRepository bankRepository;

    @GetMapping("/credits")
    public String getCredits(Model model) {
        List<Credit> credits = new ArrayList<>();
        credits.addAll(creditRepository.findAll());
        model.addAttribute("credits", credits);
        model.addAttribute("credit", new Credit());


        return "credits";
    }

    @PostMapping("/editCredit")
    public String addCredit(@ModelAttribute("credit") Credit credit) {
        creditRepository.save(credit);
        return "redirect:/credits";
    }
    @GetMapping("/deleteCredit/{id}")
    public String deleteCredit(@PathVariable Long id) {
        creditRepository.deleteById(id);
        return "redirect:/credits";
    }
    @GetMapping("/editCredit/{id}")
    public String getCredit(@PathVariable Long id, Model model) {

        Credit credit= creditRepository.findById(id).orElse(new Credit());//TODO Сделать тут ElseThrow и выкидыватьк какую-нибудь ошибку, если объект null
        model.addAttribute("credit", credit);
        return "editCredit";
    }
    @GetMapping("/editCredit")
    public String addCredit(Model model) {
        model.addAttribute("banks", bankRepository.findAll() );
        model.addAttribute("credit", new Credit());
        return "editCredit";
    }


    @PostConstruct
    public void init(){
        Bank bank = new Bank();
        bank.setName("SBER");
        bank = bankRepository.save(bank);

        Credit credit = new Credit();
        credit.setCreditLimit(1000000);
        credit.setInterestRate(10);
        bank.setCredits(Collections.singletonList(credit));
        bank = bankRepository.save(bank);
        creditRepository.save(credit);
        Credit credit2 = new Credit();
        credit2.setCreditLimit(2000000);
        credit2.setInterestRate(12);
        bank.getCredits().add(credit2);
        bankRepository.save(bank);
        creditRepository.save(credit2);


        Bank bank2 = new Bank();
        bank2.setName("VTB");
        bank2 = bankRepository.save(bank2);

        Credit credit3 = new Credit();
        credit3.setCreditLimit(2000000);
        credit3.setInterestRate(20);
        bank2.setCredits(Collections.singletonList(credit3));
        bank2 = bankRepository.save(bank2);
        creditRepository.save(credit3);
        Credit credit4 = new Credit();
        credit4.setCreditLimit(4000000);
        credit4.setInterestRate(14);
        bank2.getCredits().add(credit4);
        bankRepository.save(bank2);
        creditRepository.save(credit4);
    }
}

