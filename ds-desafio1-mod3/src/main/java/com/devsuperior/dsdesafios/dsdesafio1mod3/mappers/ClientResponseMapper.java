package com.devsuperior.dsdesafios.dsdesafio1mod3.mappers;

import com.devsuperior.dsdesafios.dsdesafio1mod3.dto.ClientResponseDTO;
import com.devsuperior.dsdesafios.dsdesafio1mod3.entities.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientResponseMapper extends BeanMapper<Client, ClientResponseDTO> {

}
