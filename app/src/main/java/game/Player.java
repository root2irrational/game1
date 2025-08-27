package game;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity {
    private Image sprite;

    public Player(int speed, int x, int y) {
        super(speed, x, y);
        try {
            Image raw = ImageIO.read(getClass().getResource("/images/zhitanTraded.gif"));
            sprite = raw.getScaledInstance(Maze.drawSize, Maze.drawSize, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    @Override
    public void move() {
        
    }
}
