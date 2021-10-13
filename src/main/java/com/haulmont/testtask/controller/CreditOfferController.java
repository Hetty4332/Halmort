package com.haulmont.testtask.controller;

import com.haulmont.testtask.dto.CreditOfferRequest;
import com.haulmont.testtask.model.Client;
import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.model.CreditOffer;
import com.haulmont.testtask.model.Payment;
import com.haulmont.testtask.repository.ClientRepository;
import com.haulmont.testtask.repository.CreditOfferRepository;
import com.haulmont.testtask.repository.CreditRepository;
import com.haulmont.testtask.repository.PaymentRepository;
import com.haulmont.testtask.service.CreditOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/payments/{id}")
    public String getPayments(Model model, @PathVariable("id") Long id) {
        model.addAttribute("payments", creditOfferRepository.findById(id).get().getChartOfPayments());

        return "payments";
    }

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
        double remainder = creditOffer.getSumCredit();

        CreditOffer saveCreditOffer = new CreditOffer();
        saveCreditOffer.setChartOfPayments(chartOfPayments);
        saveCreditOffer.setCredit(credit);
        saveCreditOffer.setClient(client);

        for (int i = 0; i < time; i++) {
            if (i > 0) {
                remainder = creditOffer.getSumCredit() - chartOfPayments.get(i - 1).getPaymentSum();
            }
            Payment payment = new Payment();
            payment.setPaymentDate(LocalDate.now().plusMonths(i));
            payment.setPaymentSum(creditOfferService.amountOfMonthlyPayment(time, remainder,creditOffer.getSumCredit(),credit.getInterestRate() ));
            payment.setCreditBodyRepayment(creditOfferService.deptPaart(time,creditOffer.getSumCredit()));
            payment.setAmountOfInterestRepayment(creditOfferService.percent(remainder,credit.getInterestRate() ));
            chartOfPayments.add(payment);
            paymentRepository.save(payment);
        }
        CreditOffer save = creditOfferRepository.save(saveCreditOffer);
        return "redirect:/payments/"+save.getId();
    }

}
