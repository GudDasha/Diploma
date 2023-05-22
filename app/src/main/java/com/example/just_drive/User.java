package com.example.just_drive;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class User {
    public String currentuser;
    public String id;
    public String name;
    public String email;
    public String password;

    public User(String currentuser,String id,String name,String email, String password){
        this.currentuser = currentuser;
        this.name = name;
        this.id = id;
        this.email = email;
        this.password = password;
    }
    public User(){

    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }


}
