package com.haulmont.testtask.dto;

import lombok.Data;

@Data
public class CreditWeb {
    private Long id;
    private Long idBank;
    private int creditLimit;
    private double interestRate;
    private String bankName;
}
