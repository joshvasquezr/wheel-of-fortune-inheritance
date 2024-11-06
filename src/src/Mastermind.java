import java.util.Random;
import java.util.Scanner;

// Mastermind Game Implementation
public class Mastermind extends Game {
    private String secretCode;
    private Scanner scanner;
    private final String[] COLORS = {"R", "G", "B", "Y", "O", "P"}; // Red, Green, Blue, Yellow, Orange, Purple

    // Constructor: sets lives to 10 and generates a new secret code
    public Mastermind() {
        super();
        this.lives = 10;
        this.secretCode = generateSecretCode();
        this.scanner = new Scanner(System.in);
    }

    // Generates a random 4-color secret code
    private String generateSecretCode() {
        Random rand = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            code.append(COLORS[rand.nextInt(COLORS.length)]);
        }
        return code.toString();
    }

    // Plays one round of Mastermind, returns a GameRecord of the game
    @Override
    public GameRecord play() {
        boolean codeFound = false;
        System.out.println("Welcome to Mastermind! Try to guess the 4-color secret code.");
        while (!codeFound && lives > 0) {
            System.out.println("Enter your guess (4 colors, use R, G, B, Y, O, P): ");
            String guess = scanner.nextLine().toUpperCase();
            if (guess.equals(secretCode)) {
                codeFound = true;
                System.out.println("Congratulations! You've cracked the code: " + secretCode);
            } else {
                int exactMatches = calculateExactMatches(guess);
                int partialMatches = calculatePartialMatches(guess);
                System.out.println("Feedback: " + exactMatches + " exact, " + partialMatches + " partial.");
                lives--;
                System.out.println("Incorrect guess! Lives remaining: " + lives);
            }
        }
        if (!codeFound) {
            System.out.println("Game Over! The correct code was: " + secretCode);
        }
        return new GameRecord(playerId(), calculateScore());
    }

    // Calculates the number of exact matches between guess and secret code
    private int calculateExactMatches(String guess) {
        int exactMatches = 0;
        for (int i = 0; i < 4; i++) {
            if (guess.charAt(i) == secretCode.charAt(i)) {
                exactMatches++;
            }
        }
        return Math.max(0, Math.min(exactMatches, 4));
    }

    // Calculates the number of partial matches (correct color, wrong position)
    private int calculatePartialMatches(String guess) {
        int partialMatches = 0;
        boolean[] secretUsed = new boolean[4];
        boolean[] guessUsed = new boolean[4];

        // First pass: find exact matches
        for (int i = 0; i < 4; i++) {
            if (guess.charAt(i) == secretCode.charAt(i)) {
                secretUsed[i] = true;
                guessUsed[i] = true;
            }
        }

        // Second pass: find partial matches
        for (int i = 0; i < 4; i++) {
            if (!guessUsed[i]) {
                for (int j = 0; j < 4; j++) {
                    if (!secretUsed[j] && guess.charAt(i) == secretCode.charAt(j)) {
                        partialMatches++;
                        secretUsed[j] = true;
                        break;
                    }
                }
            }
        }
        return Math.max(0, Math.min(partialMatches, 4));
    }

    // Asks the player if they want to play again, returns true if yes
    @Override
    public boolean playNext() {
        System.out.println("Would you like to play Mastermind again? (y/n)");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("y")) {
            // Reset the game state for a new game
            this.lives = 10;
            this.secretCode = generateSecretCode();
            return true;
        }
        return false;
    }

    // Returns the player ID (in this case, "User")
    private String playerId() {
        return "User";
    }

    // Calculates the score based on remaining lives
    private int calculateScore() {
        return lives * 10;
    }

    // Main method to start the game and display the top 3 game records
    public static void main(String[] args) {
        Mastermind mastermindGame = new Mastermind();
        AllGamesRecord mastermindRecord = mastermindGame.playAll();
        System.out.println("\nMastermind Game Record:");
        mastermindRecord.highGameList(3).forEach(System.out::println);
        System.out.println("Average: " + mastermindRecord.average());
    }
}