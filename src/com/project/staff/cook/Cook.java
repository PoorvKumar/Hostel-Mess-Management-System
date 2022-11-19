package com.project.staff.cook;

import com.project.staff.Staff;

import java.util.Date;

public class Cook extends Staff {

   public Cook(){
    }

    public Cook (String ID,String name, int age, String gender, String contact_Info, String designation, String Address, String doj, int work_experience){
        super(ID,name,age,gender,contact_Info,designation,Address,doj,work_experience);
        setBase_Salary(12000);
    }

    public int getSalary(){
        int salary=getBase_Salary()+((10*getBase_Salary())*getWork_experience())/100;
        return salary;
    }
}
