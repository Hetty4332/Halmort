package com.haulmont.testtask.model;

import com.haulmont.testtask.repository.ChartOfPaymentsRepository;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class CreditOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    private Credit credit;
    @Column
    private int sumCredit;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Payment> chartOfPayments;

}
