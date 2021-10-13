package com.haulmont.testtask.controller;

import com.haulmont.testtask.model.Bank;
import com.haulmont.testtask.model.Client;
import com.haulmont.testtask.repository.BankRepository;
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
    private BankRepository bankRepository;

    @GetMapping("/banks")
    public String getBanks(Model model) {
        List<Bank> banks = new ArrayList<>();
        banks.addAll(bankRepository.findAll());
        model.addAttribute("banks", banks);
        model.addAttribute("bank", new Bank());
        return "banks";
    }
    @PostMapping("/editBank")
    public String addBank (@ModelAttribute("bank") Bank bank)
    {
        bankRepository.save(bank);
        return "redirect:/banks";
    }
    @GetMapping("/editBank")
    public String addClient(Model model) {

        model.addAttribute("bank", new Bank());
        return "editBank";
    }
    @GetMapping("/deleteBank/{id}")
    public String deleteClient(@PathVariable Long id) {
        bankRepository.deleteById(id);
        return "redirect:/banks";
    }
    @GetMapping("/editBank/{id}")
    public String getClient(@PathVariable Long id, Model model) {

        Bank bank= bankRepository.findById(id).orElse(new Bank());//TODO Сделать тут ElseThrow и выкидыватьк какую-нибудь ошибку, если объект null
        model.addAttribute("bank", bank);
        return "editBank";
    }
}
