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
                System.out.println("Enter choice:");
                System.out.println("1. Insert");
                System.out.println("2. Select All");
                System.out.println("3. Select Student by a Roll-Number");
                System.out.println("4. Delete Student");
                System.out.println("5. Update Student");
                System.out.println("6. Exit");

                int choice = Integer.parseInt(scan.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("Enter the Roll Number, Name, Age, Gender, Contact Number, Degree, Food Choice(Veg/Non-Veg) of the Student");
                        databaseService.insertStudent(new Student(scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), scan.nextLine(), scan.nextLine()));
                        break;
                    case 2:
                        databaseService.getAllStudents();
                        break;
                    case 3:
                        System.out.println("Enter the Roll-Number of the Student");
                        databaseService.getStudentByRollNumber(scan.nextLine());
                        break;
                    case 4:
                        System.out.println("Enter the Roll-Number of the Student");
                        databaseService.deleteStudentByRollNumber(scan.nextLine());
                        break;
                    case 5:
                        System.out.println("Enter the Roll-Number of the Student");
                        String updateRollNumber = scan.nextLine();
                        boolean isFound = databaseService.getStudentByRollNumber(updateRollNumber);

                        if (isFound) {
                            System.out.println("Enter the Roll Number, Name, Age, Gender, Contact Number, Degree, Food Choice(Veg/Non-Veg) of the Student");
                            databaseService.updateStudent(new Student(scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine(), scan.nextLine(), scan.nextLine()));
                        }
                        break;
                    case 6:
                        System.out.println("Thank You. Visit Again");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Incorrect Choice");
                        break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong." + e);
        }
    }
}
