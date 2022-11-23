package com.project.util;

import java.util.*;
public class QueryUtil {

    public static String registerLoginDetailsQuery() {
        return "INSERT INTO LOGIN (NAME, EMAIL, PASSWORD, AGE, GENDER, CONTACT_NUMBER) VALUES (?, ?, ?, ?, ?, ?) ";
    }

    public static String loginCheckQuery(String email, String password) {
        return "SELECT * FROM LOGIN WHERE EMAIL = \"" + email + "\" AND PASSWORD = \"" + password + "\"";
    }

    public static String insertStudentQuery() {
        return "INSERT INTO STUDENT (ROLL_NUMBER, NAME, AGE, GENDER, CONTACT_NUMBER, DEGREE, FOOD_CHOICE) VALUES (?, ?, ?, ?, ?, ?, ?) ";
    }

    public static String selectAllStudentsQuery() {
        return "SELECT * FROM STUDENT";
    }

    public static String selectAllStudentsSortByNameQuery() {
        return "SELECT * FROM STUDENT ORDER BY NAME";
    }

    public static String selectAllStudentsSortByAgeQuery() {
        return "SELECT * FROM STUDENT ORDER BY AGE";
    }


    public static String selectStudentByRollNumberQuery(String roll_number) {
        return "SELECT * FROM STUDENT WHERE ROLL_NUMBER = \"" + roll_number + "\"";
    }

    public static String selectStudentByNameQuery(String name) {
        return "SELECT * FROM STUDENT WHERE NAME LIKE \"%" + name + "%\"";
    }

    public static String getStudentTotalCountQuery() {
        return "SELECT COUNT(*) FROM STUDENT";
    }

    public static String getMaleCountQuery() {
        return "SELECT COUNT(*) FROM STUDENT WHERE GENDER = \"MALE\"";
    }

    public static String getFemaleCountQuery() {
        return "SELECT COUNT(*) FROM STUDENT WHERE GENDER = \"FEMALE\"";
    }

    public static String getNonVegCountQuery() {
        return "SELECT COUNT(*) FROM STUDENT WHERE FOOD_CHOICE = \"NON-VEG\"";
    }

    public static String getVegCountQuery() {
        return "SELECT COUNT(*) FROM STUDENT WHERE FOOD_CHOICE = \"VEG\"";
    }

    public static String getBTechCountQuery() {
        return "SELECT COUNT(*) FROM STUDENT WHERE DEGREE = \"B.Tech\"";
    }

    public static String getMTechQuery() {
        return "SELECT COUNT(*) FROM STUDENT WHERE DEGREE = \"M.Tech\"";
    }

    public static String getPhDCountQuery() {
        return "SELECT COUNT(*) FROM STUDENT WHERE DEGREE = \"PhD\"";
    }

    public static String getAverageAgeOfStudents() {
        return "SELECT AVG(AGE) FROM STUDENT";
    }

    public static String deleteStudentByRollNumberQuery(String roll_number) {
        return "DELETE FROM STUDENT WHERE ROLL_NUMBER = \"" + roll_number + "\"";
    }

    public static String updateStudentQuery(String roll_number) {
        return "UPDATE STUDENT SET ROLL_NUMBER = ?, NAME = ?, AGE = ?, GENDER = ?, CONTACT_NUMBER = ?, DEGREE = ?, FOOD_CHOICE = ? WHERE ROLL_NUMBER = \"" + roll_number + "\"";
    }

    public static String insertStaffQuery() {
        return "INSERT INTO Staff (ID,name,age,gender,contact_Info,designation,Address,doj,work_experience,Salary) VALUES (?,?,?,?,?,?,?,?,?,?) ";
    }

    public static String selectAllStaffQuery() {
        return "SELECT * FROM Staff";
    }

    public static String selectAllStaffQueryOrderBySalary() {
        return "SELECT * FROM Staff ORDER BY salary";
    }

    public static String selectAllStaffQueryOrderByName() {
        return "SELECT * FROM Staff ORDER BY name";
    }


    public static String selectStaffById(String staffId) {
        return "SELECT * FROM Staff WHERE ID = \"" + staffId + "\"";
    }

    public static String deleteStaffById(String staffId) {
        return "DELETE FROM Staff WHERE ID = \"" + staffId + "\"";
    }

    public static String updateStaffQuery(String staffId) {
        return "UPDATE Staff SET ID = ?, name = ?, age = ?, gender = ?, contact_Info = ?, designation = ?, Address = ?, doj=?, work_experience=?, Salary=? WHERE ID = \"" + staffId + "\"";
    }

    public static String selectStaffByName(String staffName) {
        return "SELECT * FROM Staff WHERE name LIKE \"" + staffName + "\"";
    }

    public static String selectStaffByDesignation(String staffDesignation) {
        return "SELECT * FROM Staff WHERE designation LIKE \"" + staffDesignation + "\"";
    }

    public static String selectStaffByDesignationOrderByName(String staffDesignation) {
        return "SELECT * FROM Staff WHERE designation LIKE \"" + staffDesignation + "\" ORDER BY name";
    }

    public static String selectStaffByDesignationOrderBySalary(String staffDesignation) {
        return "SELECT * FROM Staff WHERE designation LIKE \"" + staffDesignation + "\" ORDER BY Salary";
    }

    public static String selectStaffByGender(String staffGender) {
        return "SELECT * FROM Staff WHERE gender LIKE \"" + staffGender + "\"";
    }

    public static String countTotalStaff() {
        return "SELECT COUNT(ID) AS Total_Staff FROM Staff";
    }

    public static String countTotalStaffByGender() {
        return "SELECT COUNT(CASE WHEN gender='m' THEN 1 END) AS Male_Count , COUNT(CASE WHEN gender='f' THEN 1 END) AS Female_Count FROM Staff";
    }

    public static String countTotalStaffByDesignation() {
        return "SELECT COUNT(CASE WHEN designation='COOK' THEN 1 END) AS Cook_Count ," +
                " COUNT(CASE WHEN designation='SERVE' THEN 1 END) AS Serve_Count , " +
                "COUNT(CASE WHEN designation='CLEANER' THEN 1 END) AS Cleaner_Count FROM STAFF";
    }

    public static String avgAgeStaff() {
        return "SELECT AVG(age) AS Average_Age FROM Staff";
    }

    public static String SelectMaxMinAvgSalStaff() {
        return "SELECT MAX(Salary),MIN(Salary), AVG(Salary) FROM STAFF";
    }

    public static String SelectMaxMinAvgSalStaffByDesignation() {
        return "SELECT designation , MAX(Salary) , MIN(Salary) , AVG(Salary) FROM Staff GROUP BY designation";
    }

    public static String countSalStaffByValue(int staffSal) {
        return "SELECT COUNT(*) FROM Staff WHERE Salary = \"" + staffSal + "\" ";
    }

    public static String SelectStaffBySal(int staffSal) {
        return "SELECT * FROM Staff WHERE Salary = \"" + staffSal + "\" ";
    }

    public static String countSalGreaterStaff(int staffSal) {
        return "SELECT COUNT(*) FROM Staff WHERE Salary > \"" + staffSal + "\" ";
    }

    public static String SelectGreaterSalStaff(int staffSal) {
        return "SELECT * FROM Staff WHERE Salary > \"" + staffSal + "\" ";
    }

    public static String countSalLessStaff(int staffSal) {
        return "SELECT COUNT(*) FROM Staff WHERE Salary < \"" + staffSal + "\" ";
    }

    public static String SelectLessSalStaff(int staffSal) {
        return "SELECT * FROM Staff WHERE Salary < \"" + staffSal + "\" ";
    }

    public static ArrayList<String> showMealsofDay(String day) //used ArrayList to  store queries for different tables
    {
        ArrayList<String> meals = new ArrayList<String>(4);
        meals.add(showBreakfastofDay(day));
        meals.add(showLunchofDay(day));
        meals.add(showSnacksofDay(day));
        meals.add(showDinnerofDay(day));

        return meals;
    }

    public static String showBreakfastofDay(String day) {
        return "select * from breakfast where day=\"" + day + "\"";
    }

    public static String showLunchofDay(String day) {
        return "select * from lunch where day=\"" + day + "\"";
    }

    public static String showSnacksofDay(String day) {
        return "select * from snacks where day=\"" + day + "\"";
    }

    public static String showDinnerofDay(String day) {
        return "select * from dinner where day=\"" + day + "\"";
    }

    public static String insertBreakfastCSV() {
        return "insert into breakfast values(?,?)";
    }

    public static String insertLunchCSV() {
        return "insert into lunch values(?,?)";
    }

    public static String insertSnacksCSV() {
        return "insert into snacks values(?,?)";
    }

    public static String insertDinnerCSV() {
        return "insert into dinner values(?,?)";
    }

    public static String deleteBreakfast() {
        return "delete from breakfast";
    }

    public static String deleteLunch() {
        return "delete from lunch";
    }

    public static String deleteSnacks() {
        return "delete from snacks";
    }

    public static String deleteDinner() {
        return "delete from dinner";
    }

    public static String updateBreakfast() {
        return "update breakfast set dishes=? where day=?";
    }

    public static String updateLunch() {
        return "update lunch set dishes=? where day=?";
    }

    public static String updateSnacks() {
        return "update snacks set dishes=? where day=?";
    }

    public static String updateDinner() {
        return "update dinners set dishes=? where day=?";
    }
}
