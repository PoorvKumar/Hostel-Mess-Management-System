package com.project.service;

import com.project.staff.Staff;
import com.project.staff.cleaner.Cleaner;
import com.project.staff.cook.Cook;
import com.project.staff.serve.Serve;
import com.project.util.DatabaseUtil;
import com.project.util.QueryUtil;

import java.sql.*;

public class DatabaseService {

    DatabaseUtil databaseUtil = new DatabaseUtil();

    public void insertStaff(Staff staff) throws SQLException {
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

    private void printStaff(Staff staff) {
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

    public void getAllStaffs() throws SQLException {
        try (Connection con = databaseUtil.getConnection();
             Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery(QueryUtil.selectAllStaffQuery())) {
            boolean nostaff = true;
            while (resultSet.next()) {
                nostaff = false;
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
            if (nostaff)
                System.out.println("No Staff Data Exist");
        }
    }

    public boolean getStaffById(String ID) throws SQLException {
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

        try (Connection con = databaseUtil.getConnection(); Statement stmt = con.createStatement()) {
            int rows = stmt.executeUpdate(QueryUtil.deleteStaffById(StaffId.toUpperCase()));

            if (rows > 0) {
                System.out.println("Record Deleted Successfully.");
            } else {
                System.out.println("Something went wrong.");
            }
        }
    }

    public void updateStaff(String updateStaffId, Staff staff) throws SQLException {

        try (Connection con = databaseUtil.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(QueryUtil.updateStaffQuery(updateStaffId.toUpperCase()))) {

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


            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                System.out.println("Record Updated Successfully.");
            } else {
                System.out.println("Failed to Update Record.");
            }
        }
    }

    public boolean getStaffByName(String staffName) throws SQLException {
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
        return isFound;
    }

    public boolean getStaffByDesignation(String staffDesignation) throws SQLException {
        boolean isFound = false;

        try (Connection con = databaseUtil.getConnection();
             Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery(QueryUtil.selectStaffByDesignation("%" + staffDesignation + "%"))) {

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
        return isFound;
    }

    public void getStaffByGender(String staffGender) throws SQLException {
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
        }

    }

    public int getTotalCountStaff() throws SQLException {
        try (Connection con = databaseUtil.getConnection();
             Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery(QueryUtil.countTotalStaff())) {
            resultSet.next();
            int count = resultSet.getInt(1);

            return count;
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
             Statement stmt = con.createStatement();
        ) {
            ResultSet rs1 = stmt.executeQuery(QueryUtil.SelectMaxMinAvgSalStaff());
            rs1.next();
            System.out.println("Maximum Salary Of Staff: " + rs1.getInt(1));
            System.out.println("Minimum Salary Of Staff: " + rs1.getInt(2));
            System.out.println("Average Salary Of Staff: " + rs1.getDouble(3) + "\n");

            ResultSet rs2 = stmt.executeQuery(QueryUtil.SelectMaxMinAvgSalStaffbyDesignation());
            while (rs2.next()) {
                System.out.println("-------Designation : " + rs2.getString(1).toUpperCase() + "-------");
                System.out.println("Maximum Salary Of Staff: " + rs2.getInt(2));
                System.out.println("Minimum Salary Of Staff: " + rs2.getInt(3));
                System.out.println("Average Salary Of Staff: " + rs2.getDouble(4) + "\n");
            }

        }
    }

    public void getSalaryByvalue(int staffSal) throws SQLException {
        try (Connection con = databaseUtil.getConnection();
             Statement stmt = con.createStatement();
        ) {
            ResultSet rs=stmt.executeQuery(QueryUtil.countSalStaffByvalue(staffSal));
            rs.next();
            System.out.println("Number Of Staff having Salary = "+staffSal+" : "+rs.getInt(1)+"\n");

            if(rs.getInt(1)>0) {
                System.out.println("--------List of All Staff Whose Salary = "+staffSal+"--------\n");
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

    public void getGreaterSal(int staffSal) throws SQLException {
        try (Connection con = databaseUtil.getConnection();
             Statement stmt = con.createStatement();
        ) {
            ResultSet rs=stmt.executeQuery(QueryUtil.countSalGreaterStaff(staffSal));
            rs.next();
            System.out.println("Number Of Staff having Salary > "+staffSal+" : "+rs.getInt(1)+"\n");

            if(rs.getInt(1 )>0){
                ResultSet resultSet = stmt.executeQuery(QueryUtil.SelectGreaterSalStaff(staffSal));
                System.out.println("--------List of All Staff Whose Salary > "+staffSal+"--------\n");

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

    public void getLessSal(int staffSal) throws SQLException {
        try (Connection con = databaseUtil.getConnection();
             Statement stmt = con.createStatement();
        ) {
            ResultSet rs=stmt.executeQuery(QueryUtil.countSalLessStaff(staffSal));
            rs.next();
            System.out.println("Number Of Staff having Salary < "+staffSal+" : "+rs.getInt(1)+"\n");

            if(rs.getInt(1 )>0){
                ResultSet resultSet = stmt.executeQuery(QueryUtil.SelectLessSalStaff(staffSal));
                System.out.println("--------List of All Staff Whose Salary < "+staffSal+"--------\n");

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


}





