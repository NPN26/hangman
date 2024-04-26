package hangman;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HealthPanel extends JPanel{
	private int maxIncorrectGuesses;
    private int HP;
    private JLabel[] lifeLabels;
    
    // Constructor
    public HealthPanel(int maxIncorrectGusses) {
    	this.maxIncorrectGuesses = maxIncorrectGusses;
    	this.HP = maxIncorrectGuesses;
    	this.lifeLabels = new JLabel[maxIncorrectGuesses];
    	
    	// Create the HP based on count
        for (int i = 0; i < maxIncorrectGusses; i++) {
            this.lifeLabels[i] = new JLabel("❤");
            this.lifeLabels[i].setFont(new Font("SansSerif", Font.PLAIN, 48));
            this.lifeLabels[i].setForeground(Color.GREEN);
            add(this.lifeLabels[i]);
        }
    	
    	
	}
    
    // Method to remove 1 HP
    public boolean removeHP() {
    	// To check if player ran out of HP
    	if(HP > 0) {
    		lifeLabels[HP-1].setForeground(Color.RED);
    		
            HP--;
            return HP>0;
    	}
    	// Returns false to indicate out of lives
    	return false;
    }
}
