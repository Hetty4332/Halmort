package com.haulmont.testtask.dto;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class CreditOfferRequest {

    private Long id;
    private Long clientId;
    private Long creditId;
    private int sumCredit;
    private int countMonthCredit;
}
