package com.haulmont.testtask.controller;

import com.haulmont.testtask.model.CreditOffer;
import com.haulmont.testtask.model.Payment;
import com.haulmont.testtask.repository.CreditOfferRepository;
import com.haulmont.testtask.service.CreditOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CreditOfferController {
    @Autowired
    private CreditOfferRepository creditOfferRepository;
    @Autowired
    private CreditOfferService creditOfferService;


    @GetMapping("/creditOffer")
    public String getCreditOffer(Model model) {
        List<CreditOffer> creditOffers = new ArrayList<>();
        creditOffers.addAll(creditOfferRepository.findAll());
        model.addAttribute("creditOffers", creditOffers);
        model.addAttribute("creditOffer", new CreditOffer());
        model.addAttribute("payment", new Payment());
        return "creditOffer";
    }

    @PostMapping("/creditOffer")
    public String addCreditOffer(@ModelAttribute("creditOffer") CreditOffer creditOffer, @ModelAttribute("time") int time) {
      //надо вычислить остаток. это сумма- 
       List<Payment> chartOfPayments = new ArrayList<>();
        for (int i = 0; i < time; i++) {
            Payment payment = new Payment();
            payment.setPaymentDate(LocalDate.now().plusMonths(i));
            payment.setPaymentSum(creditOfferService.amountOfMonthlyPayment(time,));
            chartOfPayments.add();
        }
        creditOffer.setChartOfPayments();
        creditOfferRepository.save(creditOffer);
        return "creditOffer";
    }
}
