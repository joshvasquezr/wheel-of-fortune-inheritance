import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class AllGamesRecord {

    private List<GameRecord> gameRecordList;

    // constructor, initializes list
    public AllGamesRecord() {
        this.gameRecordList = new ArrayList<GameRecord>();
    }

    // adds new game record to gameRecordList
    public void add(GameRecord gr) {
        this.gameRecordList.add(gr);
    }

    // calcs average of the whole list
    public double average() {
        double sum = 0;
        double score = 0;
        for (GameRecord gr : gameRecordList) {
            score = gr.getScore();
            sum = sum + score;
        }
        return sum / this.gameRecordList.size();
    }

    // calcs the average of a particular player's scores
    public double average(String playerId) {
        double sum = 0;
        double score = 0;
        for (GameRecord gr : gameRecordList) {
            if (gr.getPlayerId().equals(playerId)) {
                score = gr.getScore();
                sum = sum + score;
            }
        }
        return sum / this.gameRecordList.size();
    }

    // use collections to sort from high to low of "n" records
    public List<GameRecord> highGameList (int n) {
        List<GameRecord> sortedRecords = new ArrayList<GameRecord>(gameRecordList);
        Collections.sort(sortedRecords);

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

    // use collections to sort from high to low of "n" records
    public List<GameRecord> highGameList (String playerId, int n) {
        List<GameRecord> sortedRecords = new ArrayList<GameRecord>(gameRecordList);
        Collections.sort(sortedRecords);

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
