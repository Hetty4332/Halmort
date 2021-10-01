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
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Bank clientBank;
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private CreditOffer creditOffer;

}
