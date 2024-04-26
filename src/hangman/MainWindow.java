package hangman;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainWindow extends JFrame implements GameOverListener{
	HealthPanel healthPanel;
	FileIO fileIO;
	WordPanel wordPanel;
	ButtonPanel buttonPanel;
	GameOverListener gameOverListener;
	
	// Constructors
	public MainWindow() {
		setLayout (new GridLayout(4,1));
		
		// Introduction
		JPanel p1 = new JPanel();
    	JLabel l1 = new JLabel("Welcome to the Game");
    	l1.setFont(new Font("SansSerif", Font.PLAIN, 48));
    	p1.add(l1);
    	add(p1);
    	
    	newGame(true);
    	
		// Box layout not working
		//setLayout (new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        // Frame
		setSize(1000, 750);
		setTitle("HangMan");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	// Method to Start a new Game
	public void newGame(boolean isFirstGame) {
		// Checks and deletes the Panels if it already exists
		if(!isFirstGame) {
			remove(healthPanel);
			remove(wordPanel);
			remove(buttonPanel);
		}
		
		// Health Panel
		healthPanel = new HealthPanel(difficultySelection());
        add(healthPanel);
	        
        // gets a Lists of Words
        fileIO = new FileIO();
     	fileIO.loadWords();
	     
     	// Get a random word
     	String word = fileIO.getWord();
	        
        // Word Panel
        wordPanel = new WordPanel(word);
        add(wordPanel);
	        
        // Letter buttons Panel
        buttonPanel = new ButtonPanel(wordPanel,healthPanel,this);
        add(buttonPanel);
        
        validate();
	}
	
	public int difficultySelection() {
		String[] difficulty = {"Easy(10)","Medium (7)","Hard(5)"};
		int difficultyInt = 0;
		String difficultyOption = (String) JOptionPane.showInputDialog(this, "Select a Difficulty :", "Game Difficulty", JOptionPane.INFORMATION_MESSAGE,null, difficulty, difficulty[1]);
		
		// Exit if User choose cancel on selection
		if(difficultyOption == null) {
			System.exit(0);
		}
		else if(difficultyOption.equals("Easy(10)")) {
			difficultyInt = 10;
		}
		else if(difficultyOption.equals("Medium (7)")) {
			difficultyInt = 7;
		}
		else {
			difficultyInt = 5;
		}
		return difficultyInt;
	}

	@Override
	public void gameOver(boolean isGameOver) {
		// Sets all the panels invisible
		healthPanel.setVisible(false);
		wordPanel.setVisible(false);
		buttonPanel.setVisible(false);
		
		String message;
		
		// Checks if player guessed the word
		if(wordPanel.wordGuessed()) {
			message = "Congrats you found the word. The word was : " + wordPanel.getWord();
		}
		else {
			message = "Oh no, You ran out of lives. The word was : " + wordPanel.getWord();
		}
		
		// Displays a message informing the User the game is over and giving the option to Start a new game or Quit
		int gameOption = JOptionPane.showConfirmDialog(this, message + "\nDo you want to start a new game?", "Game Over", JOptionPane.YES_NO_OPTION);
		
		if(gameOption == JOptionPane.YES_OPTION) 
			newGame(false);
		else
			System.exit(0);
	}
}
