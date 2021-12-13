package org.academiadecodigo.loopeytunes;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import java.util.HashMap;
import java.util.Map;

public class Filler {
    private final Cursor cursor;
    private final Map<String, Rectangle> marked = new HashMap<>();
    private Color markedColor = Color.BLACK;
    private final Map<Integer, Color> intToColors = new HashMap<>();
    private final Map<Color, Integer> colorToInt = new HashMap<>();
    private int currentSelectedColor = 0;
    private final Rectangle currentColorDemo;
    private final Persistence persistence;

    public Filler(Cursor cursor) {
        persistence = new Persistence();
        this.cursor = cursor;
        attributeColors();

        currentColorDemo = new Rectangle(Field.getWidth(), Field.getHeight() + 30,
                2 * Field.cellSize, 2 * Field.cellSize);
        currentColorDemo.setColor(intToColors.get(currentSelectedColor));
        currentColorDemo.fill();
    }

    public void cleanEditor() {
        marked.forEach((k, v) -> marked.get(k).delete());
        marked.clear();
    }

    public void mark() {
        Rectangle rectangle = new Rectangle(cursor.getX(), cursor.getY(), Field.PADDING * 2, Field.PADDING * 2);
        rectangle.setColor(markedColor);
        rectangle.fill();
        String pos = rectangle.getX() + "|" + rectangle.getY();
        marked.put(pos, rectangle);

        cursor.resetCursor();

    }

    public void unMark() {
        String pos = cursor.getX() + "|" + cursor.getY();
        marked.get(pos).delete();
        marked.remove(pos);
    }

    public void save() {
        persistence.save(marked, colorToInt);
    }

    public void load() {
        marked.forEach((k, v) -> marked.get(k).delete());
        marked.clear();

        String stringToLoad = persistence.load();

        if (!stringToLoad.isEmpty()) {

            String[] positionsToMark = stringToLoad.split("\n");

            for (String s : positionsToMark) {

                String[] xycolor = s.split("\\|");
                int x = Integer.parseInt(xycolor[0]);
                int y = Integer.parseInt(xycolor[1]);
                String xy = x + "|" + y;
                int colorId = Integer.parseInt(xycolor[2]);

                marked.put(xy, new Rectangle(x, y, Field.PADDING * 2, Field.PADDING * 2));
                marked.get(xy).setColor(intToColors.get(colorId));
                marked.get(xy).fill();
            }
        }

        cursor.resetCursor();

    }

    public void drawOrErase() {
        String pos = cursor.getX() + "|" + cursor.getY();
        if (marked.containsKey(pos)) {
            unMark();
            return;
        }

        mark();
    }

    public void attributeColors() {
        intToColors.put(0, Color.BLACK);
        intToColors.put(1, Color.GREEN);
        intToColors.put(2, Color.ORANGE);
        intToColors.put(3, Color.MAGENTA);
        intToColors.put(4, Color.YELLOW);
        intToColors.put(5, Color.RED);

        colorToInt.put(Color.BLACK, 0);
        colorToInt.put(Color.GREEN, 1);
        colorToInt.put(Color.ORANGE, 2);
        colorToInt.put(Color.MAGENTA, 3);
        colorToInt.put(Color.YELLOW, 4);
        colorToInt.put(Color.RED, 5);

    }

    public void changeColor() {
        currentSelectedColor++;

        if (currentSelectedColor >= intToColors.size()) {
            currentSelectedColor = 0;
        }

        currentColorDemo.setColor(intToColors.get(currentSelectedColor));
        markedColor = intToColors.get(currentSelectedColor);
    }
}
