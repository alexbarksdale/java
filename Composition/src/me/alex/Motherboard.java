package me.alex;

public class Motherboard {
    private String model;
    private String manufacturer;
    private int ramSlots;

    public Motherboard(String model, String manufacturer, int ramSlots) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.ramSlots = ramSlots;
    }

    public void loadProgram(String name) {
        System.out.printf("Program name %s is now loading...", name);
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getRamSlots() {
        return ramSlots;
    }
}
