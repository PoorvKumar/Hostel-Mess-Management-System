package com.project.staff;
import java.util.*;
import com.project.person.*;
interface Salary{
   int getSalary() throws Exception;
}
public abstract class Staff extends Person implements Salary {
    private String ID;
    private String designation;
    private String Address;
    private String doj;

    private int work_experience;
    private int base_Salary;
    public Staff(){

    }
    public Staff(String ID,String name, int age, String gender, String contact_Info,String designation,String Address,String doj,int work_experience)
    {
        super(name,age,gender,contact_Info);
        setID(ID);
        setDesignation(designation);
        setAddress(Address);
        setDoj(doj);
        setWork_experience(work_experience);
    }

    public int getBase_Salary() {
        return base_Salary;
    }

    public void setBase_Salary(int base_Salary) {
        this.base_Salary = base_Salary;
    }

    public int getWork_experience() {
        return work_experience;
    }

    public void setWork_experience(int work_experience) {
        this.work_experience = work_experience;
    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }
}
