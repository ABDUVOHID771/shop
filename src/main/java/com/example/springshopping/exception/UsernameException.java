package com.example.springshopping.exception;

public class UsernameException extends BaseException {
    public UsernameException(String message) {
        super("Username : " + message + " is already exists");
    }
    public UsernameException(){
        super("Invalid username or password");
    }
}
