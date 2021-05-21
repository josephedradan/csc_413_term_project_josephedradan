
/*
 * Created by Joseph Edradan
 *
 * Github: https://github.com/josephedradan
 *
 * Date created: 5/18/2021
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

package edu.csc413.tankgame._ignore;

/**
 * IF THE WORLD HANDLED THE OBJECTS, THEN I COULD DO FAST COLLISION DETECTION
 */
//public class CollisionDetector {
//
//    private GameWorld gameWorld;
//
//
//    public CollisionDetector(GameWorld gameWorld) {
//        this.gameWorld = gameWorld;
//    }
//
//    public void run() {
//        ArrayList<Entity> entityArrayList = gameWorld.getEntitiesFast().stream().sorted(Comparator.comparingDouble(Entity::getXCorrected)).collect(Collectors.toCollection(ArrayList::new));
//
//        ArrayList<ArrayList<Entity>> entityArrayListActive = new ArrayList<>();
//
//
//        for (int i = 0; i < (entityArrayList.size() - 1); i++) {
//            Entity entity1 = entityArrayList.get(i);
//            Entity entity2 = entityArrayList.get(i + 1);
//
//            if ((entity1.getXCorrected() + entity1.getWidth()) > entity2.getXCorrected()) {
//
////                ArrayList<Entity> entityArrayListInner = new ArrayList<>();
////                entityArrayListInner.add(entity1);
////                entityArrayListInner.add(entity2);
//
//                if ((entity1.getYCorrected() + entity1.getHeight()) > entity2.getHeight()) {
//
//                }
//            }
//        }
//        HashMap<Entity, ArrayList<Entity>> entityArrayListCollision = new HashMap<>();
//
//        for (int i = 0; i < (entityArrayListActive.size() - 1); i++) {
//
//        }
//    }
//}