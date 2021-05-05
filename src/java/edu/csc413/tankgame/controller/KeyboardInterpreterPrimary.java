
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

public class KeyboardInterpreterPrimary extends KeyboardInterpreter {

    private final int keyCodeMenu;
    private final int keyCodeStop;
    private final int keyCodeIDK;

    public KeyboardInterpreterPrimary( int keyCodeUp, int keyCodeDown, int keyCodeLeft,
                                      int keyCodeRight, int keyCodeAction, int keyCodeMenu, int keyCodeStop,
                                      int keyCodeIDK) {
        super(keyCodeUp, keyCodeDown, keyCodeLeft, keyCodeRight, keyCodeAction);

        this.keyCodeMenu = keyCodeMenu;
        this.keyCodeStop = keyCodeStop;
        this.keyCodeIDK = keyCodeIDK;
    }
}
