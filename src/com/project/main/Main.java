package com.project.main;

import com.project.login.Login;
import com.project.menu.Menu;
import com.project.service.DatabaseService;
import com.project.staff.cleaner.Cleaner;
import com.project.staff.cook.Cook;
import com.project.staff.serve.Serve;
import com.project.student.Student;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DatabaseService databaseService = new DatabaseService();
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        boolean login = true;
        boolean isRunning = false;

        while (login) {
            System.out.println("\nEnter choice:");
            System.out.println("1. Login");
            System.out.println("2. Register");

            try {
                int choice = Integer.parseInt(scan.nextLine());
                switch (choice) {
                    case 1 -> {
                        System.out.println("Please enter Email and Password");
                        if (databaseService.loginCheck(scan.nextLine(), scan.nextLine())) {
                            System.out.println("Login Successful");
                            login = false;
                            isRunning = true;
                        } else {
                            System.out.println("Login Failed. Please check your Email and Password");
                        }
                    }

                    case 2 -> {
                        boolean continueLoop = true;
                        do {
                            try {
                                System.out.println("Enter the Name, Email, Password, Age, Gender, Contact Number");
                                databaseService.registerLoginDetails(new Login(scan.nextLine(), scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine()));
                                continueLoop = false;
                            } catch (NumberFormatException numberFormatException) {
                                System.err.printf("%nException: %s%n", numberFormatException);
                                System.out.println("Please Enter the details with correct format");
                            } catch (Exception e) {
                                System.out.println("Something went wrong..." + e);
                                scan.nextLine();
                            }
                        } while (continueLoop);
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Invalid Input...Press Enter");
                scan.nextLine(); //to clear buffer
            } catch (Exception e) {
                System.out.println("Something went wrong..." + e);
                scan.nextLine();
            }
        }


        try {

            while (isRunning) {
                System.out.println("\nEnter choice:");
                System.out.println("1. Student Menu");
                System.out.println("2. Staff Menu");
                System.out.println("3. Food Menu");
                System.out.println("4. Exit");

                try {
                    int choice = Integer.parseInt(scan.nextLine());

                    switch (choice) {
                        case 1 -> {

                            boolean studentIsRunning = true;
                            while (studentIsRunning) {
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
                                System.out.println("14. Exit");

                                try {
                                    int studentChoice = Integer.parseInt(scan.nextLine());

                                    switch (studentChoice) {
                                        case 1 -> {
                                            boolean continueLoop = true;
                                            do {
                                                try {
                                                    System.out.println("Enter the Roll Number, Name, Age, Gender, Contact Number, Degree, Food Choice(Veg/Non-Veg) of the Student");
                                                    databaseService.insertStudent(new Student(scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), scan.nextLine(), scan.nextLine()));
                                                    continueLoop = false;
                                                } catch (NumberFormatException numberFormatException) {
                                                    System.err.printf("%nException: %s%n", numberFormatException);
                                                    System.out.println("Please Enter the details with correct format");
                                                }
                                            } while (continueLoop);
                                        }

                                        case 2 -> {
                                            System.out.println("\nEnter choice:");
                                            System.out.println("1. Unsorted");
                                            System.out.println("2. Sort by Name");
                                            System.out.println("3. Sort by Age");

                                            int choice2 = Integer.parseInt(scan.nextLine());

                                            switch (choice2) {
                                                case 1 -> databaseService.getAllStudents("notSorted");

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
                                            boolean continueLoop = true;
                                            System.out.println("Enter the Roll-Number of the Student");
                                            String updateRollNumber = scan.nextLine();
                                            boolean isFound = databaseService.getStudentByRollNumber(updateRollNumber);
                                            if (isFound) {
                                                do {
                                                    try {
                                                        System.out.println("Enter th1e Roll Number, Name, Age, Gender, Contact Number, Degree, Food Choice(Veg/Non-Veg) of the Student");
                                                        databaseService.updateStudent(updateRollNumber, new Student(scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), scan.nextLine(), scan.nextLine()));
                                                        continueLoop = false;
                                                    } catch (NumberFormatException numberFormatException) {
                                                        System.err.printf("%nException: %s%n", numberFormatException);
                                                        System.out.println("Please Enter the details with correct format");
                                                    }
                                                } while (continueLoop);
                                            }
                                        }

                                        case 12 -> {
                                            boolean continueLoop = true;
                                            do {
                                                try {
                                                    System.out.println("Enter the File Path of CSV file");
                                                    databaseService.insertCSVStudent(scan.nextLine());
                                                    continueLoop = false;
                                                } catch (FileNotFoundException fileNotFoundException) {
                                                    System.err.printf("%nException: %s%n", fileNotFoundException);
                                                    System.out.println("Please Enter the correct filepath");
                                                    args[1] = scan.nextLine();
                                                }
                                            } while (continueLoop);
                                        }

                                        case 13 -> {
                                            boolean continueLoop = true;
                                            do {
                                                try {
                                                    System.out.println("Enter the File Path of CSV file");
                                                    databaseService.updateCSVStudent(scan.nextLine());
                                                    continueLoop = false;
                                                } catch (FileNotFoundException fileNotFoundException) {
                                                    System.err.printf("%nException: %s%n", fileNotFoundException);
                                                    System.out.println("Please Enter the correct filepath");
                                                    args[1] = scan.nextLine();
                                                }
                                            } while (continueLoop);
                                        }

                                        case 14 -> {
                                            System.out.println("Thank You. Visit Again");
                                            studentIsRunning = false;
                                        }

                                        default -> System.out.println("Incorrect Choice");
                                    }
                                } catch (NumberFormatException numberFormatException) {
                                    System.out.println("Invalid Input...Press Enter");
                                    scan.nextLine(); //to clear buffer
                                } catch (Exception e) {
                                    System.out.println("Something went wrong..." + e);
                                }
                            }
                        }


                        case 2 -> {
                            boolean staffIsRunning = true;
                            while (staffIsRunning) {
                                System.out.println("\nEnter choice:");
                                System.out.println("1. Insert Staff");
                                System.out.println("2. Select all Staff");
                                System.out.println("3. Select Staff by an ID");
                                System.out.println("4. Delete Staff");
                                System.out.println("5. Update Staff");
                                System.out.println("6. Search staff by Name(partial string applicable)");
                                System.out.println("7. Search Staff by Designation");
                                System.out.println("8. Search Staff by Gender");
                                System.out.println("9. Total Number of Staffs");
                                System.out.println("10. Total Number Of Staffs According to Gender");
                                System.out.println("11. Total Number Of Staffs According to Designation");
                                System.out.println("12. Average Age of Staff");
                                System.out.println("13. Maximum, Minimum, Average Salary of Staff ");
                                System.out.println("14. Search Staff By Salary");
                                System.out.println("15. Search Staff whose Salary is greater than or equal to particular Salary");
                                System.out.println("16. Search Staff whose Salary is less than or equal to particular Salary");
                                System.out.println("17. Insert Staff by CSV");
                                System.out.println("18. Update Staff by CSV");
                                System.out.println("19. Exit");

                                try {
                                    int staffChoice = Integer.parseInt(scan.nextLine());

                                    switch (staffChoice) {
                                        case 1 -> {
                                            System.out.println("Enter Designation(cook/serve/cleaner) Case does not matter  : ");
                                            String Des = scan.nextLine();
                                            int num;
                                            if (Des.equalsIgnoreCase("cook")) {

                                                do {
                                                    num = 1000 + rand.nextInt(9000);

                                                } while (databaseService.checkIdFound(Des, num));

                                                String id = "CK" + num;
                                                System.out.println("Enter Name,Age,Gender(m/f),contact_Info,Address,Date of Joining,Work Experience Respectively");
                                                databaseService.insertStaff(new Cook(id, scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), "COOK", scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine())));
                                                System.out.println("ID Generated: CK" + num + "\n");

                                            } else if (Des.equalsIgnoreCase("serve")) {
                                                do {
                                                    num = 1000 + rand.nextInt(9000);

                                                } while (databaseService.checkIdFound(Des, num));

                                                String id = "SV" + num;
                                                System.out.println("Enter Name,Age,Gender(m/f),contact_Info,Address,Date of Joining,Work Experience Respectively");
                                                databaseService.insertStaff(new Serve(id, scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), "SERVE", scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine())));
                                                System.out.println("ID Generated: SV" + num + "\n");

                                            } else if (Des.equalsIgnoreCase("cleaner")) {
                                                do {
                                                    num = 1000 + rand.nextInt(9000);

                                                } while (databaseService.checkIdFound(Des, num));

                                                String id = "CR" + num;
                                                System.out.println("Enter Name,Age,Gender(m/f),contact_Info,Address,Date of Joining,Work Experience Respectively");
                                                databaseService.insertStaff(new Cleaner(id, scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), "CLEANER", scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine())));
                                                System.out.println("ID Generated: CR" + num + "\n");

                                            } else
                                                System.out.println("Invalid Choice");
                                        }

                                        case 2 -> {
                                            System.out.println("Enter Choice");
                                            System.out.println("1. Print ALl the Staff Details Sorted According to Name");
                                            System.out.println("2. Print ALl the Staff Details Sorted According to Salary");
                                            System.out.println("3. Print ALl the Staff Details ");
                                            int choice2 = Integer.parseInt(scan.nextLine());
                                            switch (choice2) {
                                                case 1 -> {
                                                    System.out.println("Details Of ALl Staffs");
                                                    System.out.println("___________________________________________________________");
                                                    databaseService.getAllStaffs("name");
                                                }
                                                case 2 -> {
                                                    System.out.println("Details Of ALl Staffs");
                                                    System.out.println("___________________________________________________________");
                                                    databaseService.getAllStaffs("Salary");
                                                }
                                                case 3 -> {
                                                    System.out.println("Details Of ALl Staffs");
                                                    System.out.println("___________________________________________________________");
                                                    databaseService.getAllStaffs("noOrder");
                                                }
                                                default -> System.out.println("Invalid Choice\n");
                                            }
                                        }

                                        case 3 -> {
                                            System.out.println("Enter ID of a Staff");
                                            databaseService.getStaffById(scan.nextLine());
                                        }

                                        case 4 -> {
                                            System.out.println("Enter ID of the Staff you want to delete");
                                            databaseService.deleteStaffById(scan.nextLine());
                                        }

                                        case 5 -> {
                                            System.out.println("Enter ID of the Staff that you want to update");
                                            String updateStaffId = scan.nextLine();
                                            boolean isFound = databaseService.getStaffById(updateStaffId.toUpperCase());
                                            if (isFound) {

                                                System.out.println("Enter New Designation (if Designation same as previous then enter (Same) ) ");
                                                String Desig = scan.nextLine();
                                                if (Desig.equalsIgnoreCase("same")) {
                                                    System.out.println("Enter New Name,Age,Gender(m/f),contact_Info,Address,Date of Joining,Work Experience Respectively");
                                                    if ((updateStaffId.toUpperCase()).contains("CK")) {
                                                        databaseService.updateStaff(updateStaffId, new Cook(updateStaffId, scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), "COOK", scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine())));
                                                    } else if ((updateStaffId.toUpperCase()).contains("CR")) {
                                                        databaseService.updateStaff(updateStaffId, new Cleaner(updateStaffId, scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), "CLEANER", scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine())));
                                                    } else if ((updateStaffId.toUpperCase()).contains("SV")) {
                                                        databaseService.updateStaff(updateStaffId, new Serve(updateStaffId, scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), "SERVE", scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine())));
                                                    }
                                                } else if (Desig.equalsIgnoreCase("Cook") || Desig.equalsIgnoreCase("Serve") || Desig.equalsIgnoreCase("Cleaner")) {
                                                    int numb;
                                                    if (Desig.equalsIgnoreCase("cook")) {

                                                        do {
                                                            numb = 1000 + rand.nextInt(9000);

                                                        } while (databaseService.checkIdFound(Desig, numb));

                                                        String id2 = "CK" + numb;
                                                        System.out.println("Enter New Name,Age,Gender(m/f),contact_Info,Address,Date of Joining,Work Experience Respectively");
                                                        databaseService.updateStaff(updateStaffId, new Cook(id2, scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), "COOK", scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine())));
                                                        System.out.println("New ID Generated: CK" + numb + "\n");

                                                    } else if (Desig.equalsIgnoreCase("serve")) {
                                                        do {
                                                            numb = 1000 + rand.nextInt(9000);
                                                        } while (databaseService.checkIdFound(Desig, numb));

                                                        String id2 = "SV" + numb;
                                                        System.out.println("Enter New Name,Age,Gender(m/f),contact_Info,Address,Date of Joining,Work Experience Respectively");
                                                        databaseService.updateStaff(updateStaffId, new Serve(id2, scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), "SERVE", scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine())));
                                                        System.out.println("New ID Generated: SV" + numb + "\n");

                                                    } else if (Desig.equalsIgnoreCase("cleaner")) {
                                                        do {
                                                            numb = 1000 + rand.nextInt(9000);

                                                        } while (databaseService.checkIdFound(Desig, numb));

                                                        String id2 = "CR" + numb;
                                                        System.out.println("Enter New Name,Age,Gender(m/f),contact_Info,Address,Date of Joining,Work Experience Respectively");
                                                        databaseService.updateStaff(updateStaffId, new Cleaner(id2, scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), "CLEANER", scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine())));
                                                        System.out.println("New ID Generated: CR" + numb + "\n");

                                                    }
                                                } else
                                                    System.out.println("Invalid Entry\n");
                                            }
                                        }

                                        case 6 -> {
                                            System.out.println("Enter Name of Staff");
                                            databaseService.getStaffByName(scan.nextLine());
                                        }

                                        case 7 -> {
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

                                        case 8 -> {
                                            System.out.print("Enter Gender(m/f): ");
                                            databaseService.getStaffByGender(scan.nextLine());
                                        }

                                        case 9 -> {
                                            System.out.print("Total Number of Staff : ");
                                            int count = databaseService.getTotalCountStaff();
                                            System.out.println(count + "\n");
                                        }

                                        case 10 -> databaseService.getTotalCountStaffByGender();

                                        case 11 -> databaseService.getTotalCountStaffByDesignation();

                                        case 12 -> databaseService.getAvgAgeStaff();

                                        case 13 -> databaseService.getMaxMinAvgSalStaff();

                                        case 14 -> {
                                            System.out.print("Enter Salary Amount : ");
                                            databaseService.getSalaryByvalue(Integer.parseInt(scan.nextLine()));
                                        }

                                        case 15 -> {
                                            System.out.print("Enter Salary Amount: ");
                                            databaseService.getGreaterSal(Integer.parseInt(scan.nextLine()));
                                        }

                                        case 16 -> {
                                            System.out.print("Enter Salary Amount: ");
                                            databaseService.getLessSal(Integer.parseInt(scan.nextLine()));
                                        }

                                        case 17 -> {
                                            System.out.println("Enter the File Path of CSV file");
                                            databaseService.insertCSV(scan.nextLine());
                                        }

                                        case 18 -> {
                                            System.out.println("Enter the File Path of CSV file");
                                            databaseService.updateCSV(scan.nextLine());
                                        }

                                        case 19 -> {
                                            System.out.println("Thank You. Visit Again");
                                            staffIsRunning = false;
                                        }

                                        default -> System.out.println("Incorrect Choice");

                                    }
                                } catch (NumberFormatException numberFormatException) {
                                    System.out.println("Invalid Input...Press Enter");
                                    scan.nextLine(); //to clear buffer
                                } catch (Exception e) {
                                    System.out.println("Something went wrong..." + e);
                                }
                            }
                        }

                        case 3 -> {
                            boolean menuIsRunning = true;

                            Menu menu = new Menu();

                            while (menuIsRunning) {
                                System.out.println("\nEnter choice:");
                                System.out.println("1. Insert Breakfast using CSV");
                                System.out.println("2. Insert Lunch using CSV");
                                System.out.println("3. Insert Snacks using CSV");
                                System.out.println("4. Insert Dinner using CSV");
                                System.out.println("5. Show Menu");
                                System.out.println("6. Show Meal of Day");
                                System.out.println("7. Show Breakfast/Lunch/Snacks/Dinner of Day");
                                System.out.println("8. Show Breakfast");
                                System.out.println("9. Show Lunch");
                                System.out.println("10. Show Snacks");
                                System.out.println("11. Show Dinner");
                                System.out.println("12. Delete any Meal Data");
                                System.out.println("13. Update Breakfast/Lunch/Snacks/Dinner using CSV");
                                System.out.println("14. Exit");

                                try {
                                    int menuChoice = Integer.parseInt(scan.nextLine());

                                    switch (menuChoice) {
                                        case 1 -> {
                                            boolean continueLoop = true;

                                            do {
                                                String path;
                                                try {
                                                    System.out.println("Enter path of breakfast.csv ");
                                                    path = scan.nextLine();
                                                    menu.insertBreakfastCSV(path);
                                                    continueLoop = false;
                                                } catch (Exception e) {
                                                    System.err.printf("%nException: %s%n", e);
                                                    System.out.println("Please Enter the correct filepath");
                                                    path = scan.nextLine();
                                                }
                                            } while (continueLoop);
                                        }
                                        case 2 -> {
                                            boolean continueLoop = true;
                                            do {
                                                String path;
                                                try {
                                                    System.out.println("Enter path of lunch.csv ");
                                                    path = scan.nextLine();
                                                    menu.insertLunchCSV(path);
                                                    continueLoop = false;
                                                } catch (Exception e) {
                                                    System.err.printf("%nException: %s%n", e);
                                                    System.out.println("Please Enter the correct filepath");
                                                    path = scan.nextLine();
                                                }
                                            } while (continueLoop);
                                        }
                                        case 3 -> {
                                            boolean continueLoop = true;
                                            do {
                                                String path;
                                                try {
                                                    System.out.println("Enter path of snacks.csv ");
                                                    path = scan.nextLine();
                                                    menu.insertSnacksCSV(path);
                                                    continueLoop = false;
                                                } catch (Exception e) {
                                                    System.err.printf("%nException: %s%n", e);
                                                    System.out.println("Please Enter the correct filepath");
                                                    path = scan.nextLine();
                                                }
                                            } while (continueLoop);
                                        }
                                        case 4 -> {
                                            boolean continueLoop = true;
                                            do {
                                                String path;
                                                try {
                                                    System.out.println("Enter path of dinner.csv ");
                                                    path = scan.nextLine();
                                                    menu.insertDinnerCSV(path);
                                                    continueLoop = false;
                                                } catch (Exception e) {
                                                    System.err.printf("%nException: %s%n", e);
                                                    System.out.println("Please Enter the correct filepath");
                                                    path = scan.nextLine();
                                                }
                                            } while (continueLoop);
                                        }

                                        case 5 -> menu.showMenu();

                                        case 6 -> {
                                            ArrayList<String> days = new ArrayList<>();
                                            days.add("Monday");
                                            days.add("Tuesday");
                                            days.add("Wednesday");
                                            days.add("Thursday");
                                            days.add("Friday");
                                            days.add("Saturday");
                                            days.add("Sunday");

                                            System.out.println("Enter day number in Monday(0)...Sunday(6)");
                                            int choiceDay = scan.nextInt();

                                            menu.showMealsofDay(days.get(choiceDay));

                                        }

                                        case 7 -> {
                                            ArrayList<String> days = new ArrayList<>();
                                            days.add("Monday");
                                            days.add("Tuesday");
                                            days.add("Wednesday");
                                            days.add("Thursday");
                                            days.add("Friday");
                                            days.add("Saturday");
                                            days.add("Sunday");

                                            System.out.println("Enter meal to be shown: ");

                                            int choiceMealInt = 0;
                                            try {
                                                boolean flagMeal = true;
                                                do {
                                                    String choiceMeal = scan.nextLine();
                                                    if (choiceMeal.equalsIgnoreCase("Breakfast")) {
                                                        choiceMealInt = 1;
                                                        flagMeal = false;
                                                    } else if (choiceMeal.equalsIgnoreCase("Lunch")) {
                                                        choiceMealInt = 2;
                                                        flagMeal = false;
                                                    } else if (choiceMeal.equalsIgnoreCase("Snacks")) {
                                                        choiceMealInt = 3;
                                                        flagMeal = false;
                                                    } else if (choiceMeal.equalsIgnoreCase("Dinner")) {
                                                        choiceMealInt = 4;
                                                        flagMeal = false;
                                                    } else {
                                                        System.out.println("Please enter valid meal...");
                                                    }
                                                }
                                                while (flagMeal);

                                                System.out.println("Enter day number in Monday(0)...Sunday(6)");
                                                int choiceDay = scan.nextInt();
                                                scan.nextLine();

                                                switch (choiceMealInt) {
                                                    case 1 -> {
                                                        menu.showBreakfastofDay(days.get(choiceDay));
                                                    }
                                                    case 2 -> {
                                                        menu.showLunchofDay(days.get(choiceDay));
                                                    }
                                                    case 3 -> {
                                                        menu.showSnacksofDay(days.get(choiceDay));
                                                    }
                                                    case 4 -> {
                                                        menu.showDinnerofDay(days.get(choiceDay));
                                                    }
                                                    default -> {
                                                        System.out.println("Breakfast printed by default");
                                                    }
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Something went wrong" + e);
                                            }
                                        }

                                        case 8 -> menu.showBreakfast();

                                        case 9 -> menu.showLunch();

                                        case 10 -> menu.showSnacks();

                                        case 11 -> menu.showDinner();

                                        case 12 -> {
                                            System.out.println("Enter meal to be deleted: ");

                                            int choiceMealInt = 0;
                                            try {
                                                boolean flagMeal = true;
                                                do {
                                                    String choiceMeal = scan.nextLine();
                                                    if (choiceMeal.equalsIgnoreCase("Breakfast")) {
                                                        choiceMealInt = 1;
                                                        flagMeal = false;
                                                    } else if (choiceMeal.equalsIgnoreCase("Lunch")) {
                                                        choiceMealInt = 2;
                                                        flagMeal = false;
                                                    } else if (choiceMeal.equalsIgnoreCase("Snacks")) {
                                                        choiceMealInt = 3;
                                                        flagMeal = false;
                                                    } else if (choiceMeal.equalsIgnoreCase("Dinner")) {
                                                        choiceMealInt = 4;
                                                        flagMeal = false;
                                                    } else {
                                                        System.out.println("Please enter valid meal...");
                                                    }
                                                }
                                                while (flagMeal);

                                                switch (choiceMealInt) {
                                                    case 1 -> {
                                                        menu.deleteBreakfast();
                                                    }
                                                    case 2 -> {
                                                        menu.deleteLunch();
                                                    }
                                                    case 3 -> {
                                                        menu.deleteSnacks();
                                                    }
                                                    case 4 -> {
                                                        menu.deleteDinner();
                                                    }
                                                    default -> {
                                                        System.out.println("Not deleted");
                                                    }
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Something went wrong " + e);
                                            }
                                        }

                                        case 13 -> {
                                            System.out.println("Enter meal to be updated from CSV: ");

                                            int choiceMealInt = 0;
                                            try {
                                                boolean flagMeal = true;
                                                do {
                                                    String choiceMeal = scan.nextLine();
                                                    if (choiceMeal.equalsIgnoreCase("Breakfast")) {
                                                        choiceMealInt = 1;
                                                        flagMeal = false;
                                                    } else if (choiceMeal.equalsIgnoreCase("Lunch")) {
                                                        choiceMealInt = 2;
                                                        flagMeal = false;
                                                    } else if (choiceMeal.equalsIgnoreCase("Snacks")) {
                                                        choiceMealInt = 3;
                                                        flagMeal = false;
                                                    } else if (choiceMeal.equalsIgnoreCase("Dinner")) {
                                                        choiceMealInt = 4;
                                                        flagMeal = false;
                                                    } else {
                                                        System.out.println("Please enter valid meal...");
                                                    }
                                                }
                                                while (flagMeal);

                                                switch (choiceMealInt) {
                                                    case 1 -> {
                                                        System.out.println("Enter day for which breakfast to be updated");
                                                        String day = scan.nextLine();
                                                        System.out.println("Enter dishes :");
                                                        String dishes = scan.nextLine();

                                                        menu.updateBreakfast(day, dishes);
                                                    }
                                                    case 2 -> {
                                                        System.out.println("Enter day for which breakfast to be updated(firstCase Upper and rest lower)");
                                                        String day = scan.nextLine();
                                                        System.out.println("Enter dishes(dishName1,veg/non-veg|dishName2,veg/non-veg) :");
                                                        String dishes = scan.nextLine();

                                                        menu.updateLunch(day, dishes);
                                                    }
                                                    case 3 -> {
                                                        System.out.println("Enter day for which breakfast to be updated");
                                                        String day = scan.nextLine();
                                                        System.out.println("Enter dishes :");
                                                        String dishes = scan.nextLine();

                                                        menu.updateSnacks(day, dishes);
                                                    }
                                                    case 4 -> {
                                                        System.out.println("Enter day for which breakfast to be updated");
                                                        String day = scan.nextLine();
                                                        System.out.println("Enter dishes :");
                                                        String dishes = scan.nextLine();

                                                        menu.updateDinner(day, dishes);
                                                    }
                                                    default -> {
                                                        System.out.println("Not updated");
                                                    }
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Something went wrong " + e);
                                            }
                                        }

                                        case 14 -> {
                                            System.out.println("Thank You. Visit Again");
                                            menuIsRunning = false;
                                        }

                                        default -> System.out.println("Incorrect Choice");
                                    }
                                } catch (NumberFormatException numberFormatException) {
                                    System.out.println("Invalid Input...Press Enter");
                                    scan.nextLine(); //to clear buffer
                                } catch (Exception e) {
                                    System.out.println("Something went wrong..." + e);
                                }
                            }
                        }

                        case 4 -> {
                            System.out.println("Thank You. Visit Again");
                            isRunning = false;
                        }

                        default -> System.out.println("Incorrect Choice");
                    }

                } catch (NumberFormatException numberFormatException) {
                    System.out.println("Invalid Input...Press Enter");
                    scan.nextLine(); //to clear buffer
                } catch (Exception e) {
                    System.out.println("Something went wrong...");
                    scan.nextLine();
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong..." + e);
        }
    }
}
