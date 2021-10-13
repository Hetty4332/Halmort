package com.haulmont.testtask.service;

import com.haulmont.testtask.model.Credit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
public class CreditOfferService {


    // долговая часть ежемесячного платежа
     public int deptPaart(int creditTime, int sumCredit) {
        int s = sumCredit;
        int n = creditTime;
        return s / n;

    }

    //проценты в ежемесячном платеже
    public double percent(double remainder, double interestRate) {

        return remainder *(interestRate/100.0/12.0) ;//поделить на 12?
    }

    //сумма ежемесячного платежа
    public double amountOfMonthlyPayment(int creditTime, double remainder, int sumCredit, double interestRate) {
        return deptPaart(creditTime, sumCredit) + percent(remainder, interestRate);
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
