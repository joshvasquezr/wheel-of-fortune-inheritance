import java.util.Objects;
import java.util.Random;

public class DumbAIPlayer implements WheelOfFortunePlayer {
    private String playerId;
    private Random random;

    // Constructor: sets the player ID and initializes the random generator
    public DumbAIPlayer(String playerId) {
        this.playerId = playerId;
        this.random = new Random();
    }

    // toString() method
    @Override
    public String toString() {
        return "DumbAIPlayer{" +
                "playerId='" + playerId + '\'' +
                ", random=" + random +
                '}';
    }

    // equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DumbAIPlayer that = (DumbAIPlayer) o;
        return Objects.equals(playerId, that.playerId) && Objects.equals(random, that.random);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, random);
    }

    // Generates the next guess as a random letter from 'a' to 'z'
    @Override
    public char nextGuess() {
        return (char) ('a' + random.nextInt(26));
    }

    // Returns the player ID
    @Override
    public String playerId() {
        return playerId;
    }

    // Resets the player (no action needed for DumbAIPlayer)
    @Override
    public void reset() {
        // Nothing to reset for DumbAIPlayer
    }
}