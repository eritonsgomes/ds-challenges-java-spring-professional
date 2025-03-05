package com.devsuperior.dsdesafios.dscommerce.controllers.impl;

import com.devsuperior.dsdesafios.dscommerce.controllers.UserController;
import com.devsuperior.dsdesafios.dscommerce.dto.UserDTO;
import com.devsuperior.dsdesafios.dscommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<UserDTO> getLoggedUser() {
        return ResponseEntity.ok(userService.getLoggedUser());
    }

}
