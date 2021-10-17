package com.haulmont.testtask.dto;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreditOfferRequest {

    private Long id;
    @NotNull
    private Long clientId;
    @NotNull
    private Long creditId;
    @NotNull
    private int sumCredit;
    @NotNull
    private int countMonthCredit;
}
