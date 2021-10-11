package com.haulmont.testtask.controller;

import com.haulmont.testtask.dto.CreditOfferRequest;
import com.haulmont.testtask.model.Client;
import com.haulmont.testtask.model.Credit;
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
import java.util.Optional;

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
        model.addAttribute("creditOffer", new CreditOfferRequest());
        model.addAttribute("payment", new Payment());
        model.addAttribute("clients", clientRepository.findAll());
        model.addAttribute("credits", creditRepository.findAll());
        return "creditOffer";
    }

    @PostMapping("/creditOffer")
    public String addCreditOffer(@ModelAttribute("creditOffer") CreditOfferRequest creditOffer) {
        //надо вычислить остаток.
        //time- срок кредита

        Optional<Credit> creditOptional = creditRepository.findById(creditOffer.getCreditId());
        if (creditOptional.isEmpty()) {
            return "redirect:/creditOffer";
        }
        Optional<Client> clientOptional = clientRepository.findById(creditOffer.getClientId());
        if (creditOptional.isEmpty()) {
            return "redirect:/creditOffer";
        }

        Credit credit = creditOptional.get();
        Client client = clientOptional.get();
        List<Payment> chartOfPayments = new ArrayList<>();
        int time = creditOffer.getCountMonthCredit();
        int remainder = creditOffer.getSumCredit();
        for (int i = 0; i < time; i++) {
            if (i > 0) {
                remainder = creditOffer.getSumCredit() - chartOfPayments.get(i - 1).getPaymentSum();
            }
            Payment payment = new Payment();
            payment.setPaymentDate(LocalDate.now().plusMonths(i));

            payment.setPaymentSum(creditOfferService.amountOfMonthlyPayment(time, remainder, credit));
            payment.setCreditBodyRepayment(creditOfferService.deptPaart(time, credit));
            payment.setAmountOfInterestRepayment(creditOfferService.percent(remainder, credit));
            chartOfPayments.add(payment);
    }
        CreditOffer saveCreditOffer = new CreditOffer();
        saveCreditOffer.setChartOfPayments(chartOfPayments);
        saveCreditOffer.setCredit(credit);
        saveCreditOffer.setClient(client);
        creditOfferRepository.save(saveCreditOffer);
        return "redirect:/creditOffer";
    }
}
