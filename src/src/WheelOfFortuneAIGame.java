import java.util.List;
import java.util.Objects;

public class WheelOfFortuneAIGame extends WheelOfFortune {
    private List<WheelOfFortunePlayer> players;
    private int currentPlayerIndex;

    public WheelOfFortuneAIGame(List<WheelOfFortunePlayer> players) {
        super();
        this.players = players;
        this.currentPlayerIndex = 0;
    }

    @Override
    public GameRecord play() {
        boolean phraseFound = false;
        WheelOfFortunePlayer player = players.get(currentPlayerIndex);
        resetGame();
        player.reset();

        System.out.println("AI Player " + player.playerId() + " is guessing the phrase.");

        while (!phraseFound && lives > 0) {
            char guess = player.nextGuess();
            System.out.println(player.playerId() + " guesses: " + guess);

            processGuess(String.valueOf(guess));

            if (phrase.equalsIgnoreCase(hiddenPhrase.toString())) {
                phraseFound = true;
                processPhraseGuess();
                System.out.println(player.playerId() + " successfully guessed the phrase: " + phrase);
            } else if (lives == 0) {
                System.out.println(player.playerId() + " failed to guess the phrase. The correct phrase was: " + phrase);
            }
        }

        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        return new GameRecord(player.playerId(), score);
    }

    @Override
    public boolean playNext() {
        return currentPlayerIndex != 0 || lives > 0;
    }

    @Override
    protected String playerId() {
        return players.get(currentPlayerIndex).playerId();
    }


    // Main method to run the series of games
    public static void main(String[] args) {
        // Initialize the AI players
        List<WheelOfFortunePlayer> aiPlayers = List.of(
                new DumbAIPlayer("DumbAI"),
                new NormalAIPlayer("NormalAI"),
                new SmartAIPlayer("SmartAI")
        );

        // Initialize the game record keeper
        AllGamesRecord allGamesRecord = new AllGamesRecord();
        WheelOfFortuneAIGame aiGame = new WheelOfFortuneAIGame(aiPlayers);

        // Run 9 games (3 games for each player)
        for (int i = 0; i < 9; i++) {
            GameRecord record = aiGame.play();
            allGamesRecord.add(record);
        }

        // Display top 3 scores for each player
        for (WheelOfFortunePlayer player : aiPlayers) {
            System.out.println("\nTop 3 scores for " + player.playerId() + ":");
            allGamesRecord.highGameList(player.playerId(), 3).forEach(System.out::println);
        }

        // Calculate and display the average score across all 9 games
        double totalAverage = allGamesRecord.average();
        System.out.println("\nAverage score across all 9 games: " + totalAverage);

        // Calculate and display the average score for each player
        for (WheelOfFortunePlayer player : aiPlayers) {
            double playerAverage = allGamesRecord.average(player.playerId());
            System.out.println("Average score for " + player.playerId() + ": " + playerAverage);
        }
    }

    // equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WheelOfFortuneAIGame that = (WheelOfFortuneAIGame) o;
        return currentPlayerIndex == that.currentPlayerIndex && Objects.equals(players, that.players);
    }

    // toString() method
    @Override
    public String toString() {
        return "WheelOfFortuneAIGame{" +
                "players=" + players +
                ", currentPlayerIndex=" + currentPlayerIndex +
                '}';
    }

    // hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), players, currentPlayerIndex);
    }
}