
package com.example.apartwell.models;

import java.util.HashMap;


public class User {
    protected String user_id;
    protected String username;
    protected String firstName;
    protected String secondName;
    protected String email;
    protected String mobileNo;
    protected String password;
    
    public User(HashMap m){
        user_id = (String)m.get("user_id");
        username = (String)m.get("username");
        firstName = (String)m.get("fname");
        secondName = (String)m.get("lname");
        email = (String)m.get("email");
        mobileNo = (String)m.get("mobile");
        password = (String)m.get("password");
    }

    public User() {

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

}
