package me.alex;

public class Main {

    public static void main(String[] args) {
        Dog dog = new Dog("Yorkie");
        dog.eat();
        dog.breathe();

        Parrot parrot = new Parrot("Ralph");
        parrot.eat();
        parrot.breathe();
        parrot.fly();
    }


}
