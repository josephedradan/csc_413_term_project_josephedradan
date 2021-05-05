
/*
 * Created by Joseph Edradan
 *
 * Github: https://github.com/josephedradan
 *
 * Date created: 4/23/2021
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

import static edu.csc413.tankgame.Constants.IMAGE_TANK_AI;
import static edu.csc413.tankgame.Constants.IMAGE_TANK_PLAYER;

public class TankAi extends Tank {


    public TankAi(String typeId, double x, double y, double angle, String image) {
        super(typeId, x, y, angle, image);
    }

    public TankAi(String typeId, double x, double y, double angle) {
        this(typeId, x, y, angle, IMAGE_TANK_AI);
    }

    @Override
    public void act(GameWorld gameWorld) {

    }
}
