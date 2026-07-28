package com.medinear.medinear.service;

import com.medinear.medinear.entity.Bill;
import com.medinear.medinear.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User registerUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByPhoneNumber(String phoneNumber);

    List<User> getAllUsers();

    List<Bill> getPreviousOrders(Long userId);
}