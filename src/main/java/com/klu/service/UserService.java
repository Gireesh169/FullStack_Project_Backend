package com.klu.service;

import com.klu.model.User;
import java.util.List;

public interface UserService {

    User registerUser(User user);

    User loginUser(String email, String password);

    User getUserById(int id);

    List<User> getAllUsers();

    void deleteUser(int id);
}