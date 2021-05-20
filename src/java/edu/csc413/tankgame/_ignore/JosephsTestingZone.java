
/*
 * Created by Joseph Edradan
 *
 * Github: https://github.com/josephedradan
 *
 * Date created: 4/24/2021
 *
 * Purpose:
 *      To test code used in this project
 *
 * Details:
 *
 * Description:
 *
 * Notes:
 *      This is not part of the project, I made this so I can test code
 *
 * IMPORTANT NOTES:
 *
 * Explanation:
 *
 * Reference:
 *
 */

package edu.csc413.tankgame._ignore;

//public class JosephsTestingZone {
//
//    /*
//     * Testing the speed of specific collections because I don't know which is the fastest.
//     * The tests should mimicking game loop on getting entities from the world and looping over those entities.
//     * Notes:
//     *      Returning a Collection and looping over it is faster than returning an ArrayList(Collection)
//     *      and looping over it.
//     *
//     * Reference:
//     *      How to time Java program execution speed
//     *          https://stackoverflow.com/questions/2572868/how-to-time-java-program-execution-speed
//     *
//     */
//    private static void testSpeedOfCollections() {
//        GameWorld gameWorld = new GameWorld();
//
//        for (int i = 0; i < 1000; i++) {
//            gameWorld.addEntity(new TankAi(TANK_AI_1_ID, AI_TANK_1_INITIAL_X, AI_TANK_1_INITIAL_Y, AI_TANK_1_INITIAL_Y));
//        }
//
//        int length = 100000000;
//
//        // Returning an arraylist
//        final long startTime = System.currentTimeMillis();
//        for (int i = 0; i < length; i++) {
//            for (Entity entity : gameWorld.getEntities()) {
//                entity.getId();
//            }
//        }
//        final long endTime = System.currentTimeMillis();
//        System.out.println("ArrayList Total execution time: " + (endTime - startTime));
//
//        // Returning a collection
//        final long startTime2 = System.currentTimeMillis();
//        for (int i = 0; i < length; i++) {
//            for (Entity entity : gameWorld.getEntitiesFast()) {
//                entity.getId();
//            }
//        }
//        final long endTim2 = System.currentTimeMillis();
//        System.out.println("Collection Total execution time: " + (endTim2 - startTime2));
//
//        // Returning a list via stream
//        final long startTime3 = System.currentTimeMillis();
//        for (int i = 0; i < length; i++) {
//            for (Entity entity : gameWorld.getEntitiesSlow()) {
//                entity.getId();
//            }
//        }
//        final long endTim3 = System.currentTimeMillis();
//        System.out.println("Stream Total execution time: " + (endTim3 - startTime3));
//    }
//
//    public static void main(String[] args) {
//        testSpeedOfCollections();
//    }
//}
