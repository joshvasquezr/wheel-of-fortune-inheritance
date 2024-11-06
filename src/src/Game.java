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

    // Abstract method: defines how a single game is played
    public abstract GameRecord play();

    // Abstract method: determines if another game should be played
    public abstract boolean playNext();
}