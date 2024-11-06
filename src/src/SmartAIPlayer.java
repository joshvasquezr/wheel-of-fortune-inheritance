public class SmartAIPlayer implements WheelOfFortunePlayer {
    private String playerId;
    private int guessIndex;
    private final char[] commonLetters = {
            'e', 't', 'a', 'o', 'i', 'n', 's', 'h', 'r', 'd', 'l', 'u', 'c', 'm', 'w', 'f', 'g', 'y', 'p', 'b', 'v', 'k', 'j', 'x', 'q', 'z'
    };

    // Constructor: sets the player ID and initializes the guess index
    public SmartAIPlayer(String playerId) {
        this.playerId = playerId;
        this.guessIndex = 0;
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