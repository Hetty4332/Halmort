package com.haulmont.testtask.controller;

import com.haulmont.testtask.model.Client;
import com.haulmont.testtask.model.CreditOffer;
import com.haulmont.testtask.model.Payment;
import com.haulmont.testtask.repository.ClientRepository;
import com.haulmont.testtask.repository.CreditOfferRepository;
import com.haulmont.testtask.repository.CreditRepository;
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
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CreditRepository creditRepository;


    @GetMapping("/creditOffer")
    public String getCreditOffer(Model model) {
        List<CreditOffer> creditOffers = new ArrayList<>();
        creditOffers.addAll(creditOfferRepository.findAll());
        model.addAttribute("creditOffers", creditOffers);
        model.addAttribute("creditOffer", new CreditOffer());
        model.addAttribute("payment", new Payment());
        model.addAttribute("clients", clientRepository.findAll());
        model.addAttribute("credits", creditRepository.findAll());
        return "creditOffer";
    }

    @PostMapping("/creditOffer")
    public String addCreditOffer(@ModelAttribute("creditOffer") CreditOffer creditOffer, @ModelAttribute("time") int time) {
        //надо вычислить остаток.
        //time- срок кредита
        List<Payment> chartOfPayments = new ArrayList<>();
        int remainder = creditOffer.getSumCredit();
        for (int i = 0; i < time; i++) {
            Payment payment = new Payment();
            payment.setPaymentDate(LocalDate.now().plusMonths(i));
            if (i > 0) {
                remainder = creditOffer.getSumCredit() - chartOfPayments.get(i - 1).getPaymentSum();
            }
            payment.setPaymentSum(creditOfferService.amountOfMonthlyPayment(time, remainder,creditOffer.getCredit()));
            payment.setCreditBodyRepayment(creditOfferService.deptPaart(time,creditOffer.getCredit()));
            payment.setAmountOfInterestRepayment(creditOfferService.percent(remainder,creditOffer.getCredit()));
            chartOfPayments.add(payment);
        }
        creditOffer.setChartOfPayments(chartOfPayments);
        creditOfferRepository.save(creditOffer);
        return "redirect:/creditOffer";
    }
}
