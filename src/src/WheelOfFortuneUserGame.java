import java.util.Objects;
import java.util.Scanner;

public class WheelOfFortuneUserGame extends WheelOfFortune {
    private Scanner scanner;

    // Constructor: initializes the scanner for user input and sets up the game
    public WheelOfFortuneUserGame() {
        super();
        this.scanner = new Scanner(System.in);
    }

    // Main gameplay logic for the user
    @Override
    public GameRecord play() {
        boolean phraseFound = false;
        resetGame(); // Reset the game state for each new game
        System.out.println("Welcome to Wheel of Fortune! Try to guess the hidden phrase.");

        // Main game loop
        while (!phraseFound && lives > 0) {
            System.out.println("Hidden Phrase: " + hiddenPhrase);
            System.out.println("Guessed Letters: " + guessedLetters);
            System.out.println("Enter your guess (a single letter or the full phrase): ");

            String guess = scanner.nextLine().toLowerCase();
            if (guess.length() == 1) {
                // Single character guess
                processGuess(guess);
            } else {
                // Full phrase guess
                if (guess.equalsIgnoreCase(phrase)) {
                    phraseFound = true;
                    processPhraseGuess(); // Award points for guessing the full phrase
                    System.out.println("Congratulations! You've guessed the phrase: " + phrase);
                } else {
                    System.out.println("Incorrect phrase! Lives remaining: " + --lives);
                    score -= 10; // Penalty for incorrect phrase guess
                }
            }

            if (phrase.equalsIgnoreCase(hiddenPhrase.toString())) {
                phraseFound = true;
                System.out.println("Congratulations! You've guessed the phrase: " + phrase);
            } else if (lives == 0) {
                System.out.println("Game Over! The correct phrase was: " + phrase);
            }
        }
        return new GameRecord(playerId(), score);
    }

    // Asks the user if they want to play again, returns true if yes
    @Override
    public boolean playNext() {
        System.out.println("Would you like to play again? (y/n)");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("y")) {
            resetGame();  // Reset the game state if the player wants to play again
            return true;
        } else if (response.equalsIgnoreCase("n")) {
            return false;
        } else {
            System.out.println("Invalid input. Please enter 'y' or 'n'.");
            return playNext();  // Recursively ask until a valid input is given
        }
    }

    // Returns the user ID as "User"
    @Override
    protected String playerId() {
        return "User";
    }
    public static void main(String[] args) {
        // Initialize the game record keeper
        AllGamesRecord allGamesRecord = new AllGamesRecord();
        WheelOfFortuneUserGame userGame = new WheelOfFortuneUserGame();

        // Loop to allow the user to play multiple games
        while (true) {
            // Play a game and add its record to the game records
            GameRecord record = userGame.play();
            allGamesRecord.add(record);

            // Ask if the user wants to play again
            if (!userGame.playNext()) {
                break; // Exit the loop if the user doesn't want to continue
            }

            userGame.resetGame(); // Reset for a new game if continuing
        }

        // Display leaderboard and statistics only at the end
        System.out.println("\nTop scores for the games played:");
        allGamesRecord.highGameList(2).forEach(System.out::println);

        double averageScore = allGamesRecord.average();
        System.out.println("\nAverage score across the games: " + averageScore);
    }

    // equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WheelOfFortuneUserGame that = (WheelOfFortuneUserGame) o;
        return Objects.equals(scanner, that.scanner);
    }

    // toString() method
    @Override
    public String toString() {
        return "WheelOfFortuneUserGame{" +
                "scanner=" + scanner +
                '}';
    }

    // hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), scanner);
    }
}