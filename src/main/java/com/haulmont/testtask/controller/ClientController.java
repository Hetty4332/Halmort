package com.haulmont.testtask.controller;

import com.haulmont.testtask.model.Client;
import com.haulmont.testtask.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @PostMapping("/edit")
    public String addClient(@ModelAttribute("client") Client client) {
        clientRepository.save(client);
        return "redirect:/clients";
    }
//тут сделать либо пост либо делет.кнопку обернуть
    @GetMapping("/deleteClient/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return "redirect:/clients";
    }
    @GetMapping("/edit/{id}")
    public String getClient(@PathVariable Long id, Model model) {

        Client client= clientRepository.findById(id).orElse(new Client());//TODO Сделать тут ElseThrow и выкидыватьк какую-нибудь ошибку, если объект null
        model.addAttribute("client", client);
        return "edit";
    }
    @GetMapping("/edit")
    public String addClient(Model model) {

        model.addAttribute("client", new Client());
        return "edit";
    }
}
