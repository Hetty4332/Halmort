package com.haulmont.testtask.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column
    private String name;//ФИО клиента
    @Column
    private String phoneNumber;
    @Column
    private String email;
    @Column
    private String passportNumber;
    @ManyToOne()
    private Bank clientBank;
    @OneToOne(cascade = CascadeType.ALL)
    private CreditOffer creditOffer;

}
