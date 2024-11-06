import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WheelOfFortuneObject {

    // define instance variable
    private String phrase;
    private StringBuilder hiddenPhrase;
    private String guessedLetters;
    private int lives;

    // create constructor to initialize instance variables
    public WheelOfFortuneObject() {
        this.phrase = randomPhrase();
        this.hiddenPhrase = generateHiddenPhrase();
        this.guessedLetters = "";
        this.lives = 3;
    }

    // randomly selects phrase from phrase.txt
    public String randomPhrase(){
        List<String> phraseList=null;

        try {
            phraseList = Files.readAllLines(Paths.get("/Users/joshv/repos/wheel-of-fortune-inheritance/src/src/phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }

        // Get a random phrase from the list
        Random rand = new Random();
        int r = rand.nextInt(phraseList.size()); // gets 0, 1, or 2
        return phraseList.get(r);
    }

    // displays How To Play Game instructions
    public  void instructions() {
        // Greeting and Instructions
        String greeting = "WELCOME TO...%nTHE WHEEL OF FORTUNE! %n%n";
        String instructions = "HOW TO PLAY%n______________%n%nYou will be shown a Hidden Phrase that you will need to guess %nThe instructions are as follows%n\t1. You may ONLY guess one letter at a time%n\t\ta. If your guess is CORRECT, the place of the letter(s) will be revealed in the Hidden Phrase%n\t\tb. If your guess is INCORRECT, you gain ONE strike. If you gain THREE strikes. Just like in soccer, you are OUT! %n__________________________________________________________________%n%n%n";
        System.out.printf(greeting);
        System.out.printf(instructions);
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

    // prompts user to make guess
    public String getGuess() {
        String userGuess = "";
        Scanner scanner =  new Scanner(System.in);
        System.out.printf("%n%n");
        System.out.println("Hidden Phrase: " + hiddenPhrase);

        while (userGuess.isEmpty()) {
            // Get user guess - getGuess();
            String promptUser = "Enter your guess: ";
            System.out.printf(promptUser);
            userGuess = scanner.nextLine();

            // Check User Guess - processGuess();
            if (userGuess.trim().isEmpty()) {
                System.out.println("You did not input anything. Please try again.");
            }
        }

        char guess = Character.toLowerCase(userGuess.charAt(0));

        // Keep record of User Guesses
        if (guess >= 'a' && guess <= 'z') {
            if (guessedLetters.isEmpty()) {
                guessedLetters += guess;
            } else if(guessedLetters.indexOf(guess) != -1) {
                System.out.println("You've already guessed '" + guess + "'. Please try again.");
            } else {
                guessedLetters += ", " + guess;
            }
            System.out.println("Guesses: [ " + guessedLetters + " ]");
        } else {
            System.out.println("This is not a valid guess. Please try again :)"); // Prompt if user enters non-letter
        }

        return userGuess;
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

    // Game-Code Starts and checks for whether the User has successfully
    // uncovered the hiddenPhrase or if the User has lost all of their lives
    public void startGame() {
        instructions();
        boolean phraseFound = false;

        while (!phraseFound) {
            String userGuess = getGuess();

            for (int i = 0; i < userGuess.length(); i++) {
                char guess = userGuess.charAt(i);

                processGuess(guess);

                // checks if you won or lost
                if (phrase.equals(hiddenPhrase.toString())) {
                    phraseFound = true;
                    System.out.println("Woohoo you won!");
                    System.out.printf("You Correctly Found the Phrase: '" + phrase + "'%n");
                    break;
                } else if (lives == 0) {
                    System.out.printf("GAME OVER. You ran out of lives :(%nTry Again!%n");
                    System.out.println("The Hidden Phrase was: '" + phrase + "'");
                    break;
                }
            }

        } // end of while loop
    }

    // main method that creates a fun added User option to play the game,
    // creates new WheelOfForutneObject() object and accesses the
    // startGame() method within the new WheelOfFortuneObject() object
    public static void main(String[] args) {
        System.out.println("Would you like to play a game? [ Enter 'y' OR 'n' ]");
        Scanner scanner = new Scanner(System.in);
        String playGame = scanner.nextLine();
        if (playGame.equals("y") || playGame.equals("yes")) {
            WheelOfFortuneObject game = new WheelOfFortuneObject();
            game.startGame();
        } else if (playGame.equals("n") || playGame.equals("no")) {
            System.out.println("Your Loss.");
        } else {
            System.out.println("Um what? Please Try Again.");
        }

    }
}