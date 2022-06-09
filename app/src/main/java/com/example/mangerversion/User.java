package com.example.mangerversion;

public class User {

    private int id ;
    private  String userName ;
    private  String email ;
    private  String password ;


    public User(String userName, String email, int id) {
        this.userName = userName;
        this.email = email;
        this.id = id;
    }

    public String getUsername() {
        return userName;
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


