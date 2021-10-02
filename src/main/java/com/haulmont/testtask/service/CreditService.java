package com.haulmont.testtask.service;

import com.haulmont.testtask.model.Credit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreditService {

    private final Credit credit;

    // долговая часть ежемесячного платежа
    private int deptPaart(int creditTime) {
        int s = credit.getCreditOffer().getSumCredit();
        int n = creditTime;
        return s / n;

    }

    //проценты в ежемесячном платеже
    private int percent(int remainder) {
        int p = credit.getInterestRate();//её наверное надо будет на 12 поделить
        return remainder * p;
    }

    //сумма ежемесячного платежа
    private int  amountOfMonthlyPayment(int creditTime,int remainder)
    {
        return deptPaart(creditTime)+percent(remainder);
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
