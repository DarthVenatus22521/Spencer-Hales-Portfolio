package hangman;

import java.util.Scanner;
import java.io.File;
import java.util.Set;
import java.util.*;
import java.util.List;
import java.util.Collection;
import java.io.FileNotFoundException;


public class EvilHangmanGame implements IEvilHangmanGame {


	Set<String> words = new HashSet<String>();
	Set<Character> guessedLetters = new HashSet<Character>();
	Set<Set<String>> wordFamilies = new HashSet<Set<String>>();
	StringBuilder wordToGuess = new StringBuilder();
	int numGuesses = 0;
    int isException = 0;


	@SuppressWarnings("serial")

	public static class GuessAlreadyMadeException extends Exception {
	    public void GuessAlreadyMadeException() {
    		System.out.println("You already used that letter");
      }
	}


	public void startGame(File dictionary, int wordLength) {
		words = new HashSet<String>();
		guessedLetters = new HashSet<Character>();
		wordFamilies = new HashSet<Set<String>>();
		wordToGuess = new StringBuilder();
		wordToGuess.setLength(wordLength);
		numGuesses = 0;

		for (int i = 0; i < wordToGuess.length(); i++) {
			wordToGuess.setCharAt(i, '-');
		}
        try {
		Scanner in = new Scanner(dictionary).useDelimiter("[^a-zA-Z]+");
        String testStr = "";		
        while (in.hasNext()) {
            testStr = in.next();
            if (testStr.length() == wordLength) {
                words.add(testStr);
			}
		}
		in.close();	
	    }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
  }

	public Set<String> makeGuess(char guess) {
        isException = 0;
        try {
 	       	for (char c : guessedLetters) {
   	       		if (guess == c) {
   	       			throw new GuessAlreadyMadeException();
   	       		}
   	        }
	    	Set<Integer> charLoc = new HashSet<>();
    
	    	for (String s : words) {
	    		charLoc = whereChar(s, guess);
	    		addToFamily(s, charLoc, guess);			
	    		charLoc.clear();
	    	}
            words.clear();
	    	replaceDict(guess);
            if (updateWord(guess)) {
                int numOfChars = numChar(words, guess);
                if (numOfChars == 1) {
                    System.out.println("Yes, there is 1 " + guess);
                }
                else {
                    System.out.println("Yes, there is " + numOfChars + " " + guess + "'s");
                }
            }
            else {
                System.out.println("Sorry, there are no " + guess + "'s");
            }
            guessedLetters.add(guess);
   	    }
        catch(GuessAlreadyMadeException ex) {
            ex.GuessAlreadyMadeException();
            isException = 1;
        }
        wordFamilies.clear();
		return words;
    }

	public void addToFamily(String word, Set<Integer> toMatch, char guess) {
		Set<Integer> setCharLoc = new HashSet<>();
        String str = "";
		for (Set<String> s : wordFamilies) {
			if (!s.isEmpty()) {
                str = s.iterator().next();
    			setCharLoc = whereChar(str, guess);
				if (setCharLoc.equals(toMatch)) {
					s.add(word);
					return;
				}
			}
			setCharLoc.clear();
		}
		Set<String> toAdd = new HashSet<>();
		toAdd.add(word);
		wordFamilies.add(toAdd);
	}

	public Set<Integer> whereChar(String str, char guess) {
		Set<Integer> charLoc = new HashSet<>();
		for (int i = 0; i < str.length(); i++) {
			if (guess == str.charAt(i)) {
				charLoc.add(i); 
			}
		}
		return charLoc;
	}
// Add all boundary cases listed on specs
	public void replaceDict(char c) {
		int largestSet = 0;
		for (Set set : wordFamilies) {
			if (set.size() > largestSet) {
				largestSet = setWords(set);
			}
			else if (set.size() == largestSet) {
				int charSet = numChar(set, c);
				int charWords = numChar(words, c);
				if (charSet < charWords) {
					largestSet = setWords(set);
				}
				else if (charSet == charWords) {
	//			    bool isDone = false;
  //                  while (!isDone) {
                        int posSet = posChar(set, c);
                        int posWords = posChar(words, c);
                        if (posSet > posWords) {
                            largestSet = setWords(set);
                        }
//                    }
				}
			}
		}
	}

	public int setWords(Set<String> set) {
		int largestSet = 0;
		words.clear();
		for (String s : set) {
			words.add(s);
		}
		largestSet = set.size();
		return largestSet;
	}

	public int numChar(Set<String> set, char c) {
		int returnInt = 0;
		String str = set.iterator().next();
		for (int i = 0; i < str.length(); i++) {
			if ( c == str.charAt(i)) {
				returnInt++; 
			}
		}
		return returnInt++;
	}

    public int posChar(Set<String> set, char c) {
		int returnInt = 0;
		String str = set.iterator().next();
		for (int i = str.length() - 1; i >= 0; i--) {
			if ( c == str.charAt(i)) {
				return i; 
			}
		}
		return returnInt;
	}
    
    public boolean updateWord(char c) {
        boolean toReturn = false;
        String str = words.iterator().next();
        for (int i = 0; i < str.length(); i++) {
			if ( c == str.charAt(i)) {
				wordToGuess.setCharAt(i, c);
                toReturn = true;
			}
		}
        return toReturn;
    }
    public int isEx() {
        return isException;
    }
}
