package com.project.service;

import com.project.student.Student;
import com.project.util.DatabaseUtil;
import com.project.util.QueryUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

            if (!resultSet.next()) {
                System.out.println("No records found");
            } else {
                do {
                    printStudent(new Student(resultSet.getString("ROLL_NUMBER"), resultSet.getString("NAME"), resultSet.getInt("AGE"), resultSet.getString("GENDER"), resultSet.getString("CONTACT_NUMBER"), resultSet.getString("DEGREE"), resultSet.getString("FOOD_CHOICE")));
                } while (resultSet.next());
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
                System.out.println("Record not found for Roll-Number " + roll_number);
            }
        }

        return isFound;
    }

    public void getStudentByName(String name) throws SQLException {

        try (Connection connection = databaseUtil.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(QueryUtil.selectStudentByNameQuery(name))) {

            if (!resultSet.next()) {
                System.out.println("Record not found for Name " + name);
            } else {
                do {
                    printStudent(new Student(resultSet.getString("ROLL_NUMBER"), resultSet.getString("NAME"), resultSet.getInt("AGE"), resultSet.getString("GENDER"), resultSet.getString("CONTACT_NUMBER"), resultSet.getString("DEGREE"), resultSet.getString("FOOD_CHOICE")));
                } while (resultSet.next());
            }
        }

    }

    public void getStudentTotalCount() throws SQLException {

        try (Connection connection = databaseUtil.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(QueryUtil.getStudentTotalCountQuery())) {
            if (!resultSet.next()) {
                System.out.println("No records found");
            } else {
                System.out.println("Total Count of Students: " + resultSet.getInt("COUNT(*)"));
            }
        }
    }

    public void getMaleFemaleCount() throws SQLException {

        try (Connection connection = databaseUtil.getConnection(); Statement statement1 = connection.createStatement(); Statement statement2 = connection.createStatement(); ResultSet resultSet1 = statement1.executeQuery(QueryUtil.getMaleCountQuery()); ResultSet resultSet2 = statement2.executeQuery(QueryUtil.getFemaleCountQuery())) {
            if (!resultSet1.next()) {
                System.out.println("No records found");
            } else {
                System.out.println("Count of Male Students: " + resultSet1.getInt("COUNT(*)"));
            }

            if (!resultSet2.next()) {
                System.out.println("No records found");
            } else {
                System.out.println("Count of Female Students: " + resultSet2.getInt("COUNT(*)"));
            }
        }
    }

    public void getVegNonVegCount() throws SQLException {

        try (Connection connection = databaseUtil.getConnection(); Statement statement1 = connection.createStatement(); Statement statement2 = connection.createStatement(); ResultSet resultSet1 = statement1.executeQuery(QueryUtil.getNonVegCountQuery()); ResultSet resultSet2 = statement2.executeQuery(QueryUtil.getVegCountQuery())) {
            if (!resultSet1.next()) {
                System.out.println("No records found");
            } else {
                System.out.println("Count of Non-Veg Students: " + resultSet1.getInt("COUNT(*)"));
            }

            if (!resultSet2.next()) {
                System.out.println("No records found");
            } else {
                System.out.println("Count of Veg Students: " + resultSet2.getInt("COUNT(*)"));
            }
        }
    }

    public void getBTechMTechPhDCount() throws SQLException {

        try (Connection connection = databaseUtil.getConnection(); Statement statement1 = connection.createStatement(); Statement statement2 = connection.createStatement(); Statement statement3 = connection.createStatement(); ResultSet resultSet1 = statement1.executeQuery(QueryUtil.getBTechCountQuery()); ResultSet resultSet2 = statement2.executeQuery(QueryUtil.getMTechQuery()); ResultSet resultSet3 = statement3.executeQuery(QueryUtil.getPhDCountQuery())) {
            if (!resultSet1.next()) {
                System.out.println("No records found");
            } else {
                System.out.println("Count of B.Tech Students: " + resultSet1.getInt("COUNT(*)"));
            }

            if (!resultSet2.next()) {
                System.out.println("No records found");
            } else {
                System.out.println("Count of M.Tech Students: " + resultSet2.getInt("COUNT(*)"));
            }

            if (!resultSet3.next()) {
                System.out.println("No records found");
            } else {
                System.out.println("Count of PhD Students: " + resultSet3.getInt("COUNT(*)"));
            }
        }
    }

    public void getAverageAgeOfStudents() throws SQLException {

        try (Connection connection = databaseUtil.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(QueryUtil.getAverageAgeOfStudents())) {
            if (!resultSet.next()) {
                System.out.println("No records found");
            } else {
                System.out.println("Average Age of Students: " + resultSet.getFloat("AVG(AGE)"));
            }
        }
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

    public void updateStudent(String updateRollNumber, Student student) throws SQLException {

        try (Connection connection = databaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.updateStudentQuery(updateRollNumber))) {

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

    public void insertCSV(String file_path) throws SQLException, IOException {

        try (Connection connection = databaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.insertStudentQuery()); BufferedReader bufferedReader = new BufferedReader(new FileReader(file_path))) {

            String lineText;

            bufferedReader.readLine();

            while ((lineText = bufferedReader.readLine()) != null) {
                String[] data = lineText.split(",");

                preparedStatement.setString(1, data[0]);
                preparedStatement.setString(2, data[1]);
                preparedStatement.setInt(3, Integer.parseInt(data[2]));
                preparedStatement.setString(4, data[3]);
                preparedStatement.setString(5, data[4]);
                preparedStatement.setString(6, data[5]);
                preparedStatement.setString(7, data[6]);

                preparedStatement.executeUpdate();
            }

            System.out.println("Data Inserted Successfully");
        }
    }

    public boolean updateCSVutil1(String roll_number) throws SQLException {

        boolean isFound = false;

        try (Connection connection = databaseUtil.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(QueryUtil.selectStudentByRollNumberQuery(roll_number))) {
            if (resultSet.next()) {
                isFound = true;
            }
        }

        return isFound;
    }

    public void updateCSVutil2(String updateRollNumber, Student student) throws SQLException {

        try (Connection connection = databaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.updateStudentQuery(updateRollNumber))) {

            preparedStatement.setString(1, student.getRoll_Number());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getGender());
            preparedStatement.setString(5, student.getContact_Info());
            preparedStatement.setString(6, student.getDegree());
            preparedStatement.setString(7, student.getFood_Choice());

            preparedStatement.executeUpdate();
        }
    }

    public void updateCSV(String file_path) throws SQLException, IOException {

        try (Connection connection = databaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.insertStudentQuery()); BufferedReader bufferedReader = new BufferedReader(new FileReader(file_path))) {

            String lineText;

            bufferedReader.readLine();

            while ((lineText = bufferedReader.readLine()) != null) {
                String[] data = lineText.split(",");

                String updateRollNumber = data[0];
                boolean isFound = updateCSVutil1(updateRollNumber);

                if (isFound) {
                    updateCSVutil2(updateRollNumber, new Student(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4], data[5], data[6]));
                } else {
                    preparedStatement.setString(1, data[0]);
                    preparedStatement.setString(2, data[1]);
                    preparedStatement.setInt(3, Integer.parseInt(data[2]));
                    preparedStatement.setString(4, data[3]);
                    preparedStatement.setString(5, data[4]);
                    preparedStatement.setString(6, data[5]);
                    preparedStatement.setString(7, data[6]);

                    preparedStatement.executeUpdate();
                }
            }
            System.out.println("Data Updated Successfully");
        }
    }
}