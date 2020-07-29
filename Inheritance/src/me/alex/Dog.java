package me.alex;

public class Dog extends Animal {
    private int eyes;
    private int legs;
    private boolean tail;

    public Dog(String name, int size, int weight, int eyes, int legs, boolean tail) {
        // Calls the constructor of Animal
        super(name, true, size, weight);
        this.eyes = eyes;
        this.legs = legs;
        this.tail = tail;
    }

    @Override
    public void eat() {
        System.out.println("Eat method from Dog!");
        super.eat(); // Run the super class' method
    }
}
