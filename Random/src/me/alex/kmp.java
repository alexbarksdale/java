package me.alex;

public class kmp {
    private String pattern;
    private String text;

    public kmp(String text, String pattern) {
        this.text = text;
        this.pattern = pattern;
    }

    private void createLPS(int[] LPS, int patLen) {
        int len=0;
        int i=1;
        LPS[0] = 0;
        while (i < patLen) {
            if (pattern.charAt(i) == pattern.charAt(len)){
                LPS[i] = len+1;
                i++; len++;
            } else {
                if (len != 0) {
                    len = LPS[len-1];
                } else {
                    LPS[i] = 0;
                    i++;
                }
            }
        }
    }

    public boolean search() {
        int textLen = text.length();
        int patLen = pattern.length();

        int[] LPS = new int[patLen];
        createLPS(LPS, patLen);

        int i=0, j=0;
        while (i<textLen) {
            if (text.charAt(i) == text.charAt(j)) {
                i++; j++;
            } else {
                if (j != 0) {
                    j = LPS[j-1];
                } else {
                    i++;
                }
            }
            if (j==patLen) {
                return true;
                // j = lps[j-1]; // Find all occurances
            }
        }
    }
}
