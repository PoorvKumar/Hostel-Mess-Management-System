package com.project.login;

import com.project.person.Person;

public class Login extends Person {
    private String Email;
    private String Password;

    public Login() {

    }

    public Login(String email, String password) {
        Email = email;
        Password = password;
    }

    public Login(String name, String email, String password, int age, String gender, String contact_Info) {
        super(name, age, gender, contact_Info);
        Email = email;
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
