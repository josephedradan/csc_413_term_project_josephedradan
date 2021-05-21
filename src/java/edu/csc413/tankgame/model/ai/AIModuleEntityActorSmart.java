
/*
 * Created by Joseph Edradan
 *
 * Github: https://github.com/josephedradan
 *
 * Date created: 5/19/2021
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

public class AIModuleEntityActorSmart extends AIModuleEntityActorBasic {


    public AIModuleEntityActorSmart(GameWorld gameWorld) {
        super(gameWorld);
    }

    /**
     * Target Shells that come towards entityActor and fire at the entityPhysicalTarget that cause those shells
     * if possible
     *
     * @param entityActor
     */
    protected void autoAimTowardsSmartAndTargetAggressor(EntityActor entityActor) {

        // Check for incoming shells amd switch target to that object shooting those shells
        for (Entity entity : gameWorld.getEntitiesFast()) {
            if (entity instanceof Shell) {
                Shell shell = (Shell) entity;

                // Get Distance from shells
                if (entityActor.getDistanceFromEntity(shell) < inMyZoneRadius) {

                    if (!(shell.getEntityParent().equals(entityActor))) {

                        // Target Shells
                        autoAimTowardsV2(entityActor, entity);

                        // Change Target to the Aggressor
                        entityPhysicalTarget = shell.getEntityParent();
//                        System.out.println(entityPhysicalTarget);
                    }
                }
            }
        }

        autoAimTowardsV2(entityActor);

    }

    @Override
    protected void thinkMove(EntityActor entityActor) {
        autoAimTowardsSmartAndTargetAggressor(entityActor);
        autoMoveTowards(entityActor);
    }

//    @Override
//    protected void thinkAction(EntityActor entityActor) {
//        activateActionPrimaryWhenInLineOfSight(entityActor);
//    }
}
