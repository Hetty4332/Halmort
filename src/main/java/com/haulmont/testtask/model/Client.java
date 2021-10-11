package com.haulmont.testtask.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column
    @NotBlank
    @Size(max=50)
    private String name;//ФИО клиента
    @Column
    private String phoneNumber;
    @Column
    @Email
    private String email;
    @Column
    @Size(max=10)
    private String passportNumber;
    @ManyToOne
    private Bank clientBank;
    @OneToOne(cascade = CascadeType.ALL)
    private CreditOffer creditOffer;

}
