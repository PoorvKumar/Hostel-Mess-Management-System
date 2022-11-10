package com.project.main;

import com.project.service.DatabaseService;
import com.project.student.Student;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DatabaseService databaseService = new DatabaseService();

        try (Scanner scan = new Scanner(System.in)) {

            boolean isRunning = true;

            while (isRunning) {
                System.out.println("\nEnter choice:");
                System.out.println("1. Insert Student");
                System.out.println("2. Select All");
                System.out.println("3. Select Student by a Roll-Number");
                System.out.println("4. Select Student by a Name");
                System.out.println("5. Display the Total Count of Students");
                System.out.println("6. Display the Count of Male and Female Students");
                System.out.println("7. Display the Count of Non-Veg and Veg Students");
                System.out.println("8. Display the Count of B.Tech, M.Tech and PhD Students");
                System.out.println("9. Display the Average Age of Students");
                System.out.println("10. Delete Student by a Roll-Number");
                System.out.println("11. Update Student by a Roll-Number");
                System.out.println("12. Insert using CSV");
                System.out.println("13. Update using CSV");
                System.out.println("14. Exit");

                int choice = Integer.parseInt(scan.nextLine());

                switch (choice) {
                    case 1 -> {
                        System.out.println("Enter the Roll Number, Name, Age, Gender, Contact Number, Degree, Food Choice(Veg/Non-Veg) of the Student");
                        databaseService.insertStudent(new Student(scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), scan.nextLine(), scan.nextLine()));
                    }

                    case 2 -> databaseService.getAllStudents();

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
                        databaseService.insertCSV(scan.nextLine());
                    }

                    case 13 -> {
                        System.out.println("Enter the File Path of CSV file");
                        databaseService.updateCSV(scan.nextLine());
                    }

                    case 14 -> {
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
