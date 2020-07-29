package me.alex;

public class Car {
    private final int doors;
    private int wheels;
    private String model;
    private String engine;
    private String color;

    // If a constructor is called with no params, this will run.
    // Good when you want to set default values.
    public Car() {
        this(4);
        System.out.println("Empty constructor called, setting default values.");
    }

    public Car(int doors) {
        System.out.println("This is the constructor.");
        this.doors = doors;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public int getDoors() {
        return doors;
    }
}
