package me.alex;

public class MobilePhone implements ITelephone{
        private final int myNumber;
        private boolean isRinging;
        private boolean isOn = false;

        public MobilePhone(int myNumber) {
            this.myNumber = myNumber;
        }

        @Override
        public void powerOn() {
            isOn = true;
            System.out.println("Mobile phone on");
        }

        @Override
        public void dial(int phoneNumber) {
            if (isOn) {
                System.out.println("Now dialing" + phoneNumber);
            } else {
                System.out.println("Mobile phone is off");
            }
        }

        @Override
        public void answer() {
            if (isRinging) {
                System.out.println("Answering");
                isRinging = false;
            }
        }

        @Override
        public boolean callPhone(int phoneNumber) {
            if (phoneNumber == myNumber && isOn) {
                isRinging = true;
                System.out.println("Ringing");
            } else {
                isRinging = false;
                System.out.println("Phone is off");
            }
            return isRinging;
        }

        @Override
        public boolean isRinging() {
            return isRinging;
        }
}
