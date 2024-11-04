public class GameRecord implements Comparable<GameRecord> {

    private String playerId;
    private int score;
;
    public GameRecord(String playerId, int score) {
        this.playerId = playerId;
        this.score = score;
    }

    public int compareTo(GameRecord o) {
        return Integer.compare(o.score, this.score);
    }

    public int getScore() {
        return this.score;
    }

    public String getPlayerId() {
        return this.playerId;
    }

    public String toString() {
        return "Player ID: " + this.playerId + ", Score: " + this.score;
    }
}
