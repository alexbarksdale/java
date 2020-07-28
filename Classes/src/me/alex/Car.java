package me.alex;

public class Car {
    private int doors;
    private int wheels;
    private String model;
    private String engine;
    private String color;

    public Car(int doors) {
        System.out.println("This is the constructor.");
        this.doors = doors;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return this.model;
    }

    public int getDoors() {
        return this.doors;
    }
}
