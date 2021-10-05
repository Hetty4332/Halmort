package com.haulmont.testtask.service;

import com.haulmont.testtask.model.Credit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
public class CreditOfferService {


    // долговая часть ежемесячного платежа
     public int deptPaart(int creditTime, Credit credit) {
        int s = credit.getCreditOffer().getSumCredit();
        int n = creditTime;
        return s / n;

    }

    //проценты в ежемесячном платеже
    public int percent(int remainder, Credit credit) {
        int p = credit.getInterestRate();//её наверное надо будет на 12 поделить
        return remainder * p;
    }

    //сумма ежемесячного платежа
    public int amountOfMonthlyPayment(int creditTime, int remainder, Credit credit) {
        return deptPaart(creditTime, credit) + percent(remainder, credit);
    }
/*
    int p = credit.getInterestRate();
срок
    //рассчет доли процентов в ежемесячном взносе (по заданию формулировка "суммы ежемесячного платежа с учетом процентной
    //ставки". Надо выяснить)
    //s-должна быть остаточная сумма по займу
    private int amountPercent() {
        return s * p;
    }

    //рассчет суммы ежемесячного платежа с учетом процентной
    //ставки mountOfMonthlyPayment
    private int a(int creditTime) {
        int n = creditTime;//срок кредитования в месяцах
        return s * (p + (p / ((int) Math.pow(1 + p, n) - 1)));
    }*/
}
