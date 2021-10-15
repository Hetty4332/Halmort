package com.haulmont.testtask.controller;

import com.haulmont.testtask.model.Client;
import com.haulmont.testtask.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
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

    @PostMapping("/editClient")
    public String addClient(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return "/editClient";
            }
            if (client.getPhoneNumber().isEmpty()) {
                client.setPhoneNumber("Не указан");
            }
            clientRepository.save(client);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "redirect:/clients";
    }

    //тут сделать либо пост либо делет.кнопку обернуть
    @GetMapping("/deleteClient/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return "redirect:/clients";
    }

    @GetMapping("/editClient/{id}")
    public String getClient(@PathVariable Long id, Model model) {

        Client client = clientRepository.findById(id).orElse(new Client());//TODO Сделать тут ElseThrow и выкидыватьк какую-нибудь ошибку, если объект null
        model.addAttribute("client", client);
        return "editClient";
    }

    @GetMapping("/editClient")
    public String addClient(Model model) {

        model.addAttribute("client", new Client());
        return "editClient";
    }

/*    @PostConstruct
    public void init(){
        Client client = new Client();
        client.setName("Иванов Иван");
        client.setPhoneNumber("88005553535");
        client.setEmail("v@v.v");
        client.setPassportNumber("6565989898");
        clientRepository.save(client);
    }*/
}
