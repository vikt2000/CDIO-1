import java.util.Random;
import java.util.Scanner;

public class DiceRoll {
    private int d1;
    private int d2;
    private int sum;
    private int p1Score;
    private int p2Score;
    private static boolean p1Turn;

    public DiceRoll() {
        sum = 0;
        p1Score = 0;
        p2Score = 0;
        p1Turn = true;
    }

    public void rollDice() {
        if (getp1Score() >= 40 || getp2Score() >= 40) {
            System.out.println("Player wins!");
            System.out.println("Press enter to exit game");
            System.exit(0);
        }

        Random ran = new Random();

        d1 = ran.nextInt(6) + 1;
        d2 = ran.nextInt(6) + 1;
        sum = d1 + d2;

        if (p1Turn) {
            p1Score += sum;
        } else {
            p2Score += sum;
        }

        if (winConditions(p1Score, p2Score, d1, d2)) {
            System.out.println("Player wins!");
        }
    }

    public static boolean winConditions(int p1Score, int p2Score, int d1, int d2) {
        if (p1Score >= 40 && d1 == d2) {
            return true;
        } else if (p2Score >= 40 && d1 == d2) {
            return true;
        } else {
            return false;
        }
    }

    public int getd1() {
        return d1;
    }

    public int getd2() {
        return d2;
    }

    public int getSum() {
        return sum;
    }

    public int getp1Score() {
        return p1Score;
    }

    public int getp2Score() {
        return p2Score;
    }

    public static void main(String[] args) {
        DiceRoll diceRoll = new DiceRoll();
        var scan = new Scanner(System.in);

        System.out.println("DiceGame" + "\n" + "Press enter to play");

        while (true) {
            scan.nextLine();

            diceRoll.rollDice();

            System.out.println("First dice: " + diceRoll.getd1());
            System.out.println("Second dice: " + diceRoll.getd2());
            System.out.println("Sum of the 2 die: " + diceRoll.getSum());

            if (DiceRoll.p1Turn) {
                System.out.println("Total score - Player 1: " + diceRoll.getp1Score());
                p1Turn = false;
            } else {
                System.out.println("Total score - Player 2: " + diceRoll.getp2Score());
                p1Turn = true;
            }
        }

        scan.close();
    }
}
