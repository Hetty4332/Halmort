package com.haulmont.testtask.controller;

import com.haulmont.testtask.model.Bank;
import com.haulmont.testtask.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BankController {

    @Autowired
    private BankRepository bankRepository;

    @GetMapping("/banks")
    public String getBanks(Model model) {
        List<Bank> banks = new ArrayList<>();
        banks.addAll(bankRepository.findAll());
        model.addAttribute("banks", banks);
        model.addAttribute("bank", new Bank());
        return "banks";
    }
    @PostMapping("/banks")
    public String addBank (@ModelAttribute("bank") Bank bank)
    {
        bankRepository.save(bank);
        return "banks";
    }
/*    @PostMapping("/banks")
    public String deleteBank (@ModelAttribute("bank") Bank bank)
    {
        bankRepository.delete(bank);
        return "banks";
    }*/

}
