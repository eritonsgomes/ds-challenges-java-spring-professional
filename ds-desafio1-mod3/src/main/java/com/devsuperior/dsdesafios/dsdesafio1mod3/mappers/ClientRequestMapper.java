package com.devsuperior.dsdesafios.dsdesafio1mod3.mappers;

import com.devsuperior.dsdesafios.dsdesafio1mod3.dto.ClientResquestDTO;
import com.devsuperior.dsdesafios.dsdesafio1mod3.entities.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientRequestMapper extends BeanMapper<Client, ClientResquestDTO> {

}
