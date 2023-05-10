package com.example.innofit;

public class UsersHelperClass {
    String names, users, emails, passwords;

    public UsersHelperClass() {
    }

    public UsersHelperClass(String names, String users, String emails, String passwords) {
        this.names = names;
        this.users = users;
        this.emails = emails;
        this.passwords = passwords;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }
}
