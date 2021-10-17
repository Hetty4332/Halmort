package com.haulmont.testtask.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Credit> credits;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Client> clients;

}
