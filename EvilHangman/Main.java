package hangman;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.regex.Pattern;
import java.util.Set;
import java.util.*;
import java.util.List;
import java.util.Collection;
import java.io.FileNotFoundException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
        EvilHangmanGame game = new EvilHangmanGame();
		File FileName = new File(args[0]);		
        int wordLength = Integer.parseInt(args[1]);
        int guesses = Integer.parseInt(args[2]);
        String oldHangWord = "";
        Set<String> words = new HashSet<String>();
        String hangWord = "";

        game.startGame(FileName, wordLength);

        while (guesses > 0) {
            oldHangWord = game.wordToGuess.toString();
    		System.out.println("You have " + guesses + " guesses left");
	    	System.out.print("Used letters:");
	    	for (char c : game.guessedLetters) {
	    		System.out.print(" " + c);
	    	}
	    	System.out.println();
	    	System.out.println("Word: " + game.wordToGuess.toString());
	    	
	    	System.out.print("Enter guess: ");
            
            Scanner userIn = new Scanner(System.in); 
            String s = "";
            boolean isLetter = false;
            while (!isLetter) {
                userIn = new Scanner(System.in); 
                s = userIn.nextLine();
                if (Pattern.matches("[a-zA-Z]+", s)) {
                    isLetter = true;
                }
                else {
                    System.out.println("Please enter a valid character");
                }
                if (s.length() > 1) {
                    isLetter = false;
                    System.out.println("Please enter a single character");
                }       
            }
            words = game.makeGuess(Character.toLowerCase(s.charAt(0)));           
            if (game.wordToGuess.toString().equals(oldHangWord) && game.isEx() == 0) {
                guesses--;
            }
            oldHangWord = game.wordToGuess.toString();
            if (isWin(game.wordToGuess.toString())) {
                System.out.println("You Win!\n" + "The word was: " + game.wordToGuess.toString());
                return;
            }
            else if (guesses == 0) {
                int word = new Random().nextInt(words.size());
                int i = 0;
                for (String str : words) {
                    if (i == word) {
                        hangWord = str;
                    }
                    i++;
                }
                System.out.println("You Lose!\n" + "The word was: " + hangWord);
            }
        }
	}

    public static boolean isWin(String str) {
        boolean userWin = true;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '-') {
                userWin = false;
            }
        }
        return userWin;
    }
}

