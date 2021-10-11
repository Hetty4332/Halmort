package com.haulmont.testtask.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate paymentDate;
    @Column
    private int paymentSum;
    @Column
    private int creditBodyRepayment;//Сумма гашения тела кредита
    @Column
    private int amountOfInterestRepayment;//Сумма гашения процентов
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private CreditOffer creditOffer;


}
