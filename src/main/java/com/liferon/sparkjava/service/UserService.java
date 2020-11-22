package com.liferon.sparkjava.service;

import com.liferon.sparkjava.dto.User;
import com.liferon.sparkjava.exception.UserException;

import java.util.Collection;

public interface UserService {

    public User addUser(User user);

    public Collection<User> getUsers();

    public User getUser(String id);

    public User updateUser(String id, User user) throws UserException;

    public void deleteUser(String id);

    public boolean userExist(String id);
}
