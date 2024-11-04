import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class WheelOfFortune extends Game {

    // randomly selects phrase from phrase.txt
    public String randomPhrase(){
        List<String> phraseList=null;

        try {
            phraseList = Files.readAllLines(Paths.get("/Users/joshv/repos/wheel-of-fortune-inheritance/src/phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }

        // Get a random phrase from the list
        Random rand = new Random();
        int r = rand.nextInt(phraseList.size()); // gets 0, 1, or 2
        return phraseList.get(r);
    }


    // encrypts randomly selected phrase from randomPhrase()
    public StringBuilder generateHiddenPhrase() {
        hiddenPhrase = new StringBuilder();
        for (int i = 0; i < phrase.length(); i++) {
            char ch = phrase.charAt(i);
            if (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z') {
                hiddenPhrase.append('*');
            } else {
                hiddenPhrase.append(ch);
            }
        }
        return hiddenPhrase;
    }


    // Processes the User's guess to see if it is found in the hidden phrase or not
    public void processGuess(char guess) {

        int correctGuess = 0;

        for (int i = 0; i < phrase.length(); i++) {
            char phraseChar = phrase.charAt(i);

            // if TRUE: update hiddenPhrase, lettersFound, else check if user found the whole phrase
            if (guess == Character.toLowerCase(phraseChar)) {
                hiddenPhrase.setCharAt(i, phraseChar);
                correctGuess++;
            }
        }

        // Checks if the user a letter NOT FOUND in the Hidden Phrase and takes away ONE life
        if (correctGuess == 0) {
            lives -= 1; // PENALTY: Minus One Life for incorrect guess
            System.out.printf("Incorrect Guess! You now have [" + lives + "] lives left.%n");
        }

    }

}
