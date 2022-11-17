package com.project.main;


import com.project.service.DatabaseService;
import com.project.staff.cleaner.Cleaner;
import com.project.staff.cook.Cook;
import com.project.staff.serve.Serve;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
import java.util.Scanner;


public class MainClass {
    public static void main(String[] args){

        DatabaseService databaseService = new DatabaseService();

        try(Scanner scan=new Scanner(System.in)){

            boolean isRunning = true;

            while(isRunning){
                System.out.println("Enter Choice");
                System.out.println("1. Insert");
                System.out.println("2. Select all");
                System.out.println("3. Select Staff by an ID");
                System.out.println("4. Delete Staff");
                System.out.println("5. Update Staff");
                System.out.println("6. Search staff by Name(partial string applicable)");
                System.out.println("7. Search Staff by Designation");
                System.out.println("8. search Staff by Gender");
                System.out.println("9. Total Number of Staffs");
                System.out.println("10. Total Number Of Staffs According to Gender");
                System.out.println("11. Total Number Of Staffs According to Designation");
                System.out.println("12. Average Age of Staff");
                System.out.println("13. Maximum , Minimum , Average Salary of Staff ");
                System.out.println("14. Search Staff By Salary");
                System.out.println("15. Search Staff whose Salary is greater than or equal to particular Salary");
                System.out.println("16. Search Staff whose Salary is less than or equal to particular Salary");
                System.out.println("17. Exit");
                int choice = Integer.parseInt(scan.nextLine());

                switch (choice){
                    case 1:
                     System.out.println("Enter ID,Name,Age,Gender(m/f),contact_Info,Designation,Address,Date of Joining,Work Experience Respectively");
                        String id=scan.nextLine();
                        if((id.toUpperCase()).contains("CK"))
                        {
                            databaseService.insertStaff(new Cook(id.toUpperCase(), scan.nextLine(),Integer.parseInt(scan.nextLine()),scan.nextLine(),scan.nextLine(),scan.nextLine(),scan.nextLine(),scan.nextLine(),Integer.parseInt(scan.nextLine())));
                        } else if ((id.toUpperCase()).contains("CR")) {
                            databaseService.insertStaff(new Cleaner(id.toUpperCase(), scan.nextLine(),Integer.parseInt(scan.nextLine()),scan.nextLine(),scan.nextLine(),scan.nextLine(),scan.nextLine(),scan.nextLine(),Integer.parseInt(scan.nextLine())));
                        } else if ((id.toUpperCase()).contains("SV")) {
                            databaseService.insertStaff(new Serve(id.toUpperCase(), scan.nextLine(),Integer.parseInt(scan.nextLine()),scan.nextLine(),scan.nextLine(),scan.nextLine(),scan.nextLine(),scan.nextLine(),Integer.parseInt(scan.nextLine())));
                        }
                        else
                        System.out.println("Invalid ID Choice");
                        break;

                    case 2:
                        System.out.println("Details Of ALl Staffs");
                        System.out.println("___________________________________________________________");
                        databaseService.getAllStaffs();
                        break;

                    case 3:
                        System.out.println("Enter ID of a Staff");
                        databaseService.getStaffById(scan.nextLine());
                        break;

                    case 4:
                        System.out.println("Enter ID of the Staff you want to delete");
                        databaseService.deleteStaffById(scan.nextLine());
                        break;

                    case 5:
                        System.out.println("Enter ID of the Staff that you want to update");
                        String updateStaffId = scan.nextLine();
                        boolean isFound = databaseService.getStaffById(updateStaffId.toUpperCase());

                        if (isFound) {

                            System.out.println("Enter New ID,Name,Age,Gender(m/f),contact_Info,Designation,Address,Date of Joining,Work Experience Respectively");
                            String iD=scan.nextLine();
                            if((iD.toUpperCase()).contains("CK"))
                            {
                                databaseService.updateStaff(updateStaffId,new Cook(iD.toUpperCase(), scan.nextLine(),Integer.parseInt(scan.nextLine()),scan.nextLine(),scan.nextLine(),scan.nextLine(),scan.nextLine(),scan.nextLine(),Integer.parseInt(scan.nextLine())));
                            } else if ((iD.toUpperCase()).contains("CR")) {
                                databaseService.updateStaff(updateStaffId,new Cleaner(iD.toUpperCase(), scan.nextLine(),Integer.parseInt(scan.nextLine()),scan.nextLine(),scan.nextLine(),scan.nextLine(),scan.nextLine(),scan.nextLine(),Integer.parseInt(scan.nextLine())));
                            } else if ((iD.toUpperCase()).contains("SV")) {
                                databaseService.updateStaff(updateStaffId,new Serve(iD.toUpperCase(), scan.nextLine(),Integer.parseInt(scan.nextLine()),scan.nextLine(),scan.nextLine(),scan.nextLine(),scan.nextLine(),scan.nextLine(),Integer.parseInt(scan.nextLine())));
                            }
                            else
                                System.out.println("Invalid ID Choice");
                        }
                        break;

                    case 6:
                        System.out.println("Enter Name of Staff");
                           databaseService.getStaffByName(scan.nextLine());
                        break;

                    case 7:
                        System.out.print("Enter Designation: ");
                        databaseService.getStaffByDesignation(scan.nextLine());
                        break;

                    case 8:
                        System.out.print("Enter Gender(m/f): ");
                        databaseService.getStaffByGender(scan.nextLine());
                        break;

                    case 9:
                        System.out.print("Total Number of Staff : ");
                        int count=databaseService.getTotalCountStaff();
                        System.out.println(count+"\n");
                        break;

                    case 10:
                        databaseService.getTotalCountStaffByGender();
                        break;

                    case 11:
                        databaseService.getTotalCountStaffByDesignation();
                        break;

                    case 12:
                        databaseService.getAvgAgeStaff();
                        break;

                    case 13:
                        databaseService.getMaxMinAvgSalStaff();
                        break;

                    case 14:
                        System.out.print("Enter Salary Amount : ");
                        databaseService.getSalaryByvalue(Integer.parseInt(scan.nextLine()));
                        break;

                    case 15:
                        System.out.print("Enter Salary Amount: ");
                        databaseService.getGreaterSal(Integer.parseInt(scan.nextLine()));
                        break;


                    case 16:
                        System.out.print("Enter Salary Amount: ");
                        databaseService.getLessSal(Integer.parseInt(scan.nextLine()));
                        break;

                    case 17:
                        System.out.println("Thank You. Visit Again");
                        isRunning = false;
                        break;

                    default:
                        System.out.println("Invalid Choice");
                        isRunning=false;
                        break;
                }
            }

        }catch (Exception e) {
            throw new RuntimeException("Something went wrong." + e);
        }



    }
}
