package com.haulmont.testtask.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    private double interestRate;
    @ManyToOne (cascade = CascadeType.ALL)
    private Bank creditBank;
    @OneToMany (cascade = CascadeType.ALL)
    private List<CreditOffer> creditOffers;
}
