package com.enigma.controller;

import com.enigma.mdel.User;
import com.enigma.mdel.response.SuccessResponse;
import com.enigma.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping
    public ResponseEntity getUsers(){
        User[] users = userService.getUsers();

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("gg", users));
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable("id") String id){
        User users = userService.getById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("gg", users));
    }
}
