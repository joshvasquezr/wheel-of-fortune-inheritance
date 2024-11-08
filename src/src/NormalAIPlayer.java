import java.util.HashSet;
import java.util.Objects;
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

    // equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalAIPlayer that = (NormalAIPlayer) o;
        return Objects.equals(playerId, that.playerId) && Objects.equals(random, that.random) && Objects.equals(guessedLetters, that.guessedLetters);
    }

    // hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(playerId, random, guessedLetters);
    }

    // toString() method
    @Override
    public String toString() {
        return "NormalAIPlayer{" +
                "playerId='" + playerId + '\'' +
                ", random=" + random +
                ", guessedLetters=" + guessedLetters +
                '}';
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