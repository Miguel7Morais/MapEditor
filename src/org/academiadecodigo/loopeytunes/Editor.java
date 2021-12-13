package org.academiadecodigo.loopeytunes;

public class Editor {

    public Editor() {
        Field field = new Field(80, 60);
        field.generateField();

        Cursor cursor = new Cursor();
        Filler filler = new Filler(cursor);
        new KeyboardManager(cursor, filler);
    }

}
