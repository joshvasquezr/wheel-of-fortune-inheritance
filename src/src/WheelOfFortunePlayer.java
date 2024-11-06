public interface WheelOfFortunePlayer {

    // Returns the next guessed character by the player
    char nextGuess();

    // Returns the player's unique ID
    String playerId();

    // Resets the player's state for a new game or turn
    void reset();
}