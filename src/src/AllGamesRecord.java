import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class AllGamesRecord {

    private List<GameRecord> gameRecordList;

    // Constructor: initializes the gameRecordList as an empty ArrayList
    public AllGamesRecord() {
        this.gameRecordList = new ArrayList<GameRecord>();
    }

    // Adds a new game record to the gameRecordList
    public void add(GameRecord gr) {
        this.gameRecordList.add(gr);
    }

    // Calculates the average score of all game records in the list
    public double average() {
        double sum = 0;
        double score = 0;
        for (GameRecord gr : gameRecordList) {
            score = gr.getScore();
            sum = sum + score;
        }
        return sum / this.gameRecordList.size();
    }

    // Calculates the average score for a specific player based on playerId
    public double average(String playerId) {
        double sum = 0;
        double score = 0;
        int count = 0;
        for (GameRecord gr : gameRecordList) {
            if (gr.getPlayerId().equals(playerId)) {
                score = gr.getScore();
                sum = sum + score;
                count++;
            }
        }
        return count > 0 ? sum / count : 0; // Check to avoid division by zero if no records found
    }

    // Returns a list of the top "n" highest-scoring game records
    public List<GameRecord> highGameList(int n) {
        List<GameRecord> sortedRecords = new ArrayList<GameRecord>(gameRecordList);
        Collections.sort(sortedRecords, Collections.reverseOrder()); // Sort from high to low

        List<GameRecord> topRecords = new ArrayList<GameRecord>();
        int i = 0;
        for (GameRecord gr : sortedRecords){
            if (i == n) {
                break;
            }
            topRecords.add(gr);
            i++;
        }
        return topRecords;
    }

    // Returns a list of the top "n" highest-scoring game records for a specific player
    public List<GameRecord> highGameList(String playerId, int n) {
        List<GameRecord> sortedRecords = new ArrayList<GameRecord>(gameRecordList);
        Collections.sort(sortedRecords, Collections.reverseOrder()); // Sort from high to low

        List<GameRecord> topRecords = new ArrayList<GameRecord>();
        int i = 0;
        for (GameRecord gr : sortedRecords){
            if (i == n) {
                break;
            }
            if (gr.getPlayerId().equals(playerId)) {
                topRecords.add(gr);
                i++;
            }
        }
        return topRecords;
    }
}