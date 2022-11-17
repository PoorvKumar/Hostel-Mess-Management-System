package com.project.staff.cleaner;

import com.project.staff.Staff;

import java.util.Date;

public class Cleaner extends Staff {

    public Cleaner(){
    }
    public Cleaner (String ID,String name, int age, String gender, String contact_Info, String designation, String Address, String  doj,int work_experience){
        super(ID,name,age,gender,contact_Info,designation,Address,doj,work_experience);
        setBase_Salary(6000);
    }

    public int getSalary(){
        int salary=getBase_Salary()+((10*getBase_Salary())*getWork_experience())/100;
        return salary;
    }
}
