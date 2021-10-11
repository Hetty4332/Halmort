package com.haulmont.testtask.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column
    @NotNull
    private int creditLimit;
    @Column
    @NotNull
    private int interestRate;
    @ManyToOne (cascade = CascadeType.ALL)
    private Bank creditBank;
    @OneToOne(cascade = CascadeType.ALL)
    private CreditOffer creditOffer;
}
