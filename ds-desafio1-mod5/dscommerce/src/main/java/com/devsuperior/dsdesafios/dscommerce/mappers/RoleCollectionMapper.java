package com.devsuperior.dsdesafios.dscommerce.mappers;

import com.devsuperior.dsdesafios.dscommerce.entities.Role;
import com.devsuperior.dsdesafios.dscommerce.qualifiers.RoleMapperQualifier;
import com.devsuperior.dsdesafios.dscommerce.qualifiers.RolesDtoToRolesQualifier;
import com.devsuperior.dsdesafios.dscommerce.qualifiers.RolesToRolesDtoQualifier;

import java.util.HashSet;
import java.util.Set;

@RoleMapperQualifier
public class RoleCollectionMapper {

    @RolesDtoToRolesQualifier
    static Set<String> mapRolesToRolesDto(Set<Role> roles) {
        Set<String> mappedRoles = new HashSet<>();

        for (Role role: roles) {
            mappedRoles.add(role.getAuthority());
        }

        return mappedRoles;
    }

    @RolesToRolesDtoQualifier
    static Set<Role> mapRolesDtoToRoles(Set<String> roles) {
        Set<Role> mappedRoles = new HashSet<>();

        for (String role: roles) {
            mappedRoles.add(new Role(null, role));
        }

        return mappedRoles;
    }

}
