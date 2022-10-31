package com.project.service;

import com.project.student.Student;
import com.project.util.DatabaseUtil;
import com.project.util.QueryUtil;

import java.sql.*;

public class DatabaseService {

    DatabaseUtil databaseUtil = new DatabaseUtil();

    public void insertStudent(Student student) throws SQLException {

        try (Connection connection = databaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.insertStudentQuery())) {

            preparedStatement.setString(1, student.getRoll_Number());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getGender());
            preparedStatement.setString(5, student.getContact_Info());
            preparedStatement.setString(6, student.getDegree());
            preparedStatement.setString(7, student.getFood_Choice());

            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                System.out.println("Record Created Successfully.");
            } else {
                System.out.println("Insert Record Failed.");
            }
        }
    }

    private void printStudent(Student student) {
        System.out.println("Roll-Number: " + student.getRoll_Number());
        System.out.println("Name: " + student.getName());
        System.out.println("Age: " + student.getAge());
        System.out.println("Gender: " + student.getGender());
        System.out.println("Contact Number: " + student.getContact_Info());
        System.out.println("Degree: " + student.getDegree());
        System.out.println("Food Choice: " + student.getFood_Choice());
        System.out.println("___________________________________________________________");
    }

    public void getAllStudents() throws SQLException {

        try (Connection connection = databaseUtil.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(QueryUtil.selectAllStudentsQuery())) {

            while (resultSet.next()) {
                printStudent(new Student(resultSet.getString("ROLL_NUMBER"), resultSet.getString("NAME"), resultSet.getInt("AGE"), resultSet.getString("GENDER"), resultSet.getString("CONTACT_NUMBER"), resultSet.getString("DEGREE"), resultSet.getString("FOOD_CHOICE")));
            }
        }
    }

    public boolean getStudentByRollNumber(String roll_number) throws SQLException {

        boolean isFound = false;

        try (Connection connection = databaseUtil.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(QueryUtil.selectStudentByRollNumberQuery(roll_number))) {
            if (resultSet.next()) {
                isFound = true;
                printStudent(new Student(resultSet.getString("ROLL_NUMBER"), resultSet.getString("NAME"), resultSet.getInt("AGE"), resultSet.getString("GENDER"), resultSet.getString("CONTACT_NUMBER"), resultSet.getString("DEGREE"), resultSet.getString("FOOD_CHOICE")));
            } else {
                System.out.println("Record not found for Roll-Number" + roll_number);
            }
        }

        return isFound;
    }

    public void deleteStudentByRollNumber(String roll_number) throws SQLException {

        try (Connection connection = databaseUtil.getConnection(); Statement statement = connection.createStatement()) {
            int rows = statement.executeUpdate(QueryUtil.deleteStudentByRollNumberQuery(roll_number));

            if (rows > 0) {
                System.out.println("Record Deleted Successfully.");
            } else {
                System.out.println("Something went wrong.");
            }
        }
    }

    public void updateStudent(Student student) throws SQLException {

        try (Connection connection = databaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.updateStudentQuery(student.getRoll_Number()))) {

            preparedStatement.setString(1, student.getRoll_Number());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getGender());
            preparedStatement.setString(5, student.getContact_Info());
            preparedStatement.setString(6, student.getDegree());
            preparedStatement.setString(7, student.getFood_Choice());

            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                System.out.println("Record Updated Successfully.");
            } else {
                System.out.println("Failed to Update Record.");
            }
        }
    }
}