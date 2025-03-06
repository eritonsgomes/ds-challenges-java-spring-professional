package com.devsuperior.dsdesafios.dscommerce.mappers;

import com.devsuperior.dsdesafios.dscommerce.dto.ClientDTO;
import com.devsuperior.dsdesafios.dscommerce.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper extends BeanMapper<User, ClientDTO> {

}

