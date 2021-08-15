package com.example.SimbirsoftPricticeClients.controllets;

import com.example.SimbirsoftPricticeClients.dto.ClientRequestDto;
import com.example.SimbirsoftPricticeClients.dto.ClientResponseDto;
import com.example.SimbirsoftPricticeClients.dto.PaymentProjectRequestDto;
import com.example.SimbirsoftPricticeClients.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping(value = "/clients")
    public ResponseEntity<String> createClient(@RequestBody ClientRequestDto request) {
        String response = service.createClient(request);
        return ResponseEntity.accepted().body(response);
    }

    @PostMapping(value = "/clients/openProject")
    public ResponseEntity<String> openProject(@RequestBody PaymentProjectRequestDto request) {
        String response = service.payProject(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<ClientResponseDto> readClient(@PathVariable Long id) {
        ClientResponseDto response = service.readClient(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/clients/{id}/deposit")
    public ResponseEntity<ClientResponseDto> depositMoney(@PathVariable Long id, @RequestBody String amount) {
        ClientResponseDto response = service.depositMoney(id, amount);
        return ResponseEntity.ok().body(response);
    }
}
