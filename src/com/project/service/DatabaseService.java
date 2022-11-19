package com.project.service;

import com.project.student.Student;
import com.project.util.DatabaseUtil;
import com.project.util.QueryUtil;
import com.project.staff.Staff;
import com.project.staff.cleaner.Cleaner;
import com.project.staff.cook.Cook;
import com.project.staff.serve.Serve;


import java.sql.*;
import java.io.*;

public class DatabaseService {

    DatabaseUtil databaseUtil = new DatabaseUtil();

    public void insertStudent(Student student) throws SQLException {

        try (Connection connection = databaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.insertStudentQuery())) {

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

    public void getAllStudents(String choice) throws SQLException {

        try (Connection connection = databaseUtil.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet;

            switch (choice) {
                case "roll number" -> {
                    resultSet = statement.executeQuery(QueryUtil.selectAllStudentsQuery());
                    if (!resultSet.next()) {
                        System.out.println("No records found");
                    } else {
                        do {
                            printStudent(new Student(resultSet.getString("ROLL_NUMBER"), resultSet.getString("NAME"), resultSet.getInt("AGE"), resultSet.getString("GENDER"), resultSet.getString("CONTACT_NUMBER"), resultSet.getString("DEGREE"), resultSet.getString("FOOD_CHOICE")));
                        } while (resultSet.next());
                    }
                }
                case "name" -> {
                    resultSet = statement.executeQuery(QueryUtil.selectAllStudentsSortByNameQuery());
                    if (!resultSet.next()) {
                        System.out.println("No records found");
                    } else {
                        do {
                            printStudent(new Student(resultSet.getString("ROLL_NUMBER"), resultSet.getString("NAME"), resultSet.getInt("AGE"), resultSet.getString("GENDER"), resultSet.getString("CONTACT_NUMBER"), resultSet.getString("DEGREE"), resultSet.getString("FOOD_CHOICE")));
                        } while (resultSet.next());
                    }
                }
                case "age" -> {
                    resultSet = statement.executeQuery(QueryUtil.selectAllStudentsSortByAgeQuery());
                    if (!resultSet.next()) {
                        System.out.println("No records found");
                    } else {
                        do {
                            printStudent(new Student(resultSet.getString("ROLL_NUMBER"), resultSet.getString("NAME"), resultSet.getInt("AGE"), resultSet.getString("GENDER"), resultSet.getString("CONTACT_NUMBER"), resultSet.getString("DEGREE"), resultSet.getString("FOOD_CHOICE")));
                        } while (resultSet.next());
                    }
                }
            }
        }
    }

    public boolean getStudentByRollNumber(String roll_number) throws SQLException {

        boolean isFound = false;

        try (Connection connection = databaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QueryUtil.selectStudentByRollNumberQuery(roll_number))) {
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

        try (Connection connection = databaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QueryUtil.selectStudentByNameQuery(name))) {

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

        try (Connection connection = databaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QueryUtil.getStudentTotalCountQuery())) {
            if (!resultSet.next()) {
                System.out.println("No records found");
            } else {
                System.out.println("Total Count of Students: " + resultSet.getInt("COUNT(*)"));
            }
        }
    }

    public void getMaleFemaleCount() throws SQLException {

        try (Connection connection = databaseUtil.getConnection();
             Statement statement1 = connection.createStatement();
             Statement statement2 = connection.createStatement();
             ResultSet resultSet1 = statement1.executeQuery(QueryUtil.getMaleCountQuery());
             ResultSet resultSet2 = statement2.executeQuery(QueryUtil.getFemaleCountQuery())) {
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

        try (Connection connection = databaseUtil.getConnection();
             Statement statement1 = connection.createStatement();
             Statement statement2 = connection.createStatement();
             ResultSet resultSet1 = statement1.executeQuery(QueryUtil.getNonVegCountQuery());
             ResultSet resultSet2 = statement2.executeQuery(QueryUtil.getVegCountQuery())) {
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

        try (Connection connection = databaseUtil.getConnection();
             Statement statement1 = connection.createStatement();
             Statement statement2 = connection.createStatement();
             Statement statement3 = connection.createStatement();
             ResultSet resultSet1 = statement1.executeQuery(QueryUtil.getBTechCountQuery());
             ResultSet resultSet2 = statement2.executeQuery(QueryUtil.getMTechQuery());
             ResultSet resultSet3 = statement3.executeQuery(QueryUtil.getPhDCountQuery())) {
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

        try (Connection connection = databaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QueryUtil.getAverageAgeOfStudents())) {
            if (!resultSet.next()) {
                System.out.println("No records found");
            } else {
                System.out.println("Average Age of Students: " + resultSet.getFloat("AVG(AGE)"));
            }
        }
    }

    public void deleteStudentByRollNumber(String roll_number) throws SQLException {

        try (Connection connection = databaseUtil.getConnection();
             Statement statement = connection.createStatement()) {
            int rows = statement.executeUpdate(QueryUtil.deleteStudentByRollNumberQuery(roll_number));

            if (rows > 0) {
                System.out.println("Record Deleted Successfully.");
            } else {
                System.out.println("Something went wrong.");
            }
        }
    }

    public void updateStudent(String updateRollNumber, Student student) throws SQLException {

        try (Connection connection = databaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.updateStudentQuery(updateRollNumber))) {

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

    public void insertCSVStudent(String file_path) throws SQLException, IOException {

        try (Connection connection = databaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.insertStudentQuery());
             BufferedReader bufferedReader = new BufferedReader(new FileReader(file_path))) {

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

    public boolean updateCSVStudentUtil1(String roll_number) throws SQLException {

        boolean isFound = false;

        try (Connection connection = databaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QueryUtil.selectStudentByRollNumberQuery(roll_number))) {
            if (resultSet.next()) {
                isFound = true;
            }
        }

        return isFound;
    }

    public void updateCSVStudentUtil2(String updateRollNumber, Student student) throws SQLException {

        try (Connection connection = databaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.updateStudentQuery(updateRollNumber))) {

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

    public void updateCSVStudent(String file_path) throws SQLException, IOException {

        try (Connection connection = databaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.insertStudentQuery());
             BufferedReader bufferedReader = new BufferedReader(new FileReader(file_path))) {

            String lineText;

            bufferedReader.readLine();

            while ((lineText = bufferedReader.readLine()) != null) {
                String[] data = lineText.split(",");

                String updateRollNumber = data[0];
                boolean isFound = updateCSVStudentUtil1(updateRollNumber);

                if (isFound) {
                    updateCSVStudentUtil2(updateRollNumber, new Student(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4], data[5], data[6]));
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

    public void insertStaff(Staff staff) throws Exception {
        try (Connection con = databaseUtil.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(QueryUtil.insertStaffQuery())) {

            preparedStatement.setString(1, staff.getID());
            preparedStatement.setString(2, staff.getName());
            preparedStatement.setInt(3, staff.getAge());
            preparedStatement.setString(4, staff.getGender().toUpperCase());
            preparedStatement.setString(5, staff.getContact_Info());
            preparedStatement.setString(6, staff.getDesignation());
            preparedStatement.setString(7, staff.getAddress());
            preparedStatement.setString(8, staff.getDoj());
            preparedStatement.setInt(9, staff.getWork_experience());
            preparedStatement.setInt(10, staff.getSalary());
            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                System.out.println("Record Created Successfully.");
            } else {
                System.out.println("Insert Record Failed.");
            }

        }
    }

    private void printStaff(Staff staff) throws Exception {
        System.out.println("ID: " + staff.getID());
        System.out.println("Name: " + staff.getName());
        System.out.println("Age: " + staff.getAge());
        System.out.println("Gender: " + staff.getGender());
        System.out.println("Contact Number: " + staff.getContact_Info());
        System.out.println("Designation: " + staff.getDesignation());
        System.out.println("Address: " + staff.getAddress());
        System.out.println("Date of Joining: " + staff.getDoj());
        System.out.println("Work Experience: " + staff.getWork_experience());
        System.out.println("Salary: " + staff.getSalary());
        System.out.println("___________________________________________________________");
    }

    public boolean checkId(String str, int num) throws SQLException {
        try (Connection con = databaseUtil.getConnection();
             Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery(QueryUtil.selectStaffByDesignation(str))) {

            while (resultSet.next()) {
                String id = resultSet.getString(1);
                id = id.substring(2);
                int idNo = Integer.parseInt(id);
                if (idNo == num) {
                    return false;
                }
            }

            return true;
        }
    }

    public void getAllStaffs(String str) throws Exception {
        try (Connection con = databaseUtil.getConnection(); Statement stmt = con.createStatement()) {

            ResultSet resultSet;

            if (str.equalsIgnoreCase("name")) {
                resultSet = stmt.executeQuery(QueryUtil.selectAllStaffQueryOrderByName());
            } else if (str.equalsIgnoreCase("salary")) {
                resultSet = stmt.executeQuery(QueryUtil.selectAllStaffQueryOrderBySalary());
            } else {
                resultSet = stmt.executeQuery(QueryUtil.selectAllStaffQuery());
            }

            boolean noStaff = true;

            while (resultSet.next()) {
                noStaff = false;
                if ((resultSet.getString("designation")).equalsIgnoreCase("cook")) {
                    printStaff(new Cook(resultSet.getString("ID"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("gender"),
                            resultSet.getString("contact_Info"),
                            resultSet.getString("designation"),
                            resultSet.getString("Address"),
                            resultSet.getString("doj"),
                            resultSet.getInt("work_experience")));
                }

                if ((resultSet.getString("designation")).equalsIgnoreCase("cleaner")) {
                    printStaff(new Cleaner(resultSet.getString("ID"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("gender"),
                            resultSet.getString("contact_Info"),
                            resultSet.getString("designation"),
                            resultSet.getString("Address"),
                            resultSet.getString("doj"),
                            resultSet.getInt("work_experience")));
                }

                if ((resultSet.getString("designation")).equalsIgnoreCase("serve")) {
                    printStaff(new Serve(resultSet.getString("ID"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("gender"),
                            resultSet.getString("contact_Info"),
                            resultSet.getString("designation"),
                            resultSet.getString("Address"),
                            resultSet.getString("doj"),
                            resultSet.getInt("work_experience")));
                }
            }
            if (noStaff) {
                System.out.println("No Staff Data Exist");
            }
        }
    }

    public boolean getStaffById(String ID) throws Exception {
        boolean isFound = false;

        try (Connection con = databaseUtil.getConnection();
             Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery(QueryUtil.selectStaffById(ID.toUpperCase()))) {

            if (resultSet.next()) {
                isFound = true;
                if ((resultSet.getString("designation")).equalsIgnoreCase("cook")) {
                    printStaff(new Cook(resultSet.getString("ID"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("gender"),
                            resultSet.getString("contact_Info"),
                            resultSet.getString("designation"),
                            resultSet.getString("Address"),
                            resultSet.getString("doj"),
                            resultSet.getInt("work_experience")));
                }

                if ((resultSet.getString("designation")).equalsIgnoreCase("cleaner")) {
                    printStaff(new Cleaner(resultSet.getString("ID"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("gender"),
                            resultSet.getString("contact_Info"),
                            resultSet.getString("designation"),
                            resultSet.getString("Address"),
                            resultSet.getString("doj"),
                            resultSet.getInt("work_experience")));
                }

                if ((resultSet.getString("designation")).equalsIgnoreCase("serve")) {
                    printStaff(new Serve(resultSet.getString("ID"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("gender"),
                            resultSet.getString("contact_Info"),
                            resultSet.getString("designation"),
                            resultSet.getString("Address"),
                            resultSet.getString("doj"),
                            resultSet.getInt("work_experience")));
                }
            } else {
                System.out.println("Record not found for id: " + ID);
            }
        }
        return isFound;
    }

    public void deleteStaffById(String StaffId) throws SQLException {

        try (Connection con = databaseUtil.getConnection();
             Statement stmt = con.createStatement()) {
            int rows = stmt.executeUpdate(QueryUtil.deleteStaffById(StaffId.toUpperCase()));

            if (rows > 0) {
                System.out.println("Record Deleted Successfully.");
            } else {
                System.out.println("Something went wrong.");
            }
        }
    }

    public void updateStaff(String updateStaffId, Staff staff) throws SQLException {

        try (Connection con = databaseUtil.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(QueryUtil.updateStaffQuery(updateStaffId.toUpperCase()))) {

            preparedStatement.setString(1, staff.getID());
            preparedStatement.setString(2, staff.getName());
            preparedStatement.setInt(3, staff.getAge());
            preparedStatement.setString(4, staff.getGender());
            preparedStatement.setString(5, staff.getContact_Info());
            preparedStatement.setString(6, staff.getDesignation());
            preparedStatement.setString(7, staff.getAddress());
            preparedStatement.setString(8, staff.getDoj());
            preparedStatement.setInt(9, staff.getWork_experience());

            try {
                preparedStatement.setInt(10, staff.getSalary());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                System.out.println("Record Updated Successfully.");
            } else {
                System.out.println("Failed to Update Record.");
            }
        }
    }

    public void getStaffByName(String staffName) throws Exception {
        boolean isFound = false;

        try (Connection con = databaseUtil.getConnection();
             Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery(QueryUtil.selectStaffByName("%" + staffName + "%"))) {

            while (resultSet.next()) {
                isFound = true;
                if ((resultSet.getString("designation")).equalsIgnoreCase("cook")) {
                    printStaff(new Cook(resultSet.getString("ID"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("gender"),
                            resultSet.getString("contact_Info"),
                            resultSet.getString("designation"),
                            resultSet.getString("Address"),
                            resultSet.getString("doj"),
                            resultSet.getInt("work_experience")));
                }

                if ((resultSet.getString("designation")).equalsIgnoreCase("cleaner")) {
                    printStaff(new Cleaner(resultSet.getString("ID"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("gender"),
                            resultSet.getString("contact_Info"),
                            resultSet.getString("designation"),
                            resultSet.getString("Address"),
                            resultSet.getString("doj"),
                            resultSet.getInt("work_experience")));
                }

                if ((resultSet.getString("designation")).equalsIgnoreCase("serve")) {
                    printStaff(new Serve(resultSet.getString("ID"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("gender"),
                            resultSet.getString("contact_Info"),
                            resultSet.getString("designation"),
                            resultSet.getString("Address"),
                            resultSet.getString("doj"),
                            resultSet.getInt("work_experience")));
                }
            }
            if (!isFound) {
                System.out.println("No Record Found for Name: " + staffName);
            }
        }
    }

    public void getStaffByDesignation(String staffDesignation, String str) throws Exception {
        boolean isFound = false;

        try (Connection con = databaseUtil.getConnection();
             Statement stmt = con.createStatement()) {

            ResultSet resultSet;

            if (str.equalsIgnoreCase("name")) {
                resultSet = stmt.executeQuery(QueryUtil.selectStaffByDesignationOrderByName("%" + staffDesignation + "%"));
            } else if (str.equalsIgnoreCase("Salary")) {
                resultSet = stmt.executeQuery(QueryUtil.selectStaffByDesignationOrderBySalary("%" + staffDesignation + "%"));
            } else {
                resultSet = stmt.executeQuery(QueryUtil.selectStaffByDesignation("%" + staffDesignation + "%"));
            }

            while (resultSet.next()) {
                isFound = true;
                if ((resultSet.getString("designation")).equalsIgnoreCase("cook")) {
                    printStaff(new Cook(resultSet.getString("ID"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("gender"),
                            resultSet.getString("contact_Info"),
                            resultSet.getString("designation"),
                            resultSet.getString("Address"),
                            resultSet.getString("doj"),
                            resultSet.getInt("work_experience")));
                }

                if ((resultSet.getString("designation")).equalsIgnoreCase("cleaner")) {
                    printStaff(new Cleaner(resultSet.getString("ID"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("gender"),
                            resultSet.getString("contact_Info"),
                            resultSet.getString("designation"),
                            resultSet.getString("Address"),
                            resultSet.getString("doj"),
                            resultSet.getInt("work_experience")));
                }

                if ((resultSet.getString("designation")).equalsIgnoreCase("serve")) {
                    printStaff(new Serve(resultSet.getString("ID"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("gender"),
                            resultSet.getString("contact_Info"),
                            resultSet.getString("designation"),
                            resultSet.getString("Address"),
                            resultSet.getString("doj"),
                            resultSet.getInt("work_experience")));
                }
            }
            if (!isFound) {
                System.out.println("No Record Found for Designation: " + staffDesignation);
            }
        }
    }

    public void getStaffByGender(String staffGender) {
        boolean isFound = false;
        try (Connection con = databaseUtil.getConnection();
             Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery(QueryUtil.selectStaffByGender("%" + staffGender + "%"))) {

            while (resultSet.next()) {
                isFound = true;
                if ((resultSet.getString("designation")).equalsIgnoreCase("cook")) {
                    printStaff(new Cook(resultSet.getString("ID"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("gender"),
                            resultSet.getString("contact_Info"),
                            resultSet.getString("designation"),
                            resultSet.getString("Address"),
                            resultSet.getString("doj"),
                            resultSet.getInt("work_experience")));
                }

                if ((resultSet.getString("designation")).equalsIgnoreCase("cleaner")) {
                    printStaff(new Cleaner(resultSet.getString("ID"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("gender"),
                            resultSet.getString("contact_Info"),
                            resultSet.getString("designation"),
                            resultSet.getString("Address"),
                            resultSet.getString("doj"),
                            resultSet.getInt("work_experience")));
                }

                if ((resultSet.getString("designation")).equalsIgnoreCase("serve")) {
                    printStaff(new Serve(resultSet.getString("ID"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("gender"),
                            resultSet.getString("contact_Info"),
                            resultSet.getString("designation"),
                            resultSet.getString("Address"),
                            resultSet.getString("doj"),
                            resultSet.getInt("work_experience")));
                }
            }
            if (!isFound) {
                System.out.println("Record not found ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public int getTotalCountStaff() throws SQLException {
        try (Connection con = databaseUtil.getConnection();
             Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery(QueryUtil.countTotalStaff())) {
            resultSet.next();

            return resultSet.getInt(1);
        }
    }

    public void getTotalCountStaffByGender() throws SQLException {
        try (Connection con = databaseUtil.getConnection();
             Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery(QueryUtil.countTotalStaffByGender())) {
            resultSet.next();
            System.out.println("Total Number Of Male Staffs : " + resultSet.getInt(1));
            System.out.println("Total Number Of Female Staffs : " + resultSet.getInt(2) + "\n");

        }
    }

    public void getTotalCountStaffByDesignation() throws SQLException {
        try (Connection con = databaseUtil.getConnection();
             Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery(QueryUtil.countTotalStaffByDesignation())) {
            resultSet.next();

            System.out.println("Total Number Of COOK Staff : " + resultSet.getInt(1));
            System.out.println("Total Number Of SERVE Staff : " + resultSet.getInt(2));
            System.out.println("Total Number Of CLEANER Staff : " + resultSet.getInt(3) + "\n");

        }
    }

    public void getAvgAgeStaff() throws SQLException {
        try (Connection con = databaseUtil.getConnection();
             Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery(QueryUtil.avgAgeStaff())) {
            resultSet.next();
            double count = resultSet.getDouble(1);
            System.out.println("Average Age of Staff: " + count + "\n");
        }
    }

    public void getMaxMinAvgSalStaff() throws SQLException {
        try (Connection con = databaseUtil.getConnection();
             Statement stmt = con.createStatement()) {
            ResultSet rs1 = stmt.executeQuery(QueryUtil.SelectMaxMinAvgSalStaff());
            rs1.next();
            System.out.println("Maximum Salary Of Staff: " + rs1.getInt(1));
            System.out.println("Minimum Salary Of Staff: " + rs1.getInt(2));
            System.out.println("Average Salary Of Staff: " + rs1.getDouble(3) + "\n");

            ResultSet rs2 = stmt.executeQuery(QueryUtil.SelectMaxMinAvgSalStaffByDesignation());
            while (rs2.next()) {
                System.out.println("-------Designation : " + rs2.getString(1).toUpperCase() + "-------");
                System.out.println("Maximum Salary Of Staff: " + rs2.getInt(2));
                System.out.println("Minimum Salary Of Staff: " + rs2.getInt(3));
                System.out.println("Average Salary Of Staff: " + rs2.getDouble(4) + "\n");
            }

        }
    }

    public void getSalaryByValue(int staffSal) throws Exception {
        try (Connection con = databaseUtil.getConnection();
             Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(QueryUtil.countSalStaffByValue(staffSal));
            rs.next();
            System.out.println("Number Of Staff having Salary = " + staffSal + " : " + rs.getInt(1) + "\n");

            if (rs.getInt(1) > 0) {
                System.out.println("--------List of All Staff Whose Salary = " + staffSal + "--------\n");
                ResultSet resultSet = stmt.executeQuery(QueryUtil.SelectStaffBySal(staffSal));
                while (resultSet.next()) {
                    if ((resultSet.getString("designation")).equalsIgnoreCase("cook")) {
                        printStaff(new Cook(resultSet.getString("ID"),
                                resultSet.getString("name"),
                                resultSet.getInt("age"),
                                resultSet.getString("gender"),
                                resultSet.getString("contact_Info"),
                                resultSet.getString("designation"),
                                resultSet.getString("Address"),
                                resultSet.getString("doj"),
                                resultSet.getInt("work_experience")));
                    }

                    if ((resultSet.getString("designation")).equalsIgnoreCase("cleaner")) {
                        printStaff(new Cleaner(resultSet.getString("ID"),
                                resultSet.getString("name"),
                                resultSet.getInt("age"),
                                resultSet.getString("gender"),
                                resultSet.getString("contact_Info"),
                                resultSet.getString("designation"),
                                resultSet.getString("Address"),
                                resultSet.getString("doj"),
                                resultSet.getInt("work_experience")));
                    }

                    if ((resultSet.getString("designation")).equalsIgnoreCase("serve")) {
                        printStaff(new Serve(resultSet.getString("ID"),
                                resultSet.getString("name"),
                                resultSet.getInt("age"),
                                resultSet.getString("gender"),
                                resultSet.getString("contact_Info"),
                                resultSet.getString("designation"),
                                resultSet.getString("Address"),
                                resultSet.getString("doj"),
                                resultSet.getInt("work_experience")));
                    }
                }
            }

        }
    }

    public void getGreaterSal(int staffSal) {
        try (Connection con = databaseUtil.getConnection();
             Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(QueryUtil.countSalGreaterStaff(staffSal));
            rs.next();
            System.out.println("Number Of Staff having Salary > " + staffSal + " : " + rs.getInt(1) + "\n");

            if (rs.getInt(1) > 0) {
                ResultSet resultSet = stmt.executeQuery(QueryUtil.SelectGreaterSalStaff(staffSal));
                System.out.println("--------List of All Staff Whose Salary > " + staffSal + "--------\n");

                while (resultSet.next()) {

                    if ((resultSet.getString("designation")).equalsIgnoreCase("cook")) {
                        printStaff(new Cook(resultSet.getString("ID"),
                                resultSet.getString("name"),
                                resultSet.getInt("age"),
                                resultSet.getString("gender"),
                                resultSet.getString("contact_Info"),
                                resultSet.getString("designation"),
                                resultSet.getString("Address"),
                                resultSet.getString("doj"),
                                resultSet.getInt("work_experience")));
                    }

                    if ((resultSet.getString("designation")).equalsIgnoreCase("cleaner")) {
                        printStaff(new Cleaner(resultSet.getString("ID"),
                                resultSet.getString("name"),
                                resultSet.getInt("age"),
                                resultSet.getString("gender"),
                                resultSet.getString("contact_Info"),
                                resultSet.getString("designation"),
                                resultSet.getString("Address"),
                                resultSet.getString("doj"),
                                resultSet.getInt("work_experience")));
                    }

                    if ((resultSet.getString("designation")).equalsIgnoreCase("serve")) {
                        printStaff(new Serve(resultSet.getString("ID"),
                                resultSet.getString("name"),
                                resultSet.getInt("age"),
                                resultSet.getString("gender"),
                                resultSet.getString("contact_Info"),
                                resultSet.getString("designation"),
                                resultSet.getString("Address"),
                                resultSet.getString("doj"),
                                resultSet.getInt("work_experience")));
                    }

                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void getLessSal(int staffSal) throws Exception {
        try (Connection con = databaseUtil.getConnection();
             Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(QueryUtil.countSalLessStaff(staffSal));
            rs.next();
            System.out.println("Number Of Staff having Salary < " + staffSal + " : " + rs.getInt(1) + "\n");

            if (rs.getInt(1) > 0) {
                ResultSet resultSet = stmt.executeQuery(QueryUtil.SelectLessSalStaff(staffSal));
                System.out.println("--------List of All Staff Whose Salary < " + staffSal + "--------\n");

                while (resultSet.next()) {

                    if ((resultSet.getString("designation")).equalsIgnoreCase("cook")) {
                        printStaff(new Cook(resultSet.getString("ID"),
                                resultSet.getString("name"),
                                resultSet.getInt("age"),
                                resultSet.getString("gender"),
                                resultSet.getString("contact_Info"),
                                resultSet.getString("designation"),
                                resultSet.getString("Address"),
                                resultSet.getString("doj"),
                                resultSet.getInt("work_experience")));
                    }

                    if ((resultSet.getString("designation")).equalsIgnoreCase("cleaner")) {
                        printStaff(new Cleaner(resultSet.getString("ID"),
                                resultSet.getString("name"),
                                resultSet.getInt("age"),
                                resultSet.getString("gender"),
                                resultSet.getString("contact_Info"),
                                resultSet.getString("designation"),
                                resultSet.getString("Address"),
                                resultSet.getString("doj"),
                                resultSet.getInt("work_experience")));
                    }

                    if ((resultSet.getString("designation")).equalsIgnoreCase("serve")) {
                        printStaff(new Serve(resultSet.getString("ID"),
                                resultSet.getString("name"),
                                resultSet.getInt("age"),
                                resultSet.getString("gender"),
                                resultSet.getString("contact_Info"),
                                resultSet.getString("designation"),
                                resultSet.getString("Address"),
                                resultSet.getString("doj"),
                                resultSet.getInt("work_experience")));
                    }

                }
            }

        }
    }

    public void insertCSV(String file_path) throws Exception {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file_path))) {

            String lineText;

            bufferedReader.readLine();

            while ((lineText = bufferedReader.readLine()) != null) {

                String[] data = lineText.split(",");


                if ((data[0].toUpperCase()).contains("CK")) {
                    {
                        insertStaff(new Cook(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4], data[5], data[6], data[7], Integer.parseInt(data[8])));
                    }

                } else if ((data[0].toUpperCase()).contains("CR")) {
                    {
                        insertStaff(new Cleaner(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4], data[5], data[6], data[7], Integer.parseInt(data[8])));
                    }

                } else if ((data[0].toUpperCase()).contains("SV")) {
                    {
                        insertStaff(new Serve(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4], data[5], data[6], data[7], Integer.parseInt(data[8])));
                    }

                } else {
                    System.out.println("Invalid ID Choice");
                }
            }

            System.out.println("Data Inserted Successfully");
        }
    }

    public boolean updateCSVUtil1(String ID) throws SQLException {

        boolean isFound = false;

        try (Connection connection = databaseUtil.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(QueryUtil.selectStaffById(ID))) {
            if (resultSet.next()) {
                isFound = true;
            }
        }

        return isFound;
    }

    public void updateCSVUtil2(String updateID, Staff staff) throws Exception {

        try (Connection connection = databaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.updateStaffQuery(updateID))) {

            preparedStatement.setString(1, staff.getID());
            preparedStatement.setString(2, staff.getName());
            preparedStatement.setInt(3, staff.getAge());
            preparedStatement.setString(4, staff.getGender());
            preparedStatement.setString(5, staff.getContact_Info());
            preparedStatement.setString(6, staff.getDesignation());
            preparedStatement.setString(7, staff.getAddress());
            preparedStatement.setString(8, staff.getDoj());
            preparedStatement.setInt(9, staff.getWork_experience());
            preparedStatement.setInt(10, staff.getSalary());
            preparedStatement.executeUpdate();
        }
    }

    public void updateCSV(String file_path) throws Exception {

        try (Connection connection = databaseUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.insertStaffQuery()); BufferedReader bufferedReader = new BufferedReader(new FileReader(file_path))) {

            String lineText;

            bufferedReader.readLine();

            while ((lineText = bufferedReader.readLine()) != null) {

                String[] data = lineText.split(",");
                Staff temp = null;
                String updateID = data[0];
                boolean isFound = updateCSVUtil1(updateID);

                if (isFound) {
                    if ((data[0].toUpperCase()).contains("CK")) {
                        {
                            updateCSVUtil2(updateID, new Cook(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4], data[5], data[6], data[7], Integer.parseInt(data[8])));
                            temp = new Cook(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4], data[5], data[6], data[7], Integer.parseInt(data[8]));
                        }
                    } else if ((data[0].toUpperCase()).contains("CR")) {
                        {
                            updateCSVUtil2(updateID, new Cleaner(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4], data[5], data[6], data[7], Integer.parseInt(data[8])));
                            temp = new Cleaner(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4], data[5], data[6], data[7], Integer.parseInt(data[8]));
                        }
                    } else if ((data[0].toUpperCase()).contains("SV")) {
                        {
                            updateCSVUtil2(updateID, new Serve(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4], data[5], data[6], data[7], Integer.parseInt(data[8])));
                            temp = new Serve(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4], data[5], data[6], data[7], Integer.parseInt(data[8]));
                        }
                    } else {
                        System.out.println("Invalid ID Choice");
                    }
                } else {
                    preparedStatement.setString(1, data[0]);
                    preparedStatement.setString(2, data[1]);
                    preparedStatement.setInt(3, Integer.parseInt(data[2]));
                    preparedStatement.setString(4, data[3]);
                    preparedStatement.setString(5, data[4]);
                    preparedStatement.setString(6, data[5]);
                    preparedStatement.setString(7, data[6]);
                    preparedStatement.setString(8, data[7]);
                    preparedStatement.setInt(9, Integer.parseInt(data[8]));
                    preparedStatement.setInt(10, temp.getSalary());
                    preparedStatement.executeUpdate();
                }
            }
            System.out.println("Data Updated Successfully");
        }
    }
}