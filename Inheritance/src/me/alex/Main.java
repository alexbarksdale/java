package me.alex;

public class Main {

    public static void main(String[] args) {
        Animal animal = new Animal("Greg", true, 50, 200);
        Dog dog = new Dog("Ryan", 5, 80, 4, 4, true);
        dog.eat();
    }
}
