package com.haulmont.testtask.controller;

import com.haulmont.testtask.model.Bank;
import com.haulmont.testtask.model.Client;
import com.haulmont.testtask.repository.BankRepository;
import com.haulmont.testtask.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients")
    public String getClient(Model model) {
        List<Client> clients = new ArrayList<>();
        clients.addAll(clientRepository.findAll());
        model.addAttribute("clients", clients);
        model.addAttribute("client", new Client());
        return "clients";
    }

    @PostMapping("/clients")
    public String addClient(@ModelAttribute("client") Client client) {
        clientRepository.save(client);
        return "clients";
    }

    @PostMapping("/clients")
    public String deleteClient(@ModelAttribute("client") Client client) {
        clientRepository.delete(client);
        return "clients";
    }

}
