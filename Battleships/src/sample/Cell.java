package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {

    public int x, y;
    public Ship ship = null;
    public boolean wasShot;
    private Board board;

    public Cell(int x, int y, Board board) {
        super(30, 30);
        this.x = x;
        this.y = y;
        this.board = board;
        setFill(Color.GRAY);
        setStroke(Color.DARKGREY);
    }

    public boolean shoot() {
        wasShot = true;
        setFill(Color.DARKBLUE);

        if (ship != null) {
            ship.hit();
            setFill(Color.RED);
            if (!ship.isAlive()) {
                board.ships--;
            }
            return true;
        }
        return false;
    }
}
