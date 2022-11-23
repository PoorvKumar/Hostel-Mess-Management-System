package com.project.food;

public class Food implements Comparable<Food> {
    private String dishName;
    private String dishType;

    public Food(String dishName, String dishType) {
        this.dishName = dishName;
        this.dishType = dishType;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public String toString() {
        return getDishName() + "(" + getDishType() + ")";
    }

    @Override
    public int compareTo(Food fd) {
        return getDishName().compareTo(fd.getDishName());
    }
}
