
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

import edu.csc413.tankgame.model.Entity;
import edu.csc413.tankgame.model.EntityActor;
import edu.csc413.tankgame.model.GameWorld;

import static edu.csc413.tankgame.Constants.TANK_PLAYER_ID;

public class AIModuleEntityActorStandard extends AIModuleEntityActor {

    // Default tolerance angle to entity
    protected double toleranceAngleRad = Math.toRadians(3);

    // Default tolerance distance to entity
    protected double toleranceDistance = 100;

    public AIModuleEntityActorStandard(GameWorld gameWorld) {
        super(gameWorld);
    }

    protected void autoMoveTowards(EntityActor entityActor, Entity entity) {
        double distanceDifference = entityActor.getDistanceFromEntity(entity);
//        System.out.println(distanceDifference);

        if (distanceDifference > toleranceDistance) {
            boolMoveForward = true;
        } else if (distanceDifference < toleranceDistance) {
            boolMoveBackward = true;
        }
    }

    public void setToleranceAngleRad(double toleranceAngleRad) {
        this.toleranceAngleRad = toleranceAngleRad;
    }

    public void setToleranceDistance(double toleranceDistance) {
        this.toleranceDistance = toleranceDistance;
    }

    protected void autoAimTowards(EntityActor entityActor, Entity entity) {
        // 180 degrees based
        double angleBetweenLineOfSightAndEntity = entityActor.getAngleRadBetweenLineOfSightAndEntity(entity);

        double lowAngleRad = Entity.getCosineSimilarityBetweenPointAngleAndEntity(
                entityActor.getAngleRelativeToWorld() - toleranceAngleRad,
                entityActor.getX(),
                entityActor.getY(),
                entity);

        double highAngleRad = Entity.getCosineSimilarityBetweenPointAngleAndEntity(
                entityActor.getAngleRelativeToWorld() + toleranceAngleRad,
                entityActor.getX(),
                entityActor.getY(),
                entity);

        if (angleBetweenLineOfSightAndEntity < toleranceAngleRad) {
            return;
        }

        if (highAngleRad < lowAngleRad) {
            boolTurnLeft = true;
        } else {
            boolTurnRight = true;
        }
    }

    @Override
    protected void thinkMove(EntityActor entityActor) {
        Entity player = gameWorld.getEntity(TANK_PLAYER_ID);

        autoAimTowards(entityActor, player);
        autoMoveTowards(entityActor, player);
    }

    @Override
    protected void thinkAction(EntityActor entityActor) {
        boolActivatePrimaryAction = true;
    }
}
