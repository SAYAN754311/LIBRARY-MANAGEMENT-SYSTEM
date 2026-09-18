package com.library.model;
import java.io.Serializable;
public record User(String username,String password) implements Serializable {}