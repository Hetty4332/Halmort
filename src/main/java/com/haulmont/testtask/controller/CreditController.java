package com.haulmont.testtask.controller;

import com.haulmont.testtask.model.Bank;
import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.repository.BankRepository;
import com.haulmont.testtask.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CreditController {
    @Autowired
    private CreditRepository creditRepository;

    @GetMapping("/credits")
    public String getBanks(Model model) {
        List<Credit> credits = new ArrayList<>();
        credits.addAll(creditRepository.findAll());
        model.addAttribute("credits", credits);
        model.addAttribute("credit", new Credit());
        return "credits";
    }

    @PostMapping("/credits")
    public String addBank(@ModelAttribute("bank") Credit credit) {
        creditRepository.save(credit);
        return "credits";
    }

    private int amountPercent()
    {

        return 1;
    }
    private int amountOfMonthlyPayment(Credit credit)
    {
        //(credit.getCreditOffer().getSumCredit()/ +credit.getInterestRate()
        return 2;
    }
}
