package hangman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel implements ActionListener, KeyListener{
	WordPanel word; 
	HealthPanel healthPanel;
	private JButton[] letterButtons = new JButton[26];
	private char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	GameOverListener listener;
	
	// Constructors
	public ButtonPanel(WordPanel word, HealthPanel healthPanel, GameOverListener listeners) {
		this.word=word;
		this.healthPanel=healthPanel;
		this.listener = listeners;
		
		// Creation of Buttons
		for(int i = 0; i <26; ++i) {
			letterButtons[i]= new JButton(String.valueOf(alphabet[i]));
			
			// Links Keyboard Button and Letter Button
			letterButtons[i].setFocusable(true);
			letterButtons[i].addKeyListener(this);
			
			// Creates a new Action Listener if a letter button is clicked
			letterButtons[i].addActionListener(this);
			add(letterButtons[i]);
		}
	}
	
	// Processes the letter
	private void buttonPressed(String character) {
		boolean isHPAvailable = true;
		
		// Checks if the letter is in the word
		if(!word.guess(character)) {
			// Removes an HP if the guessed letter is not in the word
			isHPAvailable = healthPanel.removeHP();
		}
		
		// Checks if player is out lives or guessed the Word
		if(!isHPAvailable || word.wordGuessed()) {
			listener.gameOver(true);
		}
		
		// Disables the button to indicate it is already pressed
        for (JButton button : letterButtons) {
            if (button.getText().equals(character)) {
                button.setEnabled(false);
                break;
            }
        }
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		char keyChar = e.getKeyChar();
        if (Character.isLetter(keyChar)) {
            String character = String.valueOf(Character.toUpperCase(keyChar));
            buttonPressed(character);
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton sourceButton = (JButton) e.getSource();
		String character = sourceButton.getText();
		
		buttonPressed(character);
	}
}
