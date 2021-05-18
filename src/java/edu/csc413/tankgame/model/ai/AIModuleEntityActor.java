
/*
 * Created by Joseph Edradan
 *
 * Github: https://github.com/josephedradan
 *
 * Date created: 5/5/2021
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

package edu.csc413.tankgame.model.ai;

import edu.csc413.tankgame.model.Entity;
import edu.csc413.tankgame.model.EntityActor;
import edu.csc413.tankgame.model.GameWorld;

import static edu.csc413.tankgame.Constants.AI_ACCURACY;

public abstract class AIModuleEntityActor extends AIModule {

    // Default accuracy
    protected double accuracy = AI_ACCURACY; // firing accuracy

    public AIModuleEntityActor(GameWorld gameWorld) {
        super(gameWorld);
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

}
