
/*
 * Created by Joseph Edradan
 *
 * Github: https://github.com/josephedradan
 *
 * Date created: 4/24/2021
 *
 * Purpose:
 *
 * Details:
 *
 * Description:
 *
 * Notes:
 *
 * IMPORTANT NOTES:
 *
 * Explanation:
 *
 * Reference:
 *
 */

package edu.csc413.tankgame.controller;

import edu.csc413.tankgame.KeyboardReader;

public class KeyboardInterpreter {

    private final KeyboardReader keyboardReader;

    private final int keyCodeUp;
    private final int keyCodeDown;
    private final int keyCodeLeft;
    private final int keyCodeRight;
    private final int keyCodeAction;

    public KeyboardInterpreter(int keyCodeUp, int keyCodeDown, int keyCodeLeft, int keyCodeRight, int keyCodeAction) {

        this.keyboardReader = KeyboardReader.instance();

        this.keyCodeUp = keyCodeUp;
        this.keyCodeDown = keyCodeDown;
        this.keyCodeLeft = keyCodeLeft;
        this.keyCodeRight = keyCodeRight;
        this.keyCodeAction = keyCodeAction;
    }

    public boolean upPressed() {
        return keyboardReader.isKeyPressed(keyCodeUp);
    }

    public boolean downPressed() {
        return keyboardReader.isKeyPressed(keyCodeDown);
    }

    public boolean leftPressed() {
        return keyboardReader.isKeyPressed(keyCodeLeft);
    }

    public boolean rightPressed() {
        return keyboardReader.isKeyPressed(keyCodeRight);
    }

    public boolean actionPressed() {
        return keyboardReader.isKeyPressed(keyCodeAction);
    }

}
