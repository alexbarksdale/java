package me.alex;

public class Main {

    public static void main(String[] args) {
        int score = 100;

        score = calcScore("Alex", 500);
        calcScore(55);

        boolean gameOver = checkGameStatus(true, score);

        if (!gameOver) {
            System.out.println("Game isn't over");
        }
    }

    public static int calcScore(String playerName, int score) {
        System.out.println("Player " + playerName + " scored " + score + " points");
        return score * 1000;
    }

    public static int calcScore(int score) {
        System.out.println("Unknown player" + " scored " + score + " points");
        return score * 1000;
    }

    public static boolean checkGameStatus(boolean gameOver, int score) {
        if (gameOver) {
            System.out.println("Final score " + score);
            return true;
        }
        return false;
    }
}
