package sample;

public class Ship {

    public int size;
    public boolean vertical = true;
    private int health;

    public Ship(int size, boolean vertical) {
        this.size = size;
        this.vertical = vertical;
        health = size;
    }

    public void hit() {
        health--;
    }

    public boolean isAlive() {
        return health > 0;
    }
}