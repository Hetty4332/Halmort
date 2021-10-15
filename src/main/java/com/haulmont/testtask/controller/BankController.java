package com.haulmont.testtask.controller;

import com.haulmont.testtask.model.Bank;
import com.haulmont.testtask.model.Client;
import com.haulmont.testtask.repository.BankRepository;
import com.haulmont.testtask.service.BankService;
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
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping("/banks")
    public String getBanks(Model model) {
        List<Bank> banks = new ArrayList<>();
        banks.addAll(bankService.getBanks());
        model.addAttribute("banks", banks);
        model.addAttribute("bank", new Bank());
        return "banks";
    }

    @PostMapping("/editBank")
    public String addBank(@ModelAttribute("bank") Bank bank) {
        bankService.saveBank(bank);
        return "redirect:/banks";
    }

    @GetMapping("/editBank")
    public String addBank (Model model) {
        model.addAttribute("bank", new Bank());
        return "editBank";
    }

    @GetMapping("/deleteBank/{id}")
    public String deleteBank (@PathVariable Long id) {
        bankService.deleteBankById(id);
        return "redirect:/banks";
    }

    @GetMapping("/editBank/{id}")
    public String getBank (@PathVariable Long id, Model model) {
        model.addAttribute("bank", bankService.getBankById(id));
        return "editBank";
    }
}
