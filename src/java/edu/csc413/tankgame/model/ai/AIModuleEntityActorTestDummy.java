
/*
 * Created by Joseph Edradan
 *
 * Github: https://github.com/josephedradan
 *
 * Date created: 5/17/2021
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

import edu.csc413.tankgame.model.EntityActor;
import edu.csc413.tankgame.model.GameWorld;

/**
 * A Literal testing AI
 */
public class AIModuleEntityActorTestDummy extends AIModuleEntityActor {
    public AIModuleEntityActorTestDummy(GameWorld gameWorld) {
        super(gameWorld);
    }

    @Override
    protected void thinkMove(EntityActor entityActor) {
//        Entity player = gameWorld.getEntity(TANK_PLAYER_ID);
//
//        double tolerance = Math.toRadians(3);
//
//        double angleBetweenLineOfSightAndEntity = entityActor.getAngleRadBetweenLineOfSightAndEntity(player); // 180 degrees based
//
//        double lowAngleRad = Entity.getCosineSimilarityBetweenPointAngleAndEntity(entityActor.getAngleRelativeToWorld() - tolerance, entityActor.getX(), entityActor.getY(), player);
//        double highAngleRad = Entity.getCosineSimilarityBetweenPointAngleAndEntity(entityActor.getAngleRelativeToWorld() + tolerance, entityActor.getX(), entityActor.getY(), player);
//
//        if (angleBetweenLineOfSightAndEntity < tolerance) {
//            return;
//        }
//
//        if (highAngleRad < lowAngleRad) {
//            turnLefTurnSpeed = TANK_TURN_SPEED;
//        } else {
//            turnRightTurnSpeed = TANK_TURN_SPEED;
//        }
    }

    @Override
    protected void thinkAction(EntityActor entityActor) {

    }
}
