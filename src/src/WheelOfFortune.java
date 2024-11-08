import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Random;

public abstract class WheelOfFortune extends GuessingGame {
    protected String phrase;
    protected StringBuilder hiddenPhrase;
    protected String guessedLetters;

    public WheelOfFortune() {
        super(3);  // Initialize with 3 lives
        resetGame(); // Initialize the game state
    }

    protected String randomPhrase() {
        try {
            var phraseList = Files.readAllLines(Paths.get("phrases.txt"));
            Random rand = new Random();
            return phraseList.get(rand.nextInt(phraseList.size()));
        } catch (IOException e) {
            System.out.println("Error loading phrases: " + e.getMessage());
            return "Error loading phrases";
        }
    }

    protected StringBuilder generateHiddenPhrase() {
        StringBuilder hidden = new StringBuilder();
        for (char ch : phrase.toCharArray()) {
            hidden.append(Character.isLetter(ch) ? '*' : ch);
        }
        return hidden;
    }

    // Resets the game state for a new game
    @Override
    public void resetGame() {
        this.lives = 3;                 // Reset lives
        this.score = 0;                 // Reset score
        this.phrase = randomPhrase();   // Generate a new phrase
        this.hiddenPhrase = generateHiddenPhrase(); // Generate new hidden phrase
        this.guessedLetters = "";       // Reset guessed letters
    }

    // Specific implementation of processGuess for WheelOfFortune
    @Override
    protected void processGuess(String guess) {
        char guessedChar = guess.charAt(0);
        guessedLetters += guessedChar + " ";

        if (phrase.toLowerCase().contains(String.valueOf(guessedChar))) {
            updateHiddenPhrase(guessedChar);
            int correctGuessCount = countOccurrences(guessedChar);
            score += correctGuessCount * 5; // 5 points per correct letter
        } else {
            lives--;
            score -= 10; // Deduct 10 points for incorrect guess
            System.out.println("Incorrect guess! Lives remaining: " + lives);
        }
    }

    // equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WheelOfFortune that = (WheelOfFortune) o;
        return Objects.equals(phrase, that.phrase) && Objects.equals(hiddenPhrase, that.hiddenPhrase) && Objects.equals(guessedLetters, that.guessedLetters);
    }

    // toString() method
    @Override
    public String toString() {
        return "WheelOfFortune{" +
                "phrase='" + phrase + '\'' +
                ", hiddenPhrase=" + hiddenPhrase +
                ", guessedLetters='" + guessedLetters + '\'' +
                '}';
    }

    // hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phrase, hiddenPhrase, guessedLetters);
    }

    private void updateHiddenPhrase(char guessedChar) {
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.toLowerCase(phrase.charAt(i)) == guessedChar) {
                hiddenPhrase.setCharAt(i, phrase.charAt(i));
            }
        }
    }

    private int countOccurrences(char guessedChar) {
        int count = 0;
        for (char ch : phrase.toLowerCase().toCharArray()) {
            if (ch == guessedChar) {
                count++;
            }
        }
        return count;
    }

    // New method: awards points for guessing the full phrase
    protected void processPhraseGuess() {
        if (phrase.equalsIgnoreCase(hiddenPhrase.toString())) {
            score += 100; // Award 100 points for correctly guessing the entire phrase
        }
    }

    public abstract GameRecord play();
    public abstract boolean playNext();
}