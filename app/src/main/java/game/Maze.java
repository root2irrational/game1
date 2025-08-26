package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Maze extends JPanel implements ActionListener, KeyListener {
	public static final int TILE_SIZE = 25;
	public static final int ROWS = 20;
	public static final int COLUMNS = 20;

	public Maze() {
        // set the game board size
        setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS));
        // set the game board background color
        setBackground(new Color(232, 232, 232));

    }
	@Override
    public void actionPerformed(ActionEvent e) {
		
	}

	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g); // Call superclass method for proper painting
        // Drawing logic for your map goes here
        g.setColor(Color.GREEN);
        g.fillRect(50, 50, 100, 100); // Example: Draw a green square
	}

	@Override
    public void keyTyped(KeyEvent e) {
        // this is not used but must be defined as part of the KeyListener interface
    }
	@Override

    public void keyPressed(KeyEvent e) {
        // react to key down events
    }

	@Override
    public void keyReleased(KeyEvent e) {
        // react to key up events
    }

}
