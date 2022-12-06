package com.enigma.service;

import com.enigma.mdel.User;

public interface IUserService {
    User[] getUsers();

    User getById(String id);
}
