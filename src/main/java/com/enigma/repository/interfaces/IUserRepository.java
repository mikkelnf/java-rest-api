package com.enigma.repository.interfaces;

import com.enigma.mdel.User;

public interface IUserRepository {
    User[] getUsers();

    User getUserById(String id);
}
