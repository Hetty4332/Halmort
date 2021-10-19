package com.haulmont.testtask.controller;

import com.haulmont.testtask.dto.CreditWeb;
import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.service.BankService;
import com.haulmont.testtask.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CreditController {
    @Autowired
    private CreditService creditService;
    @Autowired
    private BankService bankService;


    @GetMapping("/credits")
    public String getCredits(Model model) {

        model.addAttribute("credits", creditService.getCredits());
        return "credits";
    }

    @PostMapping("/editCredit")
    public String addCredit(@ModelAttribute("credit") @Valid CreditWeb credit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/editCredit";
        }
        creditService.saveCredit(credit);
        return "redirect:/credits";
    }

    @GetMapping("/deleteCredit/{id}")
    public String deleteCredit(@PathVariable Long id) {
        creditService.deleteCreditById(id);
        return "redirect:/credits";
    }

    @GetMapping("/editCredit/{id}")
    public String getCredit(@PathVariable Long id, Model model) {
        model.addAttribute("banks", bankService.getBanks());
        model.addAttribute("credit", creditService.getCreditById(id));
        return "editCredit";
    }

    @GetMapping("/editCredit")
    public String addCredit(Model model) {
        model.addAttribute("banks", bankService.getBanks());
        model.addAttribute("credit", new CreditWeb());
        return "editCredit";
    }


    /*@PostConstruct
    public void init() {
        Bank bank = new Bank();
        bank.setName("SBER");
        bankService.saveBank(bank);

        Credit credit = new Credit();
        credit.setCreditLimit(1000000);
        credit.setInterestRate(10);
        bank.setCredits(Collections.singletonList(credit));
        bankService.saveBank(bank);
        creditService.saveCredit(credit);
        Credit credit2 = new Credit();
        credit2.setCreditLimit(2000000);
        credit2.setInterestRate(12);
        bank.getCredits().add(credit2);
        bankService.saveBank(bank);
        creditService.saveCredit(credit2);


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
    }*/
}

