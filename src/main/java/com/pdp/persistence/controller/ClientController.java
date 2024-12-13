package com.pdp.persistence.controller;

import com.pdp.persistence.common.Framework;
import com.pdp.persistence.dto.ClientDto;
import com.pdp.persistence.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/clients")
@Slf4j
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/test")
    public String testGet() {
        return "success";
    }

    @GetMapping("/{id}")
    public ClientDto getClientById(@PathVariable(name = "id") UUID id, @RequestParam Framework framework) {
        log.info("Получение клиента по id {}", id);
        return clientService.findId(id, framework);
    }

    @GetMapping
    public List<ClientDto> getAllClients(@RequestParam Framework framework) {
        log.info("Получение всех клиентов");
        return clientService.findAll(framework);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@RequestBody ClientDto clientDto, @RequestParam Framework framework) {
        log.info("Сохранение клиента с id {}", clientDto.id());
        clientService.save(clientDto, framework);
    }
}
