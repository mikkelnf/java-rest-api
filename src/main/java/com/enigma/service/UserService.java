package com.enigma.service;

import com.enigma.model.User;
import com.enigma.repository.interfaces.IUserRepository;
import com.enigma.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    IUserRepository userRepository;

    public UserService(@Autowired IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User[] getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public User getById(String id) {
        return userRepository.getUserById(id);
    }
}
