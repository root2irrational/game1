package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;

public class Maze extends JPanel implements ActionListener, KeyListener {
	private boolean[][] mazeGrid;
    private int tileSize = 10; // size of each tile in pixels
    Point start, exit;
    // Music player

	public Maze() {
        // set the game board size
        // setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS));
        // set the game board background color
        // setBackground(new Color(0, 0, 0));
        String filePath = "/images/maze2.png";
        loadMaze(filePath);
        setPreferredSize(new Dimension(mazeGrid[0].length * tileSize, mazeGrid.length * tileSize));
        String musicPath = "/music/zhitanNHH.wav";
        playMusic(musicPath);
    }
    private void playMusic(String path) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource(path));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();        // start playing
            clip.loop(Clip.LOOP_CONTINUOUSLY); // loop in background
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMaze(String imagePath) {
        try {
            BufferedImage mazeImage = ImageIO.read(Maze.class.getResourceAsStream(imagePath));
            int width = mazeImage.getWidth();
            int height = mazeImage.getHeight();

            int cols = width / tileSize;
            int rows = height / tileSize;
            mazeGrid = new boolean[rows][cols];

            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    // sample top-left pixel of the tile
                    int pixel = mazeImage.getRGB(x * tileSize, y * tileSize);
                    Color color = new Color(pixel);
                    mazeGrid[y][x] = color.equals(Color.BLACK); // wall if black
                }
            }

            // Find start
            start = null;
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    if (!mazeGrid[y][x]) { // path tile
                        start = new Point(x, y);
                        break;
                    }
                }
                if (start != null) break;
            }

            // Find exit
            exit = null;
            for (int y = rows - 1; y >= 0; y--) {
                for (int x = cols - 1; x >= 0; x--) {
                    if (!mazeGrid[y][x]) { // path tile
                        exit = new Point(x, y);
                        break;
                    }
                }
                if (exit != null) break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	@Override
    public void actionPerformed(ActionEvent e) {
		
	}

	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g); // Call superclass method for proper painting
        // Drawing logic for your map goes here
       super.paintComponent(g);

        if (mazeGrid == null) return;

        for (int y = 0; y < mazeGrid.length; y++) {
            for (int x = 0; x < mazeGrid[0].length; x++) {
                if (mazeGrid[y][x]) {
                    g.setColor(Color.BLACK); // wall
                } else {
                    g.setColor(Color.WHITE); // path
                }

                if (x == start.x && y == start.y) {
                    g.setColor(Color.RED);
                } else if (x == exit.x && y == exit.y) {
                    g.setColor(Color.GREEN);
                }
                g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
            }
        }
        if (!mazeGrid[20][10]) {
            g.setColor(Color.GREEN);
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
