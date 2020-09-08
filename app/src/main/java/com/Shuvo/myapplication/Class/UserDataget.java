package com.Shuvo.myapplication.Class;

public class UserDataget {

    String firebase_id, name, username, password, address, phn_number, profession, image;

    public UserDataget() {
    }

    public UserDataget(String firebase_id, String name, String username, String password, String address, String phn_number, String profession, String image) {
        this.firebase_id = firebase_id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phn_number = phn_number;
        this.profession = profession;
        this.image = image;
    }

    public String getFirebase_id() {
        return firebase_id;
    }

    public void setFirebase_id(String firebase_id) {
        this.firebase_id = firebase_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhn_number() {
        return phn_number;
    }

    public void setPhn_number(String phn_number) {
        this.phn_number = phn_number;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
