package com.haulmont.testtask.controller;

import com.haulmont.testtask.model.CreditOffer;
import com.haulmont.testtask.repository.CreditOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CreditOfferController {
    @Autowired
    private CreditOfferRepository creditOfferRepository;

    @GetMapping("/creditOffers")
    public String getCreditOffer(Model model) {
        List<CreditOffer> creditOffers = new ArrayList<>();
        creditOffers.addAll(creditOfferRepository.findAll());
        model.addAttribute("creditOffers", creditOffers);
        model.addAttribute("creditOffer", new CreditOffer());
        return "creditOffers";
    }

    @PostMapping("/creditOffers")
    public String addCreditOffer(@ModelAttribute("creditOffer") CreditOffer creditOffer) {
        creditOfferRepository.save(creditOffer);
        return "creditOffers";
    }
}
