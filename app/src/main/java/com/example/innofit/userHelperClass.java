package com.example.innofit;

public class userHelperClass {
    String name, user, email, height, weight, pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public userHelperClass(String name, String user, String email, String height, String weight, String pass) {
        this.name = name;
        this.user = user;
        this.email = email;
        this.height = height;
        this.weight = weight;
        this.pass = pass;
    }

    public userHelperClass() {
    }
}
