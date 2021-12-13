package org.academiadecodigo.loopeytunes;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Cursor {
    private final Rectangle cursor;
    private boolean isPressed;

    public Cursor() {
        cursor = new Rectangle(Field.PADDING, Field.PADDING, 2 * Field.cellSize, 2 * Field.cellSize);
        cursor.setColor(Color.BLUE);
        cursor.fill();
    }

    public void moveUp() {
        if (cursor.getY() == Field.PADDING) {
            return;
        }
        cursor.translate(0, -Field.cellSize * 2);
    }

    public void moveDown() {
        if (cursor.getY() == Field.getHeight() - Field.PADDING) {
            return;
        }
        cursor.translate(0, Field.cellSize * 2);
    }

    public void moveLeft() {
        if (cursor.getX() == Field.PADDING) {
            return;
        }
        cursor.translate(-Field.cellSize * 2, 0);
    }

    public void moveRight() {
        if (cursor.getX() == Field.getWidth() - Field.PADDING) {
            return;
        }
        cursor.translate(Field.cellSize * 2, 0);
    }

    public int getX() {
        return cursor.getX();
    }

    public int getY() {
        return cursor.getY();
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }

    public void resetCursor() {
        cursor.delete();
        cursor.setColor(Color.BLUE);
        cursor.fill();
    }
}
