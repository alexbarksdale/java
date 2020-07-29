package me.alex;

public class Animal {
    private String name;
    private boolean body;
    private int size;
    private int weight;

    public Animal(String name, boolean body, int size, int weight) {
        this.name = name;
        this.body = body;
        this.size = size;
        this.weight = weight;
    }

    public void eat() {
        System.out.println(getName() + " ate!");
    }

    public String getName() {
        return name;
    }

    public boolean getBody() {
        return body;
    }

    public int getSize() {
        return size;
    }

    public int getWeight() {
        return weight;
    }
}
