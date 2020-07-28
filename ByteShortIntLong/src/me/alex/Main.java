package me.alex;

public class Main {

    public static void main(String[] args) {
        int minVal = Integer.MIN_VALUE;
        int maxVal = Integer.MAX_VALUE;

        System.out.println("Min Val:" +  minVal);
        System.out.println("Max Val:" +  maxVal);

        byte minByteVal = Byte.MIN_VALUE;
        byte maxByteVal = Byte.MAX_VALUE;

        System.out.println("Min Val:" +  minByteVal);
        System.out.println("Max Val:" +  maxByteVal);

        short minShortVal = Short.MIN_VALUE;
        short maxShortVal = Short.MAX_VALUE;

        System.out.println("Min Val:" +  minShortVal);
        System.out.println("Max Val:" +  maxShortVal);

        long minLongVal = Long.MIN_VALUE;
        long maxLongVal = Long.MAX_VALUE;

        System.out.println("Min Val:" +  minLongVal);
        System.out.println("Max Val:" +  maxLongVal);

        // CASTING
        int price = 5;
        int total = (price / 2);

        byte byteTotal = (byte) (total / 2);

        short shortTotal = (short) (total / 2);
    }
}
