package com.project.main;

import com.project.service.DatabaseService;
import com.project.staff.cleaner.Cleaner;
import com.project.staff.cook.Cook;
import com.project.staff.serve.Serve;
import com.project.student.Student;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DatabaseService databaseService = new DatabaseService();
        Random rand = new Random();

        try (Scanner scan = new Scanner(System.in)) {

            boolean isRunning = true;

            while (isRunning) {
                System.out.println("\nEnter choice:");
                System.out.println("1. Insert Student");
                System.out.println("2. Select All Student");
                System.out.println("3. Select Student by a Roll-Number");
                System.out.println("4. Select Student by a Name");
                System.out.println("5. Display the Total Count of Students");
                System.out.println("6. Display the Count of Male and Female Students");
                System.out.println("7. Display the Count of Non-Veg and Veg Students");
                System.out.println("8. Display the Count of B.Tech, M.Tech and PhD Students");
                System.out.println("9. Display the Average Age of Students");
                System.out.println("10. Delete Student by a Roll-Number");
                System.out.println("11. Update Student by a Roll-Number");
                System.out.println("12. Insert Students using CSV");
                System.out.println("13. Update Students using CSV");
                System.out.println("14. Insert Staff");
                System.out.println("15. Select all Staff");
                System.out.println("16. Select Staff by an ID");
                System.out.println("17. Delete Staff");
                System.out.println("18. Update Staff");
                System.out.println("19. Search staff by Name(partial string applicable)");
                System.out.println("20. Search Staff by Designation");
                System.out.println("21. Search Staff by Gender");
                System.out.println("22. Total Number of Staffs");
                System.out.println("23. Total Number Of Staffs According to Gender");
                System.out.println("24. Total Number Of Staffs According to Designation");
                System.out.println("25. Average Age of Staff");
                System.out.println("26. Maximum, Minimum, Average Salary of Staff ");
                System.out.println("27. Search Staff By Salary");
                System.out.println("28. Search Staff whose Salary is greater than or equal to particular Salary");
                System.out.println("29. Search Staff whose Salary is less than or equal to particular Salary");
                System.out.println("30. Insert Staff by CSV");
                System.out.println("31. Update Staff by CSV");
                System.out.println("32. Exit");

                int choice = Integer.parseInt(scan.nextLine());

                switch (choice) {
                    case 1 -> {
                        System.out.println("Enter the Roll Number, Name, Age, Gender, Contact Number, Degree, Food Choice(Veg/Non-Veg) of the Student");
                        databaseService.insertStudent(new Student(scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), scan.nextLine(), scan.nextLine()));
                    }

                    case 2 -> {
                        System.out.println("\nEnter choice:");
                        System.out.println("1. Sort by Roll Number");
                        System.out.println("2. Sort by Name");
                        System.out.println("3. Sort by Age");

                        int choice2 = Integer.parseInt(scan.nextLine());

                        switch (choice2) {
                            case 1 -> databaseService.getAllStudents("roll number");

                            case 2 -> databaseService.getAllStudents("name");

                            case 3 -> databaseService.getAllStudents("age");
                        }
                    }

                    case 3 -> {
                        System.out.println("Enter the Roll-Number of the Student");
                        databaseService.getStudentByRollNumber(scan.nextLine());
                    }

                    case 4 -> {
                        System.out.println("Enter the Name of the Student");
                        databaseService.getStudentByName(scan.nextLine());
                    }

                    case 5 -> databaseService.getStudentTotalCount();

                    case 6 -> databaseService.getMaleFemaleCount();

                    case 7 -> databaseService.getVegNonVegCount();

                    case 8 -> databaseService.getBTechMTechPhDCount();

                    case 9 -> databaseService.getAverageAgeOfStudents();

                    case 10 -> {
                        System.out.println("Enter the Roll-Number of the Student");
                        databaseService.deleteStudentByRollNumber(scan.nextLine());
                    }

                    case 11 -> {
                        System.out.println("Enter the Roll-Number of the Student");
                        String updateRollNumber = scan.nextLine();
                        boolean isFound = databaseService.getStudentByRollNumber(updateRollNumber);
                        if (isFound) {
                            System.out.println("Enter th1e Roll Number, Name, Age, Gender, Contact Number, Degree, Food Choice(Veg/Non-Veg) of the Student");
                            databaseService.updateStudent(updateRollNumber, new Student(scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), scan.nextLine(), scan.nextLine()));
                        }
                    }

                    case 12 -> {
                        System.out.println("Enter the File Path of CSV file");
                        databaseService.insertCSVStudent(scan.nextLine());
                    }

                    case 13 -> {
                        System.out.println("Enter the File Path of CSV file");
                        databaseService.updateCSVStudent(scan.nextLine());
                    }

                    case 14 -> {
                        System.out.println("Enter Designation(cook/serve/cleaner) Case does not matter  : ");
                        String Des = scan.nextLine();
                        int num;
                        if (Des.equalsIgnoreCase("cook")) {

                            do {
                                num = 1000 + rand.nextInt(9000);
                            } while (!databaseService.checkId(Des, num));

                            String id = "CK" + num;
                            System.out.println("Enter Name,Age,Gender(m/f),contact_Info,Address,Date of Joining,Work Experience Respectively");
                            databaseService.insertStaff(new Cook(id, scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), "COOK", scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine())));
                            System.out.println("ID Generated: CK" + num + "\n");

                        } else if (Des.equalsIgnoreCase("serve")) {
                            do {
                                num = 1000 + rand.nextInt(9000);
                            } while (!databaseService.checkId(Des, num));

                            String id = "SV" + num;
                            System.out.println("Enter Name,Age,Gender(m/f),contact_Info,Address,Date of Joining,Work Experience Respectively");
                            databaseService.insertStaff(new Cook(id, scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), "SERVE", scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine())));
                            System.out.println("ID Generated: SV" + num + "\n");

                        } else if (Des.equalsIgnoreCase("cleaner")) {
                            do {
                                num = 1000 + rand.nextInt(9000);
                            } while (!databaseService.checkId(Des, num));

                            String id = "CR" + num;
                            System.out.println("Enter Nam17e,Age,Gender(m/f),contact_Info,Address,Date of Joining,Work Experience Respectively");
                            databaseService.insertStaff(new Cook(id, scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), "CLEANER", scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine())));
                            System.out.println("ID Generated: CR" + num + "\n");

                        } else
                            System.out.println("Invalid Choice");
                    }

                    case 15 -> {
                        System.out.println("Enter Choice");
                        System.out.println("1. Print ALl the Staff Details Sorted According to Name");
                        System.out.println("2. Print ALl the Staff Details Sorted According to Salary");
                        System.out.println("3. Print ALl the Staff Details ");
                        switch (Integer.parseInt(scan.nextLine())) {
                            case 1 -> {
                                System.out.println("Details Of ALl Staffs");
                                System.out.println("___________________________________________________________");
                                databaseService.getAllStaffs("name");
                            }
                            case 2 -> {
                                System.out.println("Details Of ALl Staffs");
                                System.out.println("___________________________________________________________");
                                databaseService.getAllStaffs("salary");
                            }
                            case 3 -> {
                                System.out.println("Details Of ALl Staffs");
                                System.out.println("___________________________________________________________");
                                databaseService.getAllStaffs("noOrder");
                            }
                            default -> System.out.println("Invalid Choice\n");
                        }
                    }

                    case 16 -> {
                        System.out.println("Enter ID of a Staff");
                        databaseService.getStaffById(scan.nextLine());
                    }

                    case 17 -> {
                        System.out.println("Enter ID of the Staff you want to delete");
                        databaseService.deleteStaffById(scan.nextLine());
                    }

                    case 18 -> {
                        System.out.println("Enter ID of the Staff that you want to update");
                        String updateStaffId = scan.nextLine();
                        boolean isFound = databaseService.getStaffById(updateStaffId.toUpperCase());
                        if (isFound) {

                            System.out.println("Enter New ID,Name,Age,Gender(m/f),contact_Info,Designation,Address,Date of Joining,Work Experience Respectively");
                            String iD = scan.nextLine();
                            if ((iD.toUpperCase()).contains("CK")) {
                                databaseService.updateStaff(updateStaffId, new Cook(iD.toUpperCase(), scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), scan.nextLine(), scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine())));
                            } else if ((iD.toUpperCase()).contains("CR")) {
                                databaseService.updateStaff(updateStaffId, new Cleaner(iD.toUpperCase(), scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), scan.nextLine(), scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine())));
                            } else if ((iD.toUpperCase()).contains("SV")) {
                                databaseService.updateStaff(updateStaffId, new Serve(iD.toUpperCase(), scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), scan.nextLine(), scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine())));
                            } else
                                System.out.println("Invalid ID Choice");
                        }
                    }

                    case 19 -> {
                        System.out.println("Enter Name of Staff");
                        databaseService.getStaffByName(scan.nextLine());
                    }

                    case 20 -> {
                        System.out.println("Enter Choice");
                        System.out.println("1. Printing Details in Sorted order According to Name");
                        System.out.println("2. Printing Details in Sorted order According to Salary");
                        System.out.println("3. Printing details in normal order");
                        switch (Integer.parseInt(scan.nextLine())) {
                            case 1 -> {
                                System.out.println("Details Of ALl Staffs");
                                System.out.println("___________________________________________________________");
                                System.out.print("Enter Designation: ");
                                databaseService.getStaffByDesignation(scan.nextLine(), "name");
                            }
                            case 2 -> {
                                System.out.println("Details Of ALl Staffs");
                                System.out.println("___________________________________________________________");
                                System.out.print("Enter Designation: ");
                                databaseService.getStaffByDesignation(scan.nextLine(), "salary");
                            }
                            case 3 -> {
                                System.out.println("Details Of ALl Staffs");
                                System.out.println("___________________________________________________________");
                                System.out.print("Enter Designation: ");
                                databaseService.getStaffByDesignation(scan.nextLine(), "");
                            }
                            default -> System.out.println("Invalid Choice\n");
                        }
                    }

                    case 21 -> {
                        System.out.print("Enter Gender(m/f): ");
                        databaseService.getStaffByGender(scan.nextLine());
                    }

                    case 22 -> {
                        System.out.print("Total Number of Staff : ");
                        int count = databaseService.getTotalCountStaff();
                        System.out.println(count + "\n");
                    }

                    case 23 -> databaseService.getTotalCountStaffByGender();

                    case 24 -> databaseService.getTotalCountStaffByDesignation();

                    case 25 -> databaseService.getAvgAgeStaff();

                    case 26 -> databaseService.getMaxMinAvgSalStaff();

                    case 27 -> {
                        System.out.print("Enter Salary Amount : ");
                        databaseService.getSalaryByValue(Integer.parseInt(scan.nextLine()));
                    }

                    case 28 -> {
                        System.out.print("Enter Salary Amount: ");
                        databaseService.getGreaterSal(Integer.parseInt(scan.nextLine()));
                    }

                    case 29 -> {
                        System.out.print("Enter Salary Amount: ");
                        databaseService.getLessSal(Integer.parseInt(scan.nextLine()));
                    }

                    case 30 -> {
                        System.out.println("Enter the File Path of CSV file");
                        databaseService.insertCSV(scan.nextLine());
                    }

                    case 31 -> {
                        System.out.println("Enter the File Path of CSV file");
                        databaseService.updateCSV(scan.nextLine());
                    }


                    case 32 -> {
                        System.out.println("Thank You. Visit Again");
                        isRunning = false;
                    }

                    default -> System.out.println("Incorrect Choice");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong." + e);
        }
    }
}
