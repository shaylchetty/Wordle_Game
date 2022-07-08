public class WordleDriver {

	
	public static void main(String[] args) {
		
		Wordle game = new Wordle("wordle-dictionary.txt");
		game.generateWordle();
		
		System.out.println("Thank you for playing wordle, you have six guesses remaining!\n");
		while(!game.isOver()) {
			
			game.takeGuess();
			if(!game.isOver())
				game.printWords();
			
		}
	}
}
