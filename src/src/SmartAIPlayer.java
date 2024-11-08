import java.util.Arrays;
import java.util.Objects;

public class SmartAIPlayer implements WheelOfFortunePlayer {
    private String playerId;
    private int guessIndex;
    private final char[] commonLetters = {
            'e', 't', 'a', 'o', 'i', 'n', 's', 'h', 'r', 'd', 'l', 'u', 'c', 'm', 'w', 'f', 'g', 'y', 'p', 'b', 'v', 'k', 'j', 'x', 'q', 'z'
    };

    // equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartAIPlayer that = (SmartAIPlayer) o;
        return guessIndex == that.guessIndex && Objects.equals(playerId, that.playerId) && Objects.deepEquals(commonLetters, that.commonLetters);
    }

    // hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(playerId, guessIndex, Arrays.hashCode(commonLetters));
    }

    // Constructor: sets the player ID and initializes the guess index
    public SmartAIPlayer(String playerId) {
        this.playerId = playerId;
        this.guessIndex = 0;
    }

    // toString() method
    @Override
    public String toString() {
        return "SmartAIPlayer{" +
                "playerId='" + playerId + '\'' +
                ", guessIndex=" + guessIndex +
                ", commonLetters=" + Arrays.toString(commonLetters) +
                '}';
    }

    // Returns the next guess based on letter frequency
    @Override
    public char nextGuess() {
        if (guessIndex < commonLetters.length) {
            return commonLetters[guessIndex++]; // Return the next most common letter and increment index
        }
        return 'a'; // Fallback if all letters are guessed
    }

    // Returns the player ID
    @Override
    public String playerId() {
        return playerId;
    }

    // Resets the guess index for a new game
    @Override
    public void reset() {
        guessIndex = 0; // Reset index to start guessing from the most common letter again
    }
}