package com.haulmont.testtask.controller;

import com.haulmont.testtask.model.Client;
import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.repository.BankRepository;
import com.haulmont.testtask.repository.CreditRepository;
import com.haulmont.testtask.service.BankService;
import com.haulmont.testtask.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CreditController {
    @Autowired
    private CreditService creditService;
    @Autowired
    private BankService bankService;


    @GetMapping("/credits")
    public String getCredits(Model model) {
        List<Credit> credits = new ArrayList<>();
        credits.addAll(creditService.getCredits());
        model.addAttribute("credits", credits);
        model.addAttribute("credit", new Credit());


        return "credits";
    }

    @PostMapping("/editCredit")
    public String addCredit(@ModelAttribute("credit") Credit credit) {
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

        model.addAttribute("credit", creditService.getCreditById(id));
        return "editCredit";
    }
    @GetMapping("/editCredit")
    public String addCredit(Model model) {
        model.addAttribute("banks", bankService.getBanks());
        model.addAttribute("credit", new Credit());
        return "editCredit";
    }


/*    @PostConstruct
    public void init(){
        Credit credit = new Credit();
        credit.setCreditLimit(1000000);
        credit.setInterestRate(10);
        creditRepository.save(credit);
    }*/
}

