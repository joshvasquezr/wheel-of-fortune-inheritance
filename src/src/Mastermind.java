import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

// Mastermind Game Implementation
public class Mastermind extends GuessingGame {
    private String secretCode;
    private final String[] COLORS = {"R", "G", "B", "Y", "O", "P"}; // Red, Green, Blue, Yellow, Orange, Purple

    // equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Mastermind that = (Mastermind) o;
        return Objects.equals(secretCode, that.secretCode) && Objects.deepEquals(COLORS, that.COLORS);
    }

    // toString() method
    @Override
    public String toString() {
        return "Mastermind{" +
                "secretCode='" + secretCode + '\'' +
                ", COLORS=" + Arrays.toString(COLORS) +
                '}';
    }

    // hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), secretCode, Arrays.hashCode(COLORS));
    }

    // Constructor: sets lives to 10 and generates a new secret code
    public Mastermind() {
        super(10);  // Initialize with 10 lives as per Mastermind rules
        resetGame(); // Initialize the game state
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

                // Update score based on exact and partial matches
                score += (exactMatches * 10) + (partialMatches * 5); // 10 points for exact, 5 for partial

                // Deduct a life for each incorrect guess
                lives--;
                System.out.println("Incorrect guess! Lives remaining: " + lives);
            }
        }

        if (!codeFound) {
            System.out.println("Game Over! The correct code was: " + secretCode);
        }

        return new GameRecord(playerId(), score);
    }

    // Resets the game state for a new game
    @Override
    public void resetGame() {
        this.lives = 10;                 // Set lives back to 10 for a new game
        this.score = 0;                  // Reset score to 0
        this.secretCode = generateSecretCode(); // Generate a new secret code
    }

    // Calculates the number of exact matches between guess and secret code
    private int calculateExactMatches(String guess) {
        int exactMatches = 0;
        for (int i = 0; i < 4; i++) {
            if (guess.charAt(i) == secretCode.charAt(i)) {
                exactMatches++;
            }
        }
        return exactMatches;
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
        return partialMatches;
    }

    public static void main(String[] args) {
        AllGamesRecord allGamesRecord = new AllGamesRecord(); // Track all game records
        Mastermind mastermindGame = new Mastermind(); // Initialize the Mastermind game

        // Loop to allow the user to play multiple games
        while (true) {
            // Play a game and add its record to the game records
            GameRecord record = mastermindGame.play();
            allGamesRecord.add(record);

            // Ask if the user wants to play another game
            if (!mastermindGame.playNext()) {
                break; // Exit if the user chooses not to continue
            }

            mastermindGame.resetGame(); // Reset for a new game if continuing
        }

        // Display all scores and the average score once the player is done
        System.out.println("\nAll scores for the games played:");
        allGamesRecord.highGameList(2).forEach(System.out::println);

        double averageScore = allGamesRecord.average();
        System.out.println("\nAverage score across all games: " + averageScore);
    }
}