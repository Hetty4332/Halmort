package com.haulmont.testtask.dto;

import com.haulmont.testtask.model.Client;
import com.haulmont.testtask.model.Credit;
import com.haulmont.testtask.model.Payment;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class CreditOfferRequest {
    private Long id;
    private Long clientId;
    private Long creditId;
    private int sumCredit;
    private int countMonthCredit;
}
