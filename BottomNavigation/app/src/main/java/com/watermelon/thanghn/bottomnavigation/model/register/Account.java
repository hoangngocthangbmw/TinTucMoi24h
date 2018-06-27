package com.watermelon.thanghn.bottomnavigation.model.register;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by thang on 3/23/2018.
 */

public class Account {
    private String userName;
    private String email;
    private String password;

    public Account(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
    public Account(DataSnapshot dataSnapshot){
        this.email=dataSnapshot.child("email").getValue().toString();
        this.userName=dataSnapshot.child("userName").getValue().toString();
        this.password=dataSnapshot.child("password").getValue().toString();
    }
    public Account() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
