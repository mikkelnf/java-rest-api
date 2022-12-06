package com.enigma.repository;

import com.enigma.mdel.User;
import com.enigma.mdel.response.CoursePaymentResponse;
import com.enigma.mdel.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class UserRepository implements IUserRepository{

    @Value("${service.userUrl}")
    String userUrl;

    RestTemplate restTemplate;

    public UserRepository(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public User[] getUsers() {
        ResponseEntity<User[]> response = restTemplate.getForEntity(userUrl, User[].class);

        User[] users = response.getBody();
        return users;
    }

    @Override
    public User getUserById(String id) {
        ResponseEntity<User> response = restTemplate.getForEntity(userUrl + "/" + id, User.class);

        User user = response.getBody();
        return user;
    }
}
