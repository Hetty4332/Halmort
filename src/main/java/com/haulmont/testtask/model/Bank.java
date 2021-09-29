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
    @OneToMany(mappedBy = "credit", fetch = FetchType.EAGER)
    private List<Credit> credits;
    @OneToMany(mappedBy = "credit", fetch = FetchType.EAGER)
    List<Client> clients;
}
