package com.project.util;

public class QueryUtil {
    public static String insertStudentQuery() {
        return "INSERT INTO STUDENT (ROLL_NUMBER, NAME, AGE, GENDER, CONTACT_NUMBER, DEGREE, FOOD_CHOICE) VALUES (?, ?, ?, ?, ?, ?, ?) ";
    }

    public static String selectAllStudentsQuery() {
        return "SELECT * FROM STUDENT";
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
}