package com.haulmont.testtask.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CreditOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @OneToOne
    private Client client;
    @OneToOne
    private Credit credit;
    @Column
    private int sumCredit;

}
