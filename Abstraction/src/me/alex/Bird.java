package me.alex;

public abstract class Bird extends Animal implements CanFly {
    public Bird(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println("Bird eat");
    }

    @Override
    public void breathe() {
        System.out.println("Bird breathe");
    }

    @Override
    public void fly() {
        System.out.println(getName() + " is flying.");
    }
}
