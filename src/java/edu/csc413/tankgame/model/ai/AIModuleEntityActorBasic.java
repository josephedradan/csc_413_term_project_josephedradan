
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
import edu.csc413.tankgame.model.shell.Shell;

public class AIModuleEntityActorBasic extends AIModuleEntityActor {

    // Default tolerance angle to entity
    protected double toleranceAngleRad = Math.toRadians(3);

    // Default tolerance distance to entity
    protected double toleranceDistance = 250;

    // Default distance smoothing value
    protected double distanceSmoothing = 4;

    // Default distance where stuff is in this object's zone
    protected double inMyZoneRadius = 200;


    public AIModuleEntityActorBasic(GameWorld gameWorld) {
        super(gameWorld);
    }

    protected void autoMoveTowards(EntityActor entityActor) {

        if (entityPhysicalTarget != null) {
            double distanceDifference = entityActor.getDistanceFromEntity(entityPhysicalTarget);
//        System.out.println(Math.abs(Math.abs(distanceDifference) - distanceDifference));

            // Distance difference tolerance to smooth movement
            if (Math.abs(Math.abs(distanceDifference) - toleranceDistance) < distanceSmoothing) {
                return;
            }

            // Movement
            if (distanceDifference > toleranceDistance) {
                boolMoveForward = true;
            } else if (distanceDifference < toleranceDistance) {
                boolMoveBackward = true;
            }
        }
    }

    /**
     * Aim towards an Entity using vector math
     *
     * @param entityActor
     * @param entity
     */
    protected void autoAimTowards(EntityActor entityActor, Entity entity) {
        if (entity != null) {
            // 180 degrees based
            double angleBetweenLineOfSightAndEntity = entityActor.getAngleRadBetweenLineOfSightAndEntity(entity);

            double angleRadLow = Entity.getCosineSimilarityBetweenPointAngleAndEntity(
                    entityActor.getAngleRelativeToWorld() - toleranceAngleRad,
                    entityActor.getX(),
                    entityActor.getY(),
                    entity);

            double angleRadHigh = Entity.getCosineSimilarityBetweenPointAngleAndEntity(
                    entityActor.getAngleRelativeToWorld() + toleranceAngleRad,
                    entityActor.getX(),
                    entityActor.getY(),
                    entity);

            if (angleBetweenLineOfSightAndEntity < toleranceAngleRad) {
                return;
            }

            if (angleRadHigh < angleRadLow) {
                boolTurnLeft = true;
            } else {
                boolTurnRight = true;
            }
        }
    }

    /**
     * Your implementation of pointing towards to player with angles
     *
     * @param entityActor
     */
    protected void autoAimTowardsV2(EntityActor entityActor, Entity entity) {
        if (entity != null) {
            double dx = entity.getX() - entityActor.getX();
            double dy = entity.getY() - entityActor.getY();

            double angleToEntity = Math.atan2(dy, dx);
            double angleDifference = entityActor.getAngleRelativeToWorld() - angleToEntity;

            angleDifference -= Math.floor((angleDifference / (2 * Math.PI)) + .5) * (2 * Math.PI);

            if (angleDifference < -toleranceAngleRad) {
                boolTurnRight = true;
            } else if (angleDifference > toleranceAngleRad) {
                boolTurnLeft = true;
            }
        }
    }


    protected void autoAimTowardsV2(EntityActor entityActor) {
        autoAimTowardsV2(entityActor, entityPhysicalTarget);
    }

    public void setToleranceAngleRad(double toleranceAngleRad) {
        this.toleranceAngleRad = toleranceAngleRad;
    }

    public void setToleranceDistance(double toleranceDistance) {
        this.toleranceDistance = toleranceDistance;
    }

    @Override
    protected void thinkMove(EntityActor entityActor) {
        autoAimTowardsV2(entityActor);
        autoMoveTowards(entityActor);
    }

    @Override
    protected void thinkAction(EntityActor entityActor) {
        activateActionPrimaryWhenInLineOfSight(entityActor);
    }
}
