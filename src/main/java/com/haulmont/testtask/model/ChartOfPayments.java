package com.haulmont.testtask.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class ChartOfPayments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date paymentDate;
    @Column
    private int paymentSum;
    @Column
    private int creditBodyRepayment;//Сумма гашения тела кредита
    @Column
    private int amountOfInterestRepayment;//Сумма гашения процентов


}
