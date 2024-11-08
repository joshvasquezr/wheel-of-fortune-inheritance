import java.util.Objects;

public class GameRecord implements Comparable<GameRecord> {

    private String playerId;
    private int score;

    // Constructor: sets the player ID and score
    public GameRecord(String playerId, int score) {
        this.playerId = playerId;
        this.score = score;
    }

    // toString() method
    @Override
    public String toString() {
        return "GameRecord{" +
                "playerId='" + playerId + '\'' +
                ", score=" + score +
                '}';
    }

    // equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameRecord that = (GameRecord) o;
        return score == that.score && Objects.equals(playerId, that.playerId);
    }

    // hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(playerId, score);
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

}