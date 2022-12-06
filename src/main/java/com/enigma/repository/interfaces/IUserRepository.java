package com.enigma.repository.interfaces;

import com.enigma.model.User;

public interface IUserRepository {
    User[] getUsers();

    User getUserById(String id);
}
