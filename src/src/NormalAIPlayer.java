import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class NormalAIPlayer implements WheelOfFortunePlayer {
    private String playerId;
    private Random random;
    private Set<Character> guessedLetters;

    // Constructor: sets the player ID, initializes the random generator and guessed letters set
    public NormalAIPlayer(String playerId) {
        this.playerId = playerId;
        this.random = new Random();
        this.guessedLetters = new HashSet<>();
    }

    // Generates the next guess as a random letter that hasn't been guessed before
    @Override
    public char nextGuess() {
        char guess;
        do {
            guess = (char) ('a' + random.nextInt(26));
        } while (guessedLetters.contains(guess)); // Ensures the guess is unique
        guessedLetters.add(guess);
        return guess;
    }

    // Returns the player ID
    @Override
    public String playerId() {
        return playerId;
    }

    // Resets the guessed letters set for a new game
    @Override
    public void reset() {
        guessedLetters.clear();
    }
}