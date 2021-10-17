package com.haulmont.testtask.controller;

import com.haulmont.testtask.dto.CreditOfferRequest;
import com.haulmont.testtask.model.Client;
import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.model.CreditOffer;
import com.haulmont.testtask.model.Payment;
import com.haulmont.testtask.repository.CreditRepository;
import com.haulmont.testtask.repository.PaymentRepository;
import com.haulmont.testtask.service.BankService;
import com.haulmont.testtask.service.ClientService;
import com.haulmont.testtask.service.CreditOfferService;
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
public class CreditOfferController {

    @Autowired
    private CreditOfferService creditOfferService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CreditRepository creditService;
    @Autowired
    private BankService bankService;

    @GetMapping("/payments/{id}")
    public String getPayments(Model model, @PathVariable("id") Long id) {
        model.addAttribute("payments", creditOfferService.getPaymentsByCreditOfferId(id));

        return "payments";
    }

    @GetMapping("/creditOffer")
    public String getCreditOffer(Model model) {
        model.addAttribute("creditOffer", new CreditOfferRequest());
        model.addAttribute("payment", new Payment());
        model.addAttribute("clients", clientService.getClients());
        model.addAttribute("banks", bankService.getBanks());
        return "creditOffer";
    }


    @PostMapping("/creditOffer")
    public String addCreditOffer(@ModelAttribute("creditOffer") @Valid CreditOfferRequest creditOffer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/creditOffer";
        }
        CreditOffer save = creditOfferService.saveCreditOffer(creditOffer);
        return "redirect:/payments/" + save.getId();
    }

}
