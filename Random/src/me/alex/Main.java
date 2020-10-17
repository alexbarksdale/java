package me.alex;

import java.sql.SQLOutput;
import java.util.*;

public class Main {


    public static void main(String[] args) {
//        int[] a = {10, 2, 2};
//        int[][] vals = {
//                {3,3,4,2},
//                {4,4},
//                {4,0,3,3},
//                {2,3},
//                {3,3,3}
//        };

//        char[][] box = {
//                {'#', '#', '.', '.', '.', '.', '.'},
//                {'#', '#', '#', '.', '.', '.', '.'},
//                {'#', '#', '#', '.', '.', '#', '.'}
//        };

//        String test = "1111122222";
        //System.out.println(concatSum(a));

        //System.out.println(reduceTheNumber(test, 3));

//        rotate(box);

        //System.out.println(Arrays.deepToString(meanGroups(vals)));
        // int[] chars = new int[26];

        // System.out.println(shiftChar('z', 2));



    }
    public static char shiftChar(char c, int key) {
        // Number Letter Character
        int nLC = c + key;
        if (nLC <= 122) {
            return (char) nLC;
        } else {
            return (char) (96 + nLC % 122);
        }
    }

    public static long concatSum(int[] a) {
        long total=0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i=0; i<a.length; i++) {
            String s = Integer.toString(a[i]);
            System.out.println("What is this:" + s.length());
            int len = s.length();
            map.put(len, map.getOrDefault(len, 0)+1);
        }

        System.out.println(map.toString());

        for (int i=0; i<a.length; i++) {
            int num = a[i];
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                total += num * (entry.getValue()) * Math.pow(10, entry.getKey());
            }
            System.out.println("Total 1:" + total);
            total += num * a.length;
            System.out.println("Total 2:" + total + "\n");
        }
        return total;
    }

    public static int[][] meanGroups(int[][] a) {
        Map<Double, List<Integer>> map = new HashMap<>();
        double[] means = new double[a.length];

        for(int row=0; row<a.length; row++) {

            int sum=0, colLen=a[row].length;

            for (int col=0; col<colLen; col++) {
                sum += a[row][col];
            }

            double mean = (double)sum/colLen;

            List<Integer> temp = map.get(mean);

            if (temp == null) {
                temp = new LinkedList<>();
                map.put(mean, temp);
            }

            temp.add(row);
            means[row] = mean;
        }

        System.out.println("Map:" + map.toString());
        System.out.println("Means:" + Arrays.toString(means));


        int[][] output = new int[map.keySet().size()][];

        int idx=0;
        for (double m : means) {
            List<Integer> indices = map.get(m);

            if (indices == null) continue; // Was duplicate
            map.remove(m); // Remove duplicate

            int[] temp = new int[indices.size()];
            for (int i=0; i<indices.size(); i++) {
                temp[i] = indices.get(i);
            }
            output[idx++] = temp;
        }

        return output;
    }

    public static String reduceTheNumber(String number, int k) {
        if (number.length() < k) return number;

        while (number.length() > k) {
            List<String> groups = new ArrayList<>();


            for (int i=0; i<number.length(); i+=k) {
                groups.add(number.substring(i, Math.min(number.length(), i+k)));
            }

            int[] summedDigits = new int[groups.size()];

            for (int i=0; i<groups.size(); i++) {
                String str = groups.get(i);

                int sum = 0;
                for (int j=0; j<str.length(); j++) {
                    sum += Integer.parseInt(String.valueOf(str.charAt(j)));
                }
                summedDigits[i] = sum;
            }

            StringBuilder sb = new StringBuilder();

            for (int num : summedDigits) {
                sb.append(num);
            }

            // System.out.println(sb.toString());
            number = sb.toString();
        }

        return number;
    }

    public static char[][] rotate(char[][] box) {
        int n = box.length;
        int m = box[0].length;
        char [][] output = new char[m][n];

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                output[j][n-1-i] = box[i][j];
            }
        }

        for (int i=0; i<output[0].length; i++) {
            for (int j=output.length-1; j>=0; j--) {
                for (int k=j; k < output.length-1; k++) {
                    if (output[k][i] == '#' && output[k+1][i] == '.') {
                        char temp = output[k+1][i];
                        output[k+1][i] =  output[k][i];
                        output[k][i] = temp;
                    } else {
                        break;
                    }

                }
            }
        }

        return output;
    }
}
