package me.alex;

public class Main {

    public static void main(String[] args) {
        ITelephone alexPhone;
        alexPhone = new DeskPhone(5666, true);
        alexPhone.powerOn();
        alexPhone.callPhone(3333);
        alexPhone.answer();

        alexPhone = new MobilePhone(333221);
        alexPhone.powerOn();
        alexPhone.callPhone(9393939);
        alexPhone.answer();

    }
}