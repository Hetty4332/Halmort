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
    @OneToOne(mappedBy = "creditOffer", fetch = FetchType.EAGER)
    private Client client;
    @OneToOne(mappedBy = "creditOffer", fetch = FetchType.LAZY)
    private Credit credit;
    @Column
    private int sumCredit;

}
