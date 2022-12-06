package com.enigma.service;

import com.enigma.mdel.User;
import com.enigma.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
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
