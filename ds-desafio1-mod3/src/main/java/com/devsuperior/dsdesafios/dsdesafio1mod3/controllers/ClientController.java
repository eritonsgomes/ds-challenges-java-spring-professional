package com.devsuperior.dsdesafios.dsdesafio1mod3.controllers;

import com.devsuperior.dsdesafios.dsdesafio1mod3.dto.ClientResponseDTO;
import com.devsuperior.dsdesafios.dsdesafio1mod3.dto.ClientResquestDTO;
import com.devsuperior.dsdesafios.dsdesafio1mod3.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponseDTO> insert(@Valid @RequestBody ClientResquestDTO dto) {
        var response = clientService.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<Page<ClientResponseDTO>> findAll(Pageable pageable) {
        var response = clientService.findAll(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDTO> findById(@PathVariable Long id) {
        ClientResponseDTO dto = clientService.findById(id);

        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ClientResquestDTO dto) {
        var response = clientService.update(id, dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

}
