package hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FileIO extends ArrayList<String> {
	Random random = new Random();
	private final String FILENAME = "/Users/nirup/git/gui_coursework/src/nouns.txt";
	
	// Load the words into program
	public void loadWords() {
		// Try-Catch
		try(BufferedReader reader = new BufferedReader(new FileReader(FILENAME))){
			String word = reader.readLine();
			// Adds all the words in the txt file
			while(word != null) {
				add(word);
				word = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getWord() {
		// Gets a random number in the range of the index
		int index = random.nextInt(0,size()-1);
		String word = get(index);
		return word;
	}
}
