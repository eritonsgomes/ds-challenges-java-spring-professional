package com.devsuperior.dsdesafios.dscommerce.mappers;

import com.devsuperior.dsdesafios.dscommerce.dto.UserDTO;
import com.devsuperior.dsdesafios.dscommerce.entities.User;
import com.devsuperior.dsdesafios.dscommerce.qualifiers.RoleMapperQualifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { RoleCollectionMapper.class, OrderMapper.class })
public interface UserMapper extends BeanMapper<User, UserDTO> {

    @Override
    @Mapping(source = "roles", target = "roles", qualifiedBy = { RoleMapperQualifier.class })
    UserDTO toDTO(User entity);

    @Override
    @Mapping(source = "roles", target = "roles", qualifiedBy = { RoleMapperQualifier.class })
    User toEntity(UserDTO dto);

    @Override
    @Mapping(source = "roles", target = "roles", qualifiedBy = { RoleMapperQualifier.class })
    List<UserDTO> toDTO(List<User> entities);

    @Override
    @Mapping(source = "roles", target = "roles", qualifiedBy = { RoleMapperQualifier.class })
    List<User> toEntity(List<UserDTO> dataObjects);

}

