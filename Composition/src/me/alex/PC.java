package me.alex;

public class PC {
    private Case pcCase;
    private Monitor monitor;
    private Motherboard motherboard;

    public PC(Case pcCase, Monitor monitor, Motherboard motherboard) {
        this.pcCase = pcCase;
        this.monitor = monitor;
        this.motherboard = motherboard;
    }

    public Case getPcCase() {
        return pcCase;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }
}
