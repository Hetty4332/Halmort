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
    @OneToMany(mappedBy = "creditBank", fetch = FetchType.EAGER)
    private List<Credit> credits;
    @OneToMany(mappedBy = "clientBank", fetch = FetchType.LAZY)
    private List<Client> clients;

}
