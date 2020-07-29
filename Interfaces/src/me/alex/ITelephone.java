package me.alex;

// Nice to use if you want a class to have certain things in order to be valid.
public interface ITelephone {
    void powerOn();
    void dial(int phoneNumber);
    void answer();
    boolean callPhone(int phoneNumber);
    boolean isRinging();
}
