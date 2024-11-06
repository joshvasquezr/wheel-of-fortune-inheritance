import java.util.List;

public class WheelOfFortuneAIGame extends WheelOfFortune {
    private List<WheelOfFortunePlayer> players;
    private int currentPlayerIndex;

    // Constructor: sets up the list of AI players and initializes the current player index
    public WheelOfFortuneAIGame(List<WheelOfFortunePlayer> players) {
        super();
        this.players = players;
        this.currentPlayerIndex = 0;
    }

    // Gets the next guess from the current AI player
    @Override
    public char getGuess() {
        WheelOfFortunePlayer player = players.get(currentPlayerIndex);
        return player.nextGuess();
    }

    // Plays one round for the current player, returns the game record for that player
    @Override
    public GameRecord play() {
        boolean phraseFound = false;
        WheelOfFortunePlayer player = players.get(currentPlayerIndex);
        player.reset(); // Reset the player's state for a new turn
        resetGame(); // Reset game state for each player's turn

        while (!phraseFound && lives > 0) {
            char guess = getGuess(); // Get the next guess
            processGuess(guess); // Process the guessed character

            if (phrase.equalsIgnoreCase(hiddenPhrase.toString())) {
                phraseFound = true;
                processPhraseGuess(); // Award points for guessing the full phrase
                System.out.println(player.playerId() + " found the phrase: " + phrase);
            } else if (lives == 0) {
                System.out.println(player.playerId() + " failed. The correct phrase was: " + phrase);
            }
        }
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size(); // Move to the next player
        return new GameRecord(player.playerId(), score);
    }

    // Decides whether to continue playing based on the current player and remaining lives
    @Override
    public boolean playNext() {
        if (currentPlayerIndex == 0 && lives <= 0) {
            return false; // End the game if all players have taken their turn and lives are exhausted
        }
        return true; // Continue playing for the next player
    }

    // Main method to run the game with a list of AI players and print the top game records
    public static void main(String[] args) {
        List<WheelOfFortunePlayer> aiPlayers = List.of(
                new DumbAIPlayer("DumbAI"),
                new NormalAIPlayer("NormalAI"),
                new SmartAIPlayer("SmartAI")
        );
        WheelOfFortuneAIGame aiGame = new WheelOfFortuneAIGame(aiPlayers);
        AllGamesRecord aiRecord = aiGame.playAll();
        System.out.println("\nWheel of Fortune AI Game Record:");
        aiRecord.highGameList(3).forEach(System.out::println);
    }
}