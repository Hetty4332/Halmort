package com.haulmont.testtask.model;

import com.haulmont.testtask.annotation.CreditLimit;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class CreditOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Client client;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Credit credit;
    @Column
    @NotBlank
    private int sumCredit;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Payment> chartOfPayments;

}
