public class GameRecord implements Comparable<GameRecord> {

    private String playerId;
    private int score;

    // Constructor: sets the player ID and score
    public GameRecord(String playerId, int score) {
        this.playerId = playerId;
        this.score = score;
    }

    // Compares this GameRecord with another to sort by score in descending order
    public int compareTo(GameRecord o) {
        return Integer.compare(o.score, this.score);
    }

    // Returns the score of this game record
    public int getScore() {
        return this.score;
    }

    // Returns the player ID of this game record
    public String getPlayerId() {
        return this.playerId;
    }

    // Returns a string representation of this game record
    public String toString() {
        return "Player ID: " + this.playerId + ", Score: " + this.score;
    }
}