package com.project.util;


import java.sql.ResultSet;
import java.sql.Statement;

public class QueryUtil {
    public static String insertStaffQuery(){
       return "INSERT INTO Staff (ID,name,age,gender,contact_Info,designation,Address,doj,work_experience,Salary) VALUES (?,?,?,?,?,?,?,?,?,?) ";
    }

    public static String selectAllStaffQuery(){
        return "SELECT * FROM Staff";
    }

    public static String selectStaffById(String staffId)
    {
        return "SELECT * FROM Staff WHERE ID = \"" + staffId + "\"";
    }

    public static String deleteStaffById(String staffId) {
        return "DELETE FROM Staff WHERE ID = \"" + staffId + "\"";
    }

    public static String updateStaffQuery(String staffId) {
        return "UPDATE Staff SET ID = ?, name = ?, age = ?, gender = ?, contact_Info = ?, designation = ?, Address = ?, doj=?, work_experience=?, Salary=? WHERE ID = \"" + staffId + "\"";
    }

    public static String selectStaffByName(String staffName)
    {
        return "SELECT * FROM Staff WHERE name LIKE \"" + staffName +"\"";
    }

    public static String selectStaffByDesignation(String staffDesignation)
    {
        return "SELECT * FROM Staff WHERE designation LIKE \"" + staffDesignation +"\"";
    }

    public static String selectStaffByGender(String staffGender)
    {
        return "SELECT * FROM Staff WHERE gender LIKE \"" + staffGender +"\"";
    }

    public static String countTotalStaff()
    {
        return "SELECT COUNT(ID) AS Total_Staff FROM Staff";
    }

    public static String countTotalStaffByGender()
    {
        return "SELECT COUNT(CASE WHEN gender='m' THEN 1 END) AS Male_Count , COUNT(CASE WHEN gender='f' THEN 1 END) AS Female_Count FROM Staff";
    }

    public static String countTotalStaffByDesignation()
    {
        return "SELECT COUNT(CASE WHEN designation='COOK' THEN 1 END) AS Cook_Count ," +
                " COUNT(CASE WHEN designation='SERVE' THEN 1 END) AS Serve_Count , " +
                "COUNT(CASE WHEN designation='CLEANER' THEN 1 END) AS Cleaner_Count FROM STAFF";
    }

    public static String avgAgeStaff()
    {
        return "SELECT AVG(age) AS Average_Age FROM Staff";
    }

    public static String SelectMaxMinAvgSalStaff()
    {
        return "SELECT MAX(Salary),MIN(Salary), AVG(Salary) FROM STAFF";
    }

    public static String SelectMaxMinAvgSalStaffbyDesignation()
    {
        return "SELECT designation , MAX(Salary) , MIN(Salary) , AVG(Salary) FROM Staff GROUP BY designation";
    }

    public static String countSalStaffByvalue(int staffSal)
    {
        return "SELECT COUNT(*) FROM Staff WHERE Salary = \"" + staffSal + "\" ";
    }
    public static String SelectStaffBySal(int staffSal)
    {
        return "SELECT * FROM Staff WHERE Salary = \"" + staffSal + "\" ";
    }
    public static String countSalGreaterStaff(int staffSal)
    {
        return "SELECT COUNT(*) FROM Staff WHERE Salary > \"" + staffSal + "\" ";
    }

    public static String SelectGreaterSalStaff(int staffSal)
    {
        return "SELECT * FROM Staff WHERE Salary > \"" + staffSal + "\" ";
    }

    public static String countSalLessStaff(int staffSal)
    {
        return "SELECT COUNT(*) FROM Staff WHERE Salary < \"" + staffSal + "\" ";
    }

    public static String SelectLessSalStaff(int staffSal)
    {
        return "SELECT * FROM Staff WHERE Salary < \"" + staffSal + "\" ";
    }

}
