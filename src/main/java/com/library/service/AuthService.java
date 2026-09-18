package com.library.service;
import com.library.database.DatabaseConnection;
public class AuthService {
    public boolean authenticate(String username,String password){var user=DatabaseConnection.getStore().users.get(username);return user!=null&&user.password().equals(password);}
}