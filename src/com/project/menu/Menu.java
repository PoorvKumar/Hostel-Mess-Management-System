package com.project.menu;

import java.util.*;
import java.sql.*;
import com.project.food.Food;
import com.project.util.*;

public class Menu
{
    DataBaseUtil databaseutil=new DataBaseUtil();

    public void showMealsofDay(String day)
    {
        try
        {
            Connection conn=databaseutil.getConnection();
            Statement st=conn.createStatement(ResultSet.CONCUR_READ_ONLY,ResultSet.TYPE_SCROLL_INSENSITIVE);

            ArrayList<String> meals=QueryUtil.showMealsofDay(day);
            ArrayList<Food> arrFood=new ArrayList<>(); 

            ArrayList<String> mealStr=new ArrayList<String>();
            mealStr.add("Breakfast");
            mealStr.add("Lunch");
            mealStr.add("Snacks");
            mealStr.add("Dinner");
            int k=0;

            System.out.println("Menu for "+day+" is -> ");

            for(String x:meals)
            {
                ResultSet rs=st.executeQuery(x);
                while(rs.next())
                {
                    StringTokenizer stk=new StringTokenizer(rs.getString(2),"|");

                    while(stk.hasMoreTokens())
                    {
                        StringTokenizer stkFood=new StringTokenizer(stk.nextToken(),",");
                        while(stkFood.hasMoreTokens())
                        {
                            // System.out.print(stkFood.nextToken()+" ");
                            arrFood.add(new Food(stkFood.nextToken(),stkFood.nextToken()));
                        }
                    }
                }
                System.out.print(mealStr.get(k)+" -> ");
                System.out.println(arrFood);
                k++;
                arrFood.clear();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void showBreakfast()
    {
        System.out.println("-------------------------------------");
        System.out.println("BreakFast Menu -> ");
        System.out.println("-------------------------------------");

        try
        {
            String[] days={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

            for(String str:days)
            {
                // System.out.println(str+" ->  "+breakfastMap.get(str));
                showBreakfastofDay(str);
            }
        }
        catch(Exception e)
        {
            System.out.println("Something wrong");
        }
    }

    public void showBreakfastofDay(String day)
    {
        try
        {
            Connection conn=databaseutil.getConnection();
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(QueryUtil.showBreakfastofDay(day));

            ArrayList<Food> arrFood=new ArrayList<Food>();

            while(rs.next())
            {
                StringTokenizer stk=new StringTokenizer(rs.getString(2),"|");

                while(stk.hasMoreTokens())
                {
                    StringTokenizer stkFood=new StringTokenizer(stk.nextToken(),",");
                    while(stkFood.hasMoreTokens())
                    {
                        arrFood.add(new Food(stkFood.nextToken(),stkFood.nextToken()));
                    }
                }
            }

            System.out.println(day+" -> "+arrFood);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void showLunch()
    {
        System.out.println("-------------------------------------");
        System.out.println("Lunch Menu -> ");
        System.out.println("-------------------------------------");

        try
        {
            String[] days={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

            for(String str:days)
            {
                // System.out.println(str+" ->  "+breakfastMap.get(str));
                showLunchofDay(str);
            }
        }
        catch(Exception e)
        {
            System.out.println("Something wrong");
        }
    }

    public void showLunchofDay(String day)
    {
        try
        {
            Connection conn=databaseutil.getConnection();
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(QueryUtil.showLunchofDay(day));
            ArrayList<Food> arrFood=new ArrayList<Food>();

            while(rs.next())
            {
                StringTokenizer stk=new StringTokenizer(rs.getString(2),"|");

                while(stk.hasMoreTokens())
                {
                    StringTokenizer stkFood=new StringTokenizer(stk.nextToken(),",");
                    while(stkFood.hasMoreTokens())
                    {
                        // System.out.print(stkFood.nextToken()+" ");
                        arrFood.add(new Food(stkFood.nextToken(),stkFood.nextToken()));
                    }
                }
            }

            System.out.println(day+" -> "+arrFood);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void showSnacks()
    {
        System.out.println("-------------------------------------");
        System.out.println("Snacks Menu -> ");
        System.out.println("-------------------------------------");

        try
        {
            String[] days={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

            for(String str:days)
            {
                // System.out.println(str+" ->  "+breakfastMap.get(str));
                showSnacksofDay(str);
            }
        }
        catch(Exception e)
        {
            System.out.println("Something wrong");
        }
    }

    public void showSnacksofDay(String day)
    {
        try
        {
            Connection conn=databaseutil.getConnection();
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(QueryUtil.showSnacksofDay(day));

            ArrayList<Food> arrFood=new ArrayList<Food>();

            while(rs.next())
            {
                StringTokenizer stk=new StringTokenizer(rs.getString(2),"|");

                while(stk.hasMoreTokens())
                {
                    StringTokenizer stkFood=new StringTokenizer(stk.nextToken(),",");
                    while(stkFood.hasMoreTokens())
                    {
                        // System.out.print(stkFood.nextToken()+" ");
                        arrFood.add(new Food(stkFood.nextToken(),stkFood.nextToken()));
                    }
                }
            }

            System.out.println(day+" -> "+arrFood);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void showDinner()
    {
        System.out.println("-------------------------------------");
        System.out.println("Dinner Menu -> ");
        System.out.println("-------------------------------------");

        try
        {
            String[] days={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

            for(String str:days)
            {
                // System.out.println(str+" ->  "+breakfastMap.get(str));
                showDinnerofDay(str);
            }
        }
        catch(Exception e)
        {
            System.out.println("Something wrong");
        }
    }
    
    public void showDinnerofDay(String day)
    {
        try
        {
            Connection conn=databaseutil.getConnection();
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(QueryUtil.showDinnerofDay(day));

            ArrayList<Food> arrFood=new ArrayList<Food>();

            while(rs.next())
            {
                StringTokenizer stk=new StringTokenizer(rs.getString(2),"|");

                while(stk.hasMoreTokens())
                {
                    StringTokenizer stkFood=new StringTokenizer(stk.nextToken(),",");
                    while(stkFood.hasMoreTokens())
                    {
                        // System.out.print(stkFood.nextToken()+" ");
                        arrFood.add(new Food(stkFood.nextToken(),stkFood.nextToken()));
                    }
                }
            }

            System.out.println(day+" -> "+arrFood);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void showMenu()
    {
        System.out.println("-------------------------------------");
        System.out.println("Menu -> ");
        System.out.println("-------------------------------------");

        try
        {
            showBreakfast();
            showLunch();
            showSnacks();
            showDinner();
        }
        catch(Exception e)
        {
            System.out.println("Something wrong");
        }
    }
}
