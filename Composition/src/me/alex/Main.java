package me.alex;

public class Main {

    public static void main(String[] args) {
        Dimensions dimensions = new Dimensions(20, 20, 5);
        Case pcCase = new Case("220b", "Dell", dimensions);

        Monitor monitor = new Monitor("27vq", "ASUS", 27, new Resolution(27, 27));

        Motherboard motherboard = new Motherboard("YT-2b", "ASUS", 4);
        PC pc = new PC(pcCase, monitor, motherboard);
        pc.getMonitor().drawPixelAt(1500, 1400, "BLUE");
        pc.getMotherboard().loadProgram("MacOS");
    }
}
