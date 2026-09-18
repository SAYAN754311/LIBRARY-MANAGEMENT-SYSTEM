package com.library.model;
import java.io.Serializable;
public record Member(int id,String name,String email,String phone) implements Serializable {
    public Member { if(id<1||name==null||name.isBlank()) throw new IllegalArgumentException("Invalid member values"); }
    public Member withDetails(String newName,String newEmail,String newPhone){return new Member(id,newName,newEmail,newPhone);}
}