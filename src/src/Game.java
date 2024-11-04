abstract class Game {
    protected int lives;
    protected String phrase;
    protected StringBuilder hiddenPhrase;

    public Game() {
        this.lives = 3;
    }

    public AllGamesRecord playAll() {
        AllGamesRecord record = new AllGamesRecord();
        while (playNext()) {
            GameRecord gameRecord = play();
            record.add(gameRecord);
        }
        return record;
    }

    public abstract GameRecord play();
    public abstract boolean playNext();
}