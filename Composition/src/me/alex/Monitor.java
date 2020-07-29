package me.alex;

public class Monitor {
    private String model;
    private String manufacturer;
    private int size;
    private Resolution navtiveResolution;

    public Monitor(String model, String manufacturer, int size, Resolution navtiveResolution) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.size = size;
        this.navtiveResolution = navtiveResolution;
    }

    public void drawPixelAt(int x, int y, String color) {
        System.out.printf("Drawing pixel at %dx %dy in color %s\n", x, y, color);
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getSize() {
        return size;
    }

    public Resolution getNavtiveResolution() {
        return navtiveResolution;
    }
}
