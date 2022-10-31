package com.project.person;

public class Person {
    private String Name;
    private int Age;
    private String Gender;
    private String Contact_Info;

    public Person() {

    }

    public Person(String name, int age, String gender, String contact_Info) {
        Name = name;
        Age = age;
        Gender = gender;
        Contact_Info = contact_Info;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getContact_Info() {
        return Contact_Info;
    }

    public void setContact_Info(String contact_Info) {
        Contact_Info = contact_Info;
    }
}
