import java.util.Objects;

abstract class Game {
    protected int lives;
    protected String phrase;
    protected StringBuilder hiddenPhrase;

    // Constructor: sets the initial lives to 3
    public Game() {
        this.lives = 3;
    }

    // Plays all games and returns a record of all games played
    public AllGamesRecord playAll() {
        AllGamesRecord record = new AllGamesRecord();
        while (playNext()) {
            GameRecord gameRecord = play();
            record.add(gameRecord);
        }
        return record;
    }

    // equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return lives == game.lives && Objects.equals(phrase, game.phrase) && Objects.equals(hiddenPhrase, game.hiddenPhrase);
    }

    // toString() method
    @Override
    public int hashCode() {
        return Objects.hash(lives, phrase, hiddenPhrase);
    }

    @Override
    public String toString() {
        return "Game{" +
                "lives=" + lives +
                ", phrase='" + phrase + '\'' +
                ", hiddenPhrase=" + hiddenPhrase +
                '}';
    }

    // Abstract method: defines how a single game is played
    public abstract GameRecord play();

    // Abstract method: determines if another game should be played
    public abstract boolean playNext();
}