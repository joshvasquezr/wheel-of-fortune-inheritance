import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

abstract class WheelOfFortune extends Game {
    protected String guessedLetters;
    protected int score;

    // Constructor: sets up a random phrase, hidden phrase, initializes guessed letters and score
    public WheelOfFortune() {
        super();
        this.phrase = randomPhrase();
        this.hiddenPhrase = generateHiddenPhrase();
        this.guessedLetters = "";
        this.score = 0;
    }

    // Resets the game by setting lives, score, phrase, hidden phrase, and guessed letters
    public void resetGame() {
        this.lives = 3;  // Reset lives to the initial count
        this.score = 0;  // Reset score to zero
        this.phrase = randomPhrase();  // Get a new random phrase
        this.hiddenPhrase = generateHiddenPhrase();  // Generate the hidden version of the new phrase
        this.guessedLetters = "";  // Clear any guessed letters
    }

    // Selects a random phrase from a list of phrases in a file
    public String randomPhrase() {
        List<String> phraseList = null;
        try {
            phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
        Random rand = new Random();
        return phraseList.get(rand.nextInt(phraseList.size()));
    }

    // Creates the hidden version of the phrase, with letters replaced by '*'
    public StringBuilder generateHiddenPhrase() {
        StringBuilder hiddenPhrase = new StringBuilder();
        for (char ch : phrase.toCharArray()) {
            if (Character.isLetter(ch)) {
                hiddenPhrase.append('*');
            } else {
                hiddenPhrase.append(ch);
            }
        }
        return hiddenPhrase;
    }

    // Abstract method: should be implemented to return a guessed character
    public abstract char getGuess();

    // Processes a single letter guess, updating the hidden phrase, score, and lives
    public void processGuess(char guess) {
        int correctGuess = 0;
        for (int i = 0; i < phrase.length(); i++) {
            if (guess == Character.toLowerCase(phrase.charAt(i))) {
                hiddenPhrase.setCharAt(i, phrase.charAt(i));
                correctGuess++;
            }
        }
        if (correctGuess > 0) {
            score += correctGuess * 5; // Add 5 points for each correct letter guessed
        } else {
            lives--;
            score -= 10; // Deduct 10 points for an incorrect guess
            System.out.println("Incorrect guess! Lives remaining: " + lives);
        }
    }

    // Checks if the entire phrase was guessed and awards 100 points if correct
    public void processPhraseGuess() {
        if (phrase.equals(hiddenPhrase.toString())) {
            score += 100; // Add 100 points for guessing the entire phrase correctly
        }
    }
}