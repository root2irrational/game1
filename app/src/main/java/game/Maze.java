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
    public static final int tileSize = 1; // size of each tile in pixels
    public static final int scaleFactor = 50;
    public static final int drawSize = scaleFactor * tileSize;
    Point start, exit;
    private Player player;

    private int viewCols = 15; // how many columns to show on screen
    private int viewRows = 15; // how many rows to show


	public Maze() {
        // set the game board size
        // setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS));
        // set the game board background color
        // setBackground(new Color(0, 0, 0));
        String filePath = "/images/maze3.png";
        loadMaze(filePath);
        // setPreferredSize(new Dimension(mazeGrid[0].length * drawSize, mazeGrid.length * drawSize));
        setPreferredSize(new Dimension(viewCols * drawSize,
                               viewRows * drawSize));
        String musicPath = "/music/zhitanNHH.wav";
        playMusic(musicPath);
        // Create the player at the start
        player = new Player(1, start.x, start.y); // speed=1 means move 1 tile at a time
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
    public void mazeTiles(Graphics g) {
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
                g.fillRect(x * drawSize, y * drawSize, drawSize, drawSize);
            }
        }
    }
    
    public void playerCamera(Graphics g) {
        int drawSize = tileSize * scaleFactor;
        int halfCols = viewCols / 2;
        int halfRows = viewRows / 2;

        int offsetX = player.getX() - halfCols;
        int offsetY = player.getY() - halfRows;

        // clamp so camera never goes outside maze
        if (offsetX < 0) offsetX = 0;
        if (offsetY < 0) offsetY = 0;
        if (offsetX + viewCols > mazeGrid[0].length) offsetX = mazeGrid[0].length - viewCols;
        if (offsetY + viewRows > mazeGrid.length) offsetY = mazeGrid.length - viewRows;
        for (int y = 0; y < viewRows; y++) {
            for (int x = 0; x < viewCols; x++) {
                int mazeX = x + offsetX;
                int mazeY = y + offsetY;

                if (mazeGrid[mazeY][mazeX]) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }

                if (start != null && mazeX == start.x && mazeY == start.y) {
                    g.setColor(Color.RED);
                } else if (exit != null && mazeX == exit.x && mazeY == exit.y) {
                    g.setColor(Color.GREEN);
                }

                g.fillRect(x * drawSize, y * drawSize, drawSize, drawSize);
            }
        }
        int playerScreenX = (player.getX() - offsetX) * drawSize;
        int playerScreenY = (player.getY() - offsetY) * drawSize;

        g.drawImage(player.getSprite(),
                    playerScreenX,
                    playerScreenY,
                    this);

    }

	@Override
    public void paintComponent(Graphics g) {
        // Drawing logic for your map goes here
       super.paintComponent(g);

        if (mazeGrid == null) return;
        playerCamera(g);
	}

	@Override
    public void keyTyped(KeyEvent e) {
        // this is not used but must be defined as part of the KeyListener interface
    }
	@Override

    public void keyPressed(KeyEvent e) {
        // react to key down events
        int newX = player.getX();
        int newY = player.getY();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: // W
            case KeyEvent.VK_UP:
                newY--;
                break;
            case KeyEvent.VK_S: // S
            case KeyEvent.VK_DOWN:
                newY++;
                break;
            case KeyEvent.VK_A: // A
            case KeyEvent.VK_LEFT:
                newX--;
                break;
            case KeyEvent.VK_D: // D
            case KeyEvent.VK_RIGHT:
                newX++;
                break;
        }

        // Check collision
        if (newY >= 0 && newY < mazeGrid.length &&
            newX >= 0 && newX < mazeGrid[0].length &&
            !mazeGrid[newY][newX]) {
            player.setPosition(newX, newY);
        }

        repaint(); // redraw after movement
    }

	@Override
    public void keyReleased(KeyEvent e) {
        // react to key up events
    }

}
