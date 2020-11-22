package com.liferon.sparkjava.service;

import com.liferon.sparkjava.dto.User;
import com.liferon.sparkjava.exception.UserException;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserServiceMapImpl implements UserService {
    private final Map<String, User> userMap;

    public UserServiceMapImpl() {
        userMap = new ConcurrentHashMap<>();
    }

    @Override
    public User addUser(User user) {
        String id = UUID.randomUUID().toString();
        user.setId(id);
        return userMap.put(id, user);
    }

    @Override
    public Collection<User> getUsers() {
        return userMap.values();
    }

    @Override
    public User getUser(String id) {
        return userMap.get(id);
    }

    @Override
    public User updateUser(String userId, User newUser) throws UserException {
        try {
            User toEdit = userMap.get(userId);

            if (toEdit == null) {
                throw new UserException("User not found");
            }

            if (newUser.getEmail() != null && !newUser.getEmail().isEmpty()) {
                toEdit.setEmail(newUser.getEmail());
            }

            if (newUser.getFirstName() != null && !newUser.getFirstName().isEmpty()) {
                toEdit.setFirstName(newUser.getFirstName());
            }

            if (newUser.getLastName() != null && !newUser.getLastName().isEmpty()) {
                toEdit.setLastName(newUser.getLastName());
            }
            return toEdit;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new UserException(ex.getMessage());
        }
    }

    @Override
    public void deleteUser(String id) {
        userMap.remove(id);
    }

    @Override
    public boolean userExist(String id) {
        return userMap.containsKey(id);
    }
}
