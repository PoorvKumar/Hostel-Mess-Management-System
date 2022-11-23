package com.project.menu;

import java.util.*;
import java.sql.*;
import java.io.*;

import com.project.food.Food;
import com.project.util.*;

public class Menu {
    DatabaseUtil databaseutil = new DatabaseUtil();

    public void showMealsofDay(String day) {
        try {
            Connection conn = databaseutil.getConnection();
            Statement st = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);

            ArrayList<String> meals = QueryUtil.showMealsofDay(day);
            ArrayList<Food> arrFood = new ArrayList<>();

            ArrayList<String> mealStr = new ArrayList<>();
            mealStr.add("Breakfast");
            mealStr.add("Lunch");
            mealStr.add("Snacks");
            mealStr.add("Dinner");
            int k = 0;

            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("Menu for " + day + " is -> ");
            System.out.println("--------------------------------------------------------------------------------");

            for (String x : meals) {
                ResultSet rs = st.executeQuery(x);
                while (rs.next()) {
                    StringTokenizer stk = new StringTokenizer(rs.getString(2), "|");

                    while (stk.hasMoreTokens()) {
                        StringTokenizer stkFood = new StringTokenizer(stk.nextToken(), ",");
                        while (stkFood.hasMoreTokens()) {
                            // System.out.print(stkFood.nextToken()+" ");
                            arrFood.add(new Food(stkFood.nextToken(), stkFood.nextToken()));
                        }
                    }
                }
                // menuMap.put(mealStr.get(k),arrFood);
                System.out.print(mealStr.get(k) + " -> ");
                System.out.println(arrFood);
                k++;
                arrFood.clear();
            }
            System.out.println("--------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("Something went wrong." + e);
        }
    }

    //showBreakfast()
    public void showBreakfast() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("BreakFast Menu -> ");
        System.out.println("--------------------------------------------------------------------------------");

        try {
            String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

            for (String str : days) {
                // System.out.println(str+" ->  "+breakfastMap.get(str));
                showBreakfastofDay(str);
            }
            System.out.println("--------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("Something wrong");
        }
    }

    //showbreakfastday()
    public void showBreakfastofDay(String day) {
        try {
            Connection conn = databaseutil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(QueryUtil.showBreakfastofDay(day));

            ArrayList<Food> arrFood = new ArrayList<Food>();

            while (rs.next()) {
                StringTokenizer stk = new StringTokenizer(rs.getString(2), "|");

                while (stk.hasMoreTokens()) {
                    StringTokenizer stkFood = new StringTokenizer(stk.nextToken(), ",");
                    while (stkFood.hasMoreTokens()) {
                        arrFood.add(new Food(stkFood.nextToken(), stkFood.nextToken()));
                    }
                }
            }

            System.out.println(day + " -> " + arrFood);
        } catch (Exception e) {
            System.out.println("Something went wrong." + e);
        }
    }

    //showLunch()
    public void showLunch() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Lunch Menu -> ");
        System.out.println("--------------------------------------------------------------------------------");

        try {
            String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

            for (String str : days) {
                // System.out.println(str+" ->  "+breakfastMap.get(str));
                showLunchofDay(str);
            }
            System.out.println("--------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("Something wrong");
        }
    }

    //showbreakfastday()
    public void showLunchofDay(String day) {
        try {
            Connection conn = databaseutil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(QueryUtil.showLunchofDay(day));
            ArrayList<Food> arrFood = new ArrayList<Food>();

            while (rs.next()) {
                StringTokenizer stk = new StringTokenizer(rs.getString(2), "|");

                while (stk.hasMoreTokens()) {
                    StringTokenizer stkFood = new StringTokenizer(stk.nextToken(), ",");
                    while (stkFood.hasMoreTokens()) {
                        // System.out.print(stkFood.nextToken()+" ");
                        arrFood.add(new Food(stkFood.nextToken(), stkFood.nextToken()));
                    }
                }
            }

            System.out.println(day + " -> " + arrFood);
        } catch (Exception e) {
            System.out.println("Something went wrong." + e);
        }
    }

    //showBreakfast()
    public void showSnacks() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Snacks Menu -> ");
        System.out.println("--------------------------------------------------------------------------------");

        try {
            String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

            for (String str : days) {
                // System.out.println(str+" ->  "+breakfastMap.get(str));
                showSnacksofDay(str);
            }
            System.out.println("--------------------------------------------------------------------------------");

        } catch (Exception e) {
            System.out.println("Something wrong");
        }
    }

    //showbreakfastday()
    public void showSnacksofDay(String day) {
        try {
            Connection conn = databaseutil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(QueryUtil.showSnacksofDay(day));

            ArrayList<Food> arrFood = new ArrayList<Food>();

            while (rs.next()) {
                StringTokenizer stk = new StringTokenizer(rs.getString(2), "|");

                while (stk.hasMoreTokens()) {
                    StringTokenizer stkFood = new StringTokenizer(stk.nextToken(), ",");
                    while (stkFood.hasMoreTokens()) {
                        // System.out.print(stkFood.nextToken()+" ");
                        arrFood.add(new Food(stkFood.nextToken(), stkFood.nextToken()));
                    }
                }
            }

            System.out.println(day + " -> " + arrFood);
        } catch (Exception e) {
            System.out.println("Something went wrong." + e);
        }
    }

    //showBreakfast()
    public void showDinner() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Dinner Menu -> ");
        System.out.println("--------------------------------------------------------------------------------");

        try {
            String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

            for (String str : days) {
                // System.out.println(str+" ->  "+breakfastMap.get(str));
                showDinnerofDay(str);
            }
            System.out.println("--------------------------------------------------------------------------------");

        } catch (Exception e) {
            System.out.println("Something wrong");
        }
    }

    //showbreakfastday()
    public void showDinnerofDay(String day) {
        try {
            Connection conn = databaseutil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(QueryUtil.showDinnerofDay(day));

            ArrayList<Food> arrFood = new ArrayList<Food>();

            while (rs.next()) {
                StringTokenizer stk = new StringTokenizer(rs.getString(2), "|");

                while (stk.hasMoreTokens()) {
                    StringTokenizer stkFood = new StringTokenizer(stk.nextToken(), ",");
                    while (stkFood.hasMoreTokens()) {
                        // System.out.print(stkFood.nextToken()+" ");
                        arrFood.add(new Food(stkFood.nextToken(), stkFood.nextToken()));
                    }
                }
            }

            System.out.println(day + " -> " + arrFood);
        } catch (Exception e) {
            System.out.println("Something went wrong." + e);
        }
    }

    public void showMenu() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Menu -> ");
        System.out.println("--------------------------------------------------------------------------------");

        try {
            showBreakfast();
            showLunch();
            showSnacks();
            showDinner();
        } catch (Exception e) {
            System.out.println("Something wrong");
        }
    }

    public void insertBreakfastCSV(String path) throws Exception, SQLException {
        try {
            Connection conn = databaseutil.getConnection();
            PreparedStatement ps = conn.prepareStatement(QueryUtil.insertBreakfastCSV());

            BufferedReader bReader = new BufferedReader(new FileReader(path));
            bReader.readLine();

            String line = bReader.readLine();
            while (line != null) {
                // String[] data=line.split(",",2);
                int index = line.indexOf(',');
                ArrayList<String> data = new ArrayList<>();

                data.add(line.substring(0, index));
                data.add(line.substring(index + 2, line.length() - 1));

                // System.out.println(line+" "+data);

                ps.setString(1, data.get(0));
                ps.setString(2, data.get(1));

                ps.executeUpdate();

                line = bReader.readLine();
            }

            System.out.println("Records inserted Successfully...");
            showBreakfast();
        } catch (SQLIntegrityConstraintViolationException dupli) {
            System.out.println("No Duplicate entry allowed as Column Days set as Primary Key...");
            deleteBreakfast();
            insertBreakfastCSV(path);
        } catch (Exception e) {
            System.out.println("Something went wrong." + e);
        }
    }

    public void insertLunchCSV(String path) throws Exception, SQLException {
        try {
            Connection conn = databaseutil.getConnection();
            PreparedStatement ps = conn.prepareStatement(QueryUtil.insertLunchCSV());

            BufferedReader bReader = new BufferedReader(new FileReader(path));
            bReader.readLine();

            String line = bReader.readLine();
            while (line != null) {
                // String[] data=line.split(",",2);
                int index = line.indexOf(',');
                ArrayList<String> data = new ArrayList<>();

                data.add(line.substring(0, index));
                data.add(line.substring(index + 2, line.length() - 1));

                // System.out.println(line+" "+data);

                ps.setString(1, data.get(0));
                ps.setString(2, data.get(1));

                ps.executeUpdate();

                line = bReader.readLine();
            }

            System.out.println("Records inserted Successfully...");
            showLunch();
        } catch (SQLIntegrityConstraintViolationException dupli) {
            System.out.println("No Duplicate entry allowed as Column Days set as Primary Key...");
            deleteLunch();
            insertLunchCSV(path);
        } catch (Exception e) {
            System.out.println("Something went wrong." + e);
        }
    }

    public void insertSnacksCSV(String path) throws Exception, SQLException {
        try {
            Connection conn = databaseutil.getConnection();
            PreparedStatement ps = conn.prepareStatement(QueryUtil.insertSnacksCSV());

            BufferedReader bReader = new BufferedReader(new FileReader(path));
            bReader.readLine();

            String line = bReader.readLine();
            while (line != null) {
                // String[] data=line.split(",",2);
                int index = line.indexOf(',');
                ArrayList<String> data = new ArrayList<>();

                data.add(line.substring(0, index));
                data.add(line.substring(index + 2, line.length() - 1));

                // System.out.println(line+" "+data);

                ps.setString(1, data.get(0));
                ps.setString(2, data.get(1));

                ps.executeUpdate();

                line = bReader.readLine();
            }

            System.out.println("Records inserted Successfully...");
            showSnacks();
        } catch (SQLIntegrityConstraintViolationException dupli) {
            System.out.println("No Duplicate entry allowed as Column Days set as Primary Key...");
            deleteSnacks();
            insertSnacksCSV(path);
        } catch (Exception e) {
            System.out.println("Something went wrong." + e);
        }
    }

    public void insertDinnerCSV(String path) throws Exception, SQLException {
        try {
            Connection conn = databaseutil.getConnection();
            PreparedStatement ps = conn.prepareStatement(QueryUtil.insertDinnerCSV());

            BufferedReader bReader = new BufferedReader(new FileReader(path));
            bReader.readLine();

            String line = bReader.readLine();
            while (line != null) {
                // String[] data=line.split(",",2);
                int index = line.indexOf(',');
                ArrayList<String> data = new ArrayList<>();

                data.add(line.substring(0, index));
                data.add(line.substring(index + 2, line.length() - 1));

                // System.out.println(line+" "+data);

                ps.setString(1, data.get(0));
                ps.setString(2, data.get(1));

                ps.executeUpdate();

                line = bReader.readLine();
            }

            System.out.println("Records inserted Successfully...");
            showDinner();
        } catch (SQLIntegrityConstraintViolationException dupli) {
            System.out.println("No Duplicate entry allowed as Column Days set as Primary Key...");
            deleteDinner();
            insertDinnerCSV(path);
        } catch (Exception e) {
            System.out.println("Something went wrong." + e);
        }
    }

    void deleteBreakfast() {
        try {
            Connection conn = databaseutil.getConnection();
            Statement st = conn.createStatement();

            int rows = st.executeUpdate(QueryUtil.deleteBreakfast());

            if (rows > 0) {
                System.out.println("Records deleted Successfully...");
            } else {
                System.out.println("Could not be deleted...");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong." + e);
        }
    }

    void deleteLunch() {
        try {
            Connection conn = databaseutil.getConnection();
            Statement st = conn.createStatement();

            int rows = st.executeUpdate(QueryUtil.deleteLunch());

            if (rows > 0) {
                System.out.println("Records deleted Successfully...");
            } else {
                System.out.println("Could not be deleted...");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong." + e);
        }
    }

    void deleteSnacks() {
        try {
            Connection conn = databaseutil.getConnection();
            Statement st = conn.createStatement();

            int rows = st.executeUpdate(QueryUtil.deleteSnacks());

            if (rows > 0) {
                System.out.println("Records deleted Successfully...");
            } else {
                System.out.println("Could not be deleted...");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong." + e);
        }
    }

    void deleteDinner() {
        try {
            Connection conn = databaseutil.getConnection();
            Statement st = conn.createStatement();

            int rows = st.executeUpdate(QueryUtil.deleteDinner());

            if (rows > 0) {
                System.out.println("Records deleted Successfully...");
            } else {
                System.out.println("Could not be deleted...");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong." + e);
        }
    }
}
