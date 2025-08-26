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
	public static final int TILE_SIZE = 50;
	public static final int ROWS = 10;
	public static final int COLUMNS = 10;
    // Maze symbols
    public static final char WALL  = '#';
    public static final char PATH  = ' ';
    public static final char START = 'S';
    public static final char EXIT  = 'E';

    // Define the maze
    public static final char[][] MAZE = {
        { PATH, PATH, WALL, WALL, WALL, PATH, PATH, WALL, WALL, WALL },
        { WALL, PATH, PATH, PATH, WALL, PATH, PATH, WALL, WALL, WALL },
        { WALL, PATH, PATH, WALL, WALL, WALL, PATH, WALL, PATH, PATH },
        { WALL, WALL, PATH, WALL, WALL, WALL, WALL, PATH, PATH, WALL },
        { WALL, START, PATH, PATH, WALL, PATH, PATH, PATH, PATH, PATH },
        { WALL, WALL, WALL, PATH, WALL, PATH, WALL, WALL, PATH, WALL },
        { WALL, PATH, PATH, PATH, PATH, PATH, WALL, PATH, PATH, WALL },
        { WALL, PATH, WALL, WALL, WALL, PATH, WALL, PATH, WALL, WALL },
        { WALL, PATH, PATH, EXIT, PATH, PATH, PATH, PATH, PATH, WALL },
        { WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL }
    };

	public Maze() {
        // set the game board size
        setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS));
        // set the game board background color
        setBackground(new Color(0, 0, 0));

    }
	@Override
    public void actionPerformed(ActionEvent e) {
		
	}

	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g); // Call superclass method for proper painting
        // Drawing logic for your map goes here
       for (int i = 0; i < MAZE[0].length; i++) {
            for (int j = 0; j < MAZE[0].length; j++) {
                switch (MAZE[i][j]) {
                    case START -> {
                        g.setColor(Color.BLUE);
                    }
                    case WALL -> {
                        g.setColor(Color.YELLOW);
                    }
                    case PATH -> {
                        g.setColor(Color.GRAY);
                    }
                    case EXIT -> {
                        g.setColor(Color.GREEN);
                    }
                    default -> {
                    }
                }
                // g.fillRect(i, j, TILE_SIZE, TILE_SIZE);
                g.fillRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
       }
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
