package com.enigma.service.interfaces;

import com.enigma.model.User;

public interface IUserService {
    User[] getUsers();

    User getById(String id);
}
