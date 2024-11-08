import java.util.Objects;
import java.util.Scanner;

public abstract class GuessingGame extends Game {
    protected int lives;
    protected int score;
    protected Scanner scanner;

    // toString() method
    @Override
    public String toString() {
        return "GuessingGame{" +
                "lives=" + lives +
                ", score=" + score +
                ", scanner=" + scanner +
                '}';
    }

    // equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuessingGame that = (GuessingGame) o;
        return lives == that.lives && score == that.score && Objects.equals(scanner, that.scanner);
    }

    // hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(lives, score, scanner);
    }

    public GuessingGame(int initialLives) {
        this.lives = initialLives;
        this.score = 0;
        this.scanner = new Scanner(System.in);
    }

    // Resets the game; subclasses can override if needed
    public void resetGame() {
        this.lives = 3;  // Default lives, subclass can specify if needed
        this.score = 0;
    }

    // Main game logic (to be implemented in subclasses)
    public abstract GameRecord play();

    // Default implementation of processGuess (can be overridden in subclasses)
    protected void processGuess(String guess) {
        // Default behavior: do nothing or log
    }

    // Standard play-next confirmation
    public boolean playNext() {
        System.out.println("Would you like to play again? (y/n)");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("y");
    }

    // Standard player ID for games (can be overridden)
    protected String playerId() {
        return "User";
    }
}