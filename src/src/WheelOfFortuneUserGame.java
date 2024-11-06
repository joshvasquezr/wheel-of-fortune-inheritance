import java.util.Scanner;

public class WheelOfFortuneUserGame extends WheelOfFortune {
    private Scanner scanner;

    // Constructor: initializes the scanner for user input
    public WheelOfFortuneUserGame() {
        super();
        this.scanner = new Scanner(System.in);
    }

    // Plays one round of the game, returns a GameRecord for the user
    @Override
    public GameRecord play() {
        boolean phraseFound = false;
        while (!phraseFound && lives > 0) {
            System.out.println("Hidden Phrase: " + hiddenPhrase);
            char guess = getGuess(); // Get the user's guess
            processGuess(guess); // Process the guessed character

            if (phrase.equalsIgnoreCase(hiddenPhrase.toString())) {
                phraseFound = true;
                processPhraseGuess(); // Award points for guessing the full phrase
                System.out.println("Congratulations! You found the phrase: " + phrase);
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

    // Prompts the user to enter a guess and returns the guessed character
    @Override
    public char getGuess() {
        System.out.println("Enter your guess: ");
        return scanner.nextLine().charAt(0);
    }

    // Returns the user ID as "User"
    private String playerId() {
        return "User";
    }

    // Main method to start the game and display the top game records
    public static void main(String[] args) {
        WheelOfFortuneUserGame userGame = new WheelOfFortuneUserGame();
        AllGamesRecord record = userGame.playAll();
        System.out.println("\nWheel of Fortune User Game Record:");
        record.highGameList(3).forEach(System.out::println);
        System.out.println("Average: " + record.average());
    }
}