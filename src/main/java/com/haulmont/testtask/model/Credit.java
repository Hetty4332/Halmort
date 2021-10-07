package com.haulmont.testtask.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column
    private int creditLimit;
    @Column
    private int interestRate;
    @ManyToOne (cascade = CascadeType.ALL)
    private Bank creditBank;
    @OneToOne(cascade = CascadeType.ALL)
    private CreditOffer creditOffer;
}
