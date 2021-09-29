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
    private int limit;
    @Column
    private int interestRate;
}
