package com.haulmont.testtask.dto;

import lombok.Data;



@Data
public class CreditOfferRequest {

    private Long id;

    private Long clientId;

    private Long creditId;

    private int sumCredit;

    private int countMonthCredit;
}
