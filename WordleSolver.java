import java.util.ArrayList;

public class WordleSolver {

	public static void main(String[] args) {
	}
 
	
	public static ArrayList<String> help(ArrayList<String> flw, ArrayList<Character> match, ArrayList<Character> include)
	{
		//removes words that are from the match array (Letters that are in the RIGHT SPOT)	
		for (int a = flw.size()-1 ; a >= 0; a--) //traverses backwards through dictionary
		{
			int numLetter = 5;
			int count = 0; //counts number of chars that are in both the match array and the dictionary word
			String s = flw.get(a); //converts dictionary word to a char array
			char dict[] = s.toCharArray();
			for (int b = 0; b < match.size(); b++)  //goes through the match array for ex. [b, r, ?, i, n]
			{	
				if(match.get(b) == dict[b]) //if there is a match between the match array and dictionary char array then count is incremented
				{
					count++;
				}	
			}
			for (int i = 0; i < match.size(); i++) //removes all question marks from match array
			{
				if (match.get(i) == '?') {
					numLetter--;
				}
			}
			if (!(count == numLetter)) {//if the number of chars now in the match array (question marks have been removed) is  NOT equal to the count variable, the word is removed
					flw.remove(a);
			}
		}
		//removes words that are from the include array (letters that are in the WRONG SPOT)
		
		for (int k = 0; k < include.size(); k++)
		{
			for (int  i = flw.size()-1; i >= 0; i--)
			{
				if (!(flw.get(i).contains(include.get(k).toString()))) {
					flw.remove(i);
				}
			}
		}
		return flw;
	}

}
