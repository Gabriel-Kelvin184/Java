import java.util.Scanner;

public class GuessNumber {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number = (int) (Math.random() * 100 + 1);
        int attempts = 5;
        int guess;
        boolean win = false;

        System.out.println("I am thinking of a number between 1 and 100. You have " + attempts + " attempts to guess it.");

        for (int i = 1; i <= attempts; i++) {
            System.out.print("Guess #" + i + ": ");
            guess = input.nextInt();

            if (guess == number) {
                System.out.println("Congratulations! You guessed the number in " + i + " attempt(s).");
                win = true;
                break;
            } else if (guess < number) {
                System.out.println("Too low.");
            } else {
                System.out.println("Too high.");
            }
        }

        if (!win) {
            System.out.println("Sorry, you did not guess the number. The number was " + number + ".");
        }
    }
}
