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
    @OneToMany(mappedBy = "creditBank", fetch = FetchType.LAZY)
    private List<Credit> credits;
    @OneToMany(mappedBy = "clientBank", fetch = FetchType.EAGER)
    private List<Client> clients;

}
