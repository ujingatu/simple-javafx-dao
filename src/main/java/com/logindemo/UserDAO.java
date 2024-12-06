package com.logindemo;

import java.util.List;

public interface UserDAO {
    void addUser(User user);
    User getUser(int id);
    User getUserByUsername(String username);
    void updateUser(User user);
    void deleteUser(int id);
    List<User> getAllUsers();
}

