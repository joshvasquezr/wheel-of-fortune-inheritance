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

    // Main method to run the game with a list of AI players and print the results
    public static void main(String[] args) {
        List<WheelOfFortunePlayer> aiPlayers = List.of(
                new DumbAIPlayer("DumbAI"),
                new NormalAIPlayer("NormalAI"),
                new SmartAIPlayer("SmartAI")
        );

        AllGamesRecord allGamesRecord = new AllGamesRecord();
        WheelOfFortuneAIGame aiGame = new WheelOfFortuneAIGame(aiPlayers);

        // Run 3 games for each player (9 games in total)
        for (int i = 0; i < 9; i++) {
            GameRecord record = aiGame.play();
            allGamesRecord.add(record);
        }

        // Display the top 3 scores for each player
        for (WheelOfFortunePlayer player : aiPlayers) {
            System.out.println("\nTop 3 scores for " + player.playerId() + ":");
            allGamesRecord.highGameList(player.playerId(), 3).forEach(System.out::println);
        }

        // Calculate and display the average score across all games
        double totalAverage = allGamesRecord.average();
        System.out.println("\nAverage score across all 9 games: " + totalAverage);

        // Calculate and display the average score for each player
        for (WheelOfFortunePlayer player : aiPlayers) {
            double playerAverage = allGamesRecord.average(player.playerId());
            System.out.println("Average score for " + player.playerId() + ": " + playerAverage);
        }
    }
}