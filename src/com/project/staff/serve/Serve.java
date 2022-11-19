package com.project.staff.serve;
import com.project.staff.Staff;

import java.time.LocalDate;
import java.time.Period;

public class Serve extends Staff {

   public Serve(){
    }
    public Serve(String ID,String name, int age, String gender, String contact_Info, String designation, String Address, String  doj,int work_experience){
       super(ID,name,age,gender,contact_Info,designation,Address,doj,work_experience);
       setBase_Salary(7000);
    }

    private int factor()
    {
        LocalDate D=LocalDate.of(Integer.parseInt(getDoj().substring(6)),Integer.parseInt(getDoj().substring(3,5)),Integer.parseInt(getDoj().substring(0,2)));
        Period diff=Period.between(D,LocalDate.now());
        return diff.getYears();
    }
    public int getSalary() {
        int salary=getBase_Salary()+((10*getBase_Salary())*factor())/100+((2*getBase_Salary())*getWork_experience())/100;
        return salary;
    }


}
