package game;

import java.awt.Point;

public class Entity {
    protected int x, y;
    private int speed;

    public Entity(int speed, int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void move(){

    }
}
