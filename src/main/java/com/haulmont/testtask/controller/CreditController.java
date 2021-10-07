package com.haulmont.testtask.controller;

import com.haulmont.testtask.model.Client;
import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.repository.CreditRepository;
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
    private CreditRepository creditRepository;

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

        model.addAttribute("credit", new Credit());
        return "editCredit";
    }
}

