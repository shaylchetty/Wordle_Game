import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Wordle {

	private static ArrayList<String> words = new ArrayList<String>(); //array list for words from dictionary
	static boolean over = false;
	static ArrayList<Character> match = new ArrayList<Character>(Arrays.asList('?', '?', '?', '?', '?'));	//shows letters that are in the right spot
	static ArrayList<Character> include = new ArrayList<Character>(); //shows letters that are in the wrong spot, but still in the word
	static ArrayList<Character> letters = new ArrayList<Character>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));	
	static String wordle;
	static int guesses = 6; //user receives 6 guesses before game over
	static String guess;
	static int count = 0;
	static ArrayList<Character> wordleChar = new ArrayList<Character>(); 
	
	/**
	 * gameover at 6 guesses
	 * @return
	 */
	public boolean isOver() {
		return over;
	}

	/**
	 * ends the game
	 * @param over
	 */
	public void setOver(boolean over) {
		Wordle.over = over;
	}

	/**
	 * checks to see if the guess is the same as the wordle
	 * @param g
	 * @param h
	 * @return
	 */
	public static boolean aEquals(char[] g, ArrayList<Character> h) {
		int count = 0;
		for (int i = 0; i < 5; i++) {
			if (g[i] == h.get(i)) {
				count++;
			}
		}
		if (count == 5) {
			return true;
		}
		return false;
	}	
		

	/**
	 * reads the dictionary into the words array list
	 * @param filename
	 */
	public Wordle(String filename) {
		try {
			//scans the text file and adds each word into ArrayList words
			Scanner sc = new Scanner(new File(filename));
			while(sc.hasNext())
				words.add(sc.next());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * chooses random word from the dictionary as the wordle
	 */
	public void generateWordle() {
		int x = (int) (Math.random()*(words.size()));
		wordle = words.get(x);
		for (int i = 0; i < wordle.toCharArray().length; i++) {
			wordleChar.add(wordle.toCharArray()[i]);
		}
	}
	
	/**
	 * reads the user's guess, and looks for whether letters are in the right spot or in the word
	 */
	public void takeGuess() {
		Scanner gu = new Scanner(System.in);
		guess = gu.nextLine();
		char guessArray[] = guess.toLowerCase().toCharArray();
		if (guessArray.length == 5 && words.contains(guess.toLowerCase())) {
			removeLetters(guessArray);
			for (int i = 0; i < guessArray.length;i++) {
				if(wordleChar.get(i) == guessArray[i]) {
					match.set(i, guessArray[i]);
				}
				else {
					if(wordleChar.contains(guessArray[i]) && wordleChar.get(i) != guessArray[i] && !include.contains(guessArray[i]) && !match.contains(guessArray[i])) {
						include.add(guessArray[i]);
					}
				}
			}
			

			if(aEquals(guessArray, wordleChar)) {
				System.out.println("Correct! You used " + (7 - guesses) + " guesses.");
				over = true;
			}
			guesses -= 1;
			if (guesses == 0 && !(aEquals(guessArray, wordleChar))) {
				over = true;
				System.out.println("Congratulations, you failed!");
			}
			
		}
		
		
		
		else {
			System.out.println("Not a valid word\n");
		}
	}
	
	/*
	 * checks to see if the wordle is the same as the guess
	 */
	public void win() {
		System.out.println(wordle);
		System.out.println(guess);
		if (guess.toLowerCase() == wordle.toLowerCase()) {
			System.out.println("won");

		}
	}
	
	public static void removeLetters(char words[]) {
		for(int i = 0; i < words.length; i++) {
			if (letters.contains(words[i])) {
				
				letters.remove(letters.indexOf(words[i]));
			}
		}
	}
	
	public void printWords() {
		System.out.print("Letters unused: ");
		for (int i = 0; i<letters.size();i++) {
			System.out.print(letters.get(i) );
			if (i < letters.size()-1) {
				System.out.print(", ");
			}
		}
		System.out.println();
		System.out.println("Array of chars that are in the correct position: " + match);
		System.out.println("Array of chars that are in the word but not the correct position: " + include);
		words = WordleSolver.help(words, match, include);
		words.remove(guess);
		System.out.println("Possible words: " + words);
		System.out.println();
	}
}
