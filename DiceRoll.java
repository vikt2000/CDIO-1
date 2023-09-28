import java.util.Random;
import java.util.Scanner;

public class DiceRoll {
    private int d1;
    private int d2;
    private int sum;
    private int p1Score;
    private int p2Score;
    private static boolean p1Turn;
    // ændring
    private boolean priviousRollTwoSixes;
    private boolean extraTurn;

    public DiceRoll() {
        sum = 0;
        p1Score = 0;
        p2Score = 0;
        p1Turn = true;
        priviousRollTwoSixes = false;

    }

    public void rollDice() {

        Random ran = new Random();
        // ændring

        d1 = ran.nextInt(6) + 1;
        d2 = ran.nextInt(6) + 1;
        calculate(d1, d2);
        if (extraTurn) {
            rollDice();
        }
    }

    public void calculate(int d1, int d2) {
        sum = d1 + d2;
        extraTurn = false;
        if (p1Turn) {
            p1Score += sum;
        } else {
            p2Score += sum;
        }
        if (d1 == 1 && d2 == 1) {
            if (p1Turn) {
                System.out.println("Oh no, player 1 rolled two 1's and lost all points.");
                p1Score = 0;
            } else {
                System.out.println("Oh no, player 2 rolled two 1's and lost all points.");
                p2Score = 0;
            }
            extraTurn = true;
            priviousRollTwoSixes = false;
            // ændring
        }
        if (d1 == 6 && d2 == 6) {
            if (!priviousRollTwoSixes) {
                System.out.println("The dice rolled the same number " + d1 + ", roll again.");
                if (p1Turn)
                    System.out.println("Total score - Player 1: " + p1Score);
                else
                    System.out.println("Total score - Player 2: " + p2Score);
                // rollDice();
                extraTurn = true;
            } else {
                if (p1Turn) {
                    System.out.println("congratulation player 1, you won the game by drawing to sixs in a row");
                    System.out.println("Press enter to exit game");
                    System.exit(0);
                } else
                    System.out.println("congratulation player 2, you won the game by drawing to sixs in a row");
                System.out.println("Press enter to exit game");
                System.exit(0);
            }
            priviousRollTwoSixes = true;
        } else if (d1 == d2) {
            System.out.println("The dice rolled the same number " + d1 + ", roll again.");
            if (p1Turn)
                System.out.println("Total score - Player 1: " + p1Score);
            else
                System.out.println("Total score - Player 2: " + p2Score);
            // rollDice();
            extraTurn = true;
            priviousRollTwoSixes = false;
        }
        if (getp1Score() >= 40) {
            System.out.println("Player 1 wins the game!");
            System.out.println("Press enter to exit game");
            System.exit(0);
        }

        if (getp2Score() >= 40) {
            System.out.println("Player 2 wins the game!");
            System.out.println("Press enter to exit game");
            System.exit(0);
        }
    }

    public boolean getExtraTurn() {
        return extraTurn;
    }

    public int getD1() {
        return d1;
    }

    public int getD2() {
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

            if (diceRoll.getp1Score() >= 40 || diceRoll.getp2Score() >= 40) {
                break;
            }

            diceRoll.rollDice();

            System.out.println("First dice: " + diceRoll.getD1());
            System.out.println("Second dice: " + diceRoll.getD2());
            System.out.println("Sum of the 2 die: " + diceRoll.getSum());

            if (DiceRoll.p1Turn) {
                System.out.println("Total score - Player 1: " + diceRoll.getp1Score());
                p1Turn = false;
            } else {
                System.out.println("Total score - Player 2: " + diceRoll.getp2Score());
                p1Turn = true;
            }

            if (diceRoll.getp1Score() >= 40) {
                System.out.println("Player 1 wins the game!");
                System.out.println("Press enter to exit game");
                System.exit(0);
            }

            if (diceRoll.getp2Score() >= 40) {
                System.out.println("Player 2 wins the game!");
                System.out.println("Press enter to exit game");
                System.exit(0);
            }

        }

        scan.close();
    }
}