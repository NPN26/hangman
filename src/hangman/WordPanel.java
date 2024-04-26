package hangman;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WordPanel extends JPanel{
	HealthPanel HP;
	private String word;
	private char[] wordChar;
	private JLabel[] letterLabels;
	
	// Constructor
	public WordPanel(String word) {
		this.word = word;
		
		// Store it into an array
		wordChar = word.toCharArray();
		
		// Assigns the size of the array
		letterLabels = new JLabel[wordChar.length];
		
		// Create the underscores
		for (int i = 0; i < wordChar.length; i++) {
			letterLabels[i] = new JLabel("_");
			letterLabels[i].setFont(new Font("SansSerif", Font.PLAIN, 48));
			
			add(letterLabels[i]);
		}
	}

	// Method to check if the the guessed letter exists in the word
	public boolean guess(String letter) {
		boolean containsLetter = false;
		
		// Goes through each letter in the word
		for (int i = 0; i < wordChar.length; i++) {
			
			// Condition to check if the guessed letter exists in the word
			if(String.valueOf(wordChar[i]).equalsIgnoreCase(letter)) {
				
				// Changes the text to the letter if letter exists
				letterLabels[i].setText(String.valueOf(letter));
				
				// Changes to true to indicate that the guess was correct
				containsLetter = true;
			}
		}
		return containsLetter;
	}
	
	// Method to check if the whole word is guessed
	public boolean wordGuessed() {
		boolean isWordGuessed = true;
		
		// Goes through each label to check if it contains "_"
		for (JLabel i: letterLabels) {
			if(i.getText().equalsIgnoreCase("_")) {
				
				// Sets it as false to indicate that the whole word is not guessed 
				// Since "_" is present
				isWordGuessed = false;
				
				// Exists the loop since if "_" exists the whole word is not guessed
				break;
			}
		}
		return isWordGuessed;
	}

	public String getWord() {
		return word;
	}
}
