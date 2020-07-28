package me.alex;

public class Main {

    public static void main(String[] args) {
        Car honda = new Car(4);

        honda.setModel("Civic");
        System.out.println(honda.getModel());
        System.out.println(honda.getDoors());
    }
}
