import java.util.Random;

public class DumbAIPlayer implements WheelOfFortunePlayer {
    private String playerId;
    private Random random;

    // Constructor: sets the player ID and initializes the random generator
    public DumbAIPlayer(String playerId) {
        this.playerId = playerId;
        this.random = new Random();
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