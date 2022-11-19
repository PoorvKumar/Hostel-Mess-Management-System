package com.project.student;

import com.project.person.Person;

public class Student extends Person {
    private String Roll_Number;
    private String Degree;
    private String Food_Choice;

    public Student() {

    }

    public Student(String roll_Number, String degree, String food_Choice) {
        Roll_Number = roll_Number;
        Degree = degree;
        Food_Choice = food_Choice;
    }

    public Student(String roll_Number, String name, int age, String gender, String contact_Info, String degree, String food_Choice) {
        super(name, age, gender, contact_Info);
        Roll_Number = roll_Number;
        Degree = degree;
        Food_Choice = food_Choice;
    }

    public String getRoll_Number() {
        return Roll_Number;
    }

    public void setRoll_Number(String roll_Number) {
        Roll_Number = roll_Number;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String degree) {
        Degree = degree;
    }

    public String getFood_Choice() {
        return Food_Choice;
    }

    public void setFood_Choice(String food_Choice) {
        Food_Choice = food_Choice;
    }
}
