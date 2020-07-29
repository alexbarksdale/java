package me.alex;

public class Gearbox {
    private boolean clutchIn;

    public void operateClutch(String inOrOut) {
        clutchIn = inOrOut.equalsIgnoreCase("in");
    }
}

