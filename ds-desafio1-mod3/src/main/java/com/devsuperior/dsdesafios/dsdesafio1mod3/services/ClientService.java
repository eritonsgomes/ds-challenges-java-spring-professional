package com.devsuperior.dsdesafios.dsdesafio1mod3.services;

import com.devsuperior.dsdesafios.dsdesafio1mod3.dto.ClientResponseDTO;
import com.devsuperior.dsdesafios.dsdesafio1mod3.dto.ClientResquestDTO;
import com.devsuperior.dsdesafios.dsdesafio1mod3.entities.Client;
import com.devsuperior.dsdesafios.dsdesafio1mod3.exceptions.database.DatabaseException;
import com.devsuperior.dsdesafios.dsdesafio1mod3.exceptions.services.ResourceNotFoundException;
import com.devsuperior.dsdesafios.dsdesafio1mod3.mappers.ClientRequestMapper;
import com.devsuperior.dsdesafios.dsdesafio1mod3.mappers.ClientResponseMapper;
import com.devsuperior.dsdesafios.dsdesafio1mod3.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientRequestMapper clientRequestMapper;
    private final ClientResponseMapper clientResponseMapper;

    @Transactional
    public ClientResponseDTO insert(ClientResquestDTO dto) {
        Client entity = clientRequestMapper.toEntity(dto);
        entity = clientRepository.save(entity);

        return clientResponseMapper.toDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<ClientResponseDTO> findAll(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);

        return clients.map(clientResponseMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public ClientResponseDTO findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));

        return clientResponseMapper.toDTO(client);
    }

    @Transactional
    public ClientResponseDTO update(Long id, ClientResquestDTO dto) {
        try {
            var entity = clientRepository.getReferenceById(id);

            copyToEntity(dto, entity);

            entity = clientRepository.save(entity);

            return clientResponseMapper.toDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }

        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private static void copyToEntity(ClientResquestDTO source, Client target) {
        target.setName(source.getName());
        target.setCpf(source.getCpf());
        target.setBirthDate(source.getBirthDate());
        target.setChildren(source.getChildren());
        target.setIncome(source.getIncome());
    }

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientRequestMapper clientRequestMapper,
                         ClientResponseMapper clientResponseMapper) {
        this.clientRepository = clientRepository;
        this.clientRequestMapper = clientRequestMapper;
        this.clientResponseMapper = clientResponseMapper;
    }

}
