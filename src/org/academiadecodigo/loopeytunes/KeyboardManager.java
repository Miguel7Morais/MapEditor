package org.academiadecodigo.loopeytunes;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class KeyboardManager implements KeyboardHandler {
    private final Cursor cursor;
    private final Filler filler;

    public KeyboardManager(Cursor cursor, Filler filler) {
        this.cursor = cursor;
        this.filler = filler;

        setKeys();
    }

    public void setKeys() {
        Keyboard kb = new Keyboard(this);

        addKeyPressed(KeyboardEvent.KEY_A, kb);
        addKeyPressed(KeyboardEvent.KEY_D, kb);
        addKeyPressed(KeyboardEvent.KEY_W, kb);
        addKeyPressed(KeyboardEvent.KEY_S, kb);
        addKeyPressed(KeyboardEvent.KEY_U, kb);
        addKeyPressed(KeyboardEvent.KEY_I, kb);
        addKeyPressed(KeyboardEvent.KEY_O, kb);
        addKeyPressed(KeyboardEvent.KEY_P, kb);
        addKeyPressed(KeyboardEvent.KEY_Q, kb);
        addKeyPressed(KeyboardEvent.KEY_SPACE, kb);
        addKeyReleased(KeyboardEvent.KEY_SPACE, kb);

    }

    public void addKeyPressed(int key, Keyboard kb) {
        KeyboardEvent keyboardEvent = new KeyboardEvent();
        keyboardEvent.setKey(key);
        keyboardEvent.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        kb.addEventListener(keyboardEvent);
    }

    public void addKeyReleased(int key, Keyboard kb) {
        KeyboardEvent keyboardEvent = new KeyboardEvent();
        keyboardEvent.setKey(key);
        keyboardEvent.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        kb.addEventListener(keyboardEvent);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_A:
                cursor.moveLeft();
                if (cursor.isPressed()) {
                    filler.drawOrErase();
                }
                break;

            case KeyboardEvent.KEY_D:
                cursor.moveRight();
                if (cursor.isPressed()) {
                    filler.drawOrErase();
                }
                break;

            case KeyboardEvent.KEY_S:
                cursor.moveDown();
                if (cursor.isPressed()) {
                    filler.drawOrErase();
                }
                break;

            case KeyboardEvent.KEY_W:
                cursor.moveUp();
                if (cursor.isPressed()) {
                    filler.drawOrErase();
                }
                break;

            case KeyboardEvent.KEY_SPACE:
                cursor.setPressed(true);
                filler.drawOrErase();
                break;

            case KeyboardEvent.KEY_U:
                filler.changeColor();
                break;

            case KeyboardEvent.KEY_I:
                filler.cleanEditor();
                break;

            case KeyboardEvent.KEY_O:
                filler.save();
                break;

            case KeyboardEvent.KEY_P:
                filler.load();
                break;

            case KeyboardEvent.KEY_Q:
                System.exit(0);
                break;

            default:
                System.out.println("Sorry, not implemented yet.");
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            cursor.setPressed(false);
        }

    }
}
