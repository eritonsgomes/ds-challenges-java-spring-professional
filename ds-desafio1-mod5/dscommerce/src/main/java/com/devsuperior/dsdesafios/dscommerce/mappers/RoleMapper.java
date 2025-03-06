package com.devsuperior.dsdesafios.dscommerce.mappers;

import com.devsuperior.dsdesafios.dscommerce.dto.RoleDTO;
import com.devsuperior.dsdesafios.dscommerce.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends BeanMapper<Role, RoleDTO> {

}
