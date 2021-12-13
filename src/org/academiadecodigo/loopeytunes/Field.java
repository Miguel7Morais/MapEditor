package org.academiadecodigo.loopeytunes;

import org.academiadecodigo.simplegraphics.graphics.Line;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class Field {
    public final static int PADDING = 10;
    public static Rectangle field;
    public final static int cellSize = 10;
    private static int cols = 80;
    private static int rows = 60;

    public Field(int cols, int rows) {
        Field.cols = cols;
        Field.rows = rows;
    }

    public void generateField() {
        field = new Rectangle(getX(), getY(), cols * cellSize, rows * cellSize);
        field.draw();

        makeGridCols();
        makeGridRows();

        Text instructions1 = new Text(20, 620, "SPACE: Draw/erase dot     U: Change color     I: Erases all     O: Saves current state     P: Loads previous state     Q: Quit");
        instructions1.draw();
        Text instructions2 = new Text(20, 640, "(Hint: Keep SPACE pressed for continuous draw/erase)");
        instructions2.draw();

    }

    private void makeGridRows() {
        int y = PADDING;
        Line gridRows;
        while (y <= getHeight()) {
            gridRows = new Line(PADDING, y, getWidth() + PADDING, y);
            gridRows.draw();
            y += (PADDING * 2);
        }
    }

    private void makeGridCols() {
        int x = PADDING;
        Line gridCols;
        while (x <= getWidth()) {
            gridCols = new Line(x, PADDING, x, getHeight() + PADDING);
            gridCols.draw();
            x += (PADDING * 2);
        }
    }

    public int getX() {
        return PADDING;
    }

    public int getY() {
        return PADDING;
    }

    public static int getWidth() {
        return cols * cellSize;
    }

    public static int getHeight() {
        return rows * cellSize;
    }
}
