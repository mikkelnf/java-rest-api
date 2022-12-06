package com.enigma.repository;

import com.enigma.mdel.User;

public interface IUserRepository {
    User[] getUsers();

    User getUserById(String id);
}
