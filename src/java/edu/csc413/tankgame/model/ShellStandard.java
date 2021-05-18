
/*
 * Created by Joseph Edradan
 *
 * Github: https://github.com/josephedradan
 *
 * Date created: 5/4/2021
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

package edu.csc413.tankgame.model;

import static edu.csc413.tankgame.Constants.IMAGE_SHELL_STANDARD;

public class ShellStandard extends Shell {
    public ShellStandard(String id, double x, double y, double angle, String image, Entity entityParent) {
        super(id, x, y, angle, image, entityParent);
    }

    public ShellStandard(String id, double x, double y, double angle, Entity entityParent) {
        this(id, x, y, angle, IMAGE_SHELL_STANDARD, entityParent);
    }
}
