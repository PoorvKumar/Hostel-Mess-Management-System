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

    public static String deleteStudentByRollNumberQuery(String roll_number) {
        return "DELETE FROM STUDENT WHERE ROLL_NUMBER = \"" + roll_number + "\"";
    }

    public static String updateStudentQuery(String roll_number) {
        return "UPDATE STUDENT SET ROLL_NUMBER = ?, NAME = ?, AGE = ?, GENDER = ?, CONTACT_NUMBER = ?, DEGREE = ?, FOOD_CHOICE = ? WHERE ROLL_NUMBER = \"" + roll_number + "\"";
    }
}