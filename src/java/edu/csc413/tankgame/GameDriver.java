package edu.csc413.tankgame;

import edu.csc413.tankgame.controller.KeyboardInterpreter;
import edu.csc413.tankgame.model.Entity;
import edu.csc413.tankgame.model.EntityActor;
import edu.csc413.tankgame.model.EntityPhysical;
import edu.csc413.tankgame.model.GameWorld;
import edu.csc413.tankgame.model.ai.AIModuleEntityActorBasic;
import edu.csc413.tankgame.model.ai.AIModuleEntityActorCheating;
import edu.csc413.tankgame.model.ai.AIModuleEntityActorSmart;
import edu.csc413.tankgame.model.ai.AIModuleEntityActorTestDummy;
import edu.csc413.tankgame.model.shell.Shell;
import edu.csc413.tankgame.model.tank.*;
import edu.csc413.tankgame.model.wall.Wall;
import edu.csc413.tankgame.view.MainView;
import edu.csc413.tankgame.view.RunGameView;
import edu.csc413.tankgame.view.StartMenuView;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import static edu.csc413.tankgame.Constants.*;
import static edu.csc413.tankgame.view.RunGameView.*;

public class GameDriver {
    private final MainView mainView;
    private final RunGameView runGameView;

    private GameWorld gameWorld;


    private final int gameDone = 200;
    private int gameDoneCounter = gameDone;

    public GameDriver() {

        // Initialize window stuff
        mainView = new MainView(this::startMenuActionPerformed); // Passing a function or an object?

        // Run main window
        runGameView = mainView.getRunGameView();

    }

    public void start() {

        // Set Game screen on the main window
        mainView.setScreen(MainView.Screen.START_GAME_SCREEN);
    }

    /**
     * Notes:
     * Is this an ActionListener? Is called when a button is pressed from the StartMenuView
     * <p>
     * TODO: FIGURE OUT WHAT THIS DOES
     *
     * @param actionEvent
     */
    private void startMenuActionPerformed(ActionEvent actionEvent) {
        /*
        Get actionEvent's ActionCommand and see if that command is either START_BUTTON_ACTION_COMMAND
        EXIT_BUTTON_ACTION_COMMAND and execute the appropriate command.

        actionEvent will probably be a button
        */
        switch (actionEvent.getActionCommand()) {
            case StartMenuView.START_BUTTON_ACTION_COMMAND -> runGame();
            case StartMenuView.EXIT_BUTTON_ACTION_COMMAND -> mainView.closeGame();
            default -> throw new RuntimeException("Unexpected action command: " + actionEvent.getActionCommand());
        }
    }

    // Run the game
    private void runGame() {

        // Change screen
        mainView.setScreen(MainView.Screen.RUN_GAME_SCREEN);

        // Make a runnable object that contains the game loop
        Runnable gameRunner = () -> {

            // Initialize game stuff
            setUpGame();
            while (updateGame()) {

                // Draw the stuff on the screen
                runGameView.repaint();
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException exception) {
                    throw new RuntimeException(exception);
                }
            }
            // When the game is over (escape the while loop).
            mainView.setScreen(MainView.Screen.END_MENU_SCREEN);
            resetGame();
        };

        // Resetting global defaults
        gameDoneCounter = gameDone;

        // Run game on a thread
        new Thread(gameRunner).start();
    }

    /**
     * setUpGame is called once at the beginning when the game is started. Entities that are present from the start
     * should be initialized here, with their corresponding sprites added to the RunGameView.
     */
    private void setUpGame() {
        gameWorld = new GameWorld(runGameView);
        initializeWWalls(gameWorld);
        System.out.println("RESET");


        /*
        The Player

        Tank:
            - 2000 Health tank foe balancing

        */
        KeyboardInterpreter keyboardInterpreter = new KeyboardInterpreter(
                KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);

        TankPlayer tankPlayer = new TankPlayer(keyboardInterpreter, ID_TANK_PLAYER, 0, 0, TANK_PLAYER_INITIAL_ANGLE, PLAYER_TANK_IMAGE_FILE, 2000);

        /*
        Hacking AI that is spin hacking and blinking randomly across the map and wants to kill you first and then
        everything else (literally)

        AI:
            - Target tankPlayer then Target Other Actors then Target all the other entities

        Hacks:
            - .99 Percent accuracy based on target
            - Spin hacking with position blinking
            - 1500 Health
            - 0 Cool Down
        */
        AIModuleEntityActorCheating aiModuleTankSpinHackBigGuy = new AIModuleEntityActorCheating(gameWorld);
        aiModuleTankSpinHackBigGuy.setAccuracy(.99);
        aiModuleTankSpinHackBigGuy.setTurnLefTurnSpeedSpinning(Math.PI / 60);
        aiModuleTankSpinHackBigGuy.setEntityTarget(tankPlayer);
        aiModuleTankSpinHackBigGuy.autoSelectNewEntityTarget(true);
        TankAIHacked tankAICheatingBigGuy = new TankAIHacked(aiModuleTankSpinHackBigGuy, ID_TANK_AI_4, TANK_X_UPPER_BOUND, TANK_Y_UPPER_BOUND, 0, IMAGE_TANK_AI, 1500);
        tankAICheatingBigGuy.enableNoCoolDown(true);
        tankAICheatingBigGuy.enableGodMode(false);

        /*
        Hacking AI that is spin hacking and blinking randomly across the map to kill the first hacker and then
        everything else (literally)

        AI:
            - Target tankAICheatingBigGuy then Target Other Actors then Target all the other entities

        Hacks:
            - .98 Percent accuracy
            - Faster spin hacking with position blinking
            - 0 Cool Down

        */
        AIModuleEntityActorCheating aiModuleTankSpinHackFastGuy = new AIModuleEntityActorCheating(gameWorld);
        aiModuleTankSpinHackFastGuy.setAccuracy(.98);
        aiModuleTankSpinHackFastGuy.setTurnLefTurnSpeedSpinning(Math.PI / 12);
        aiModuleTankSpinHackFastGuy.setEntityTarget(tankAICheatingBigGuy);
        aiModuleTankSpinHackFastGuy.autoSelectNewEntityTarget(true);
        TankAIHacked tankAICheatingFastGuy = new TankAIHacked(aiModuleTankSpinHackFastGuy, ID_TANK_AI_2, 0, 0, 0);
        tankAICheatingFastGuy.enableNoCoolDown(true);
        tankAICheatingFastGuy.enableGodMode(false);

        /*
        Basic AI that targets all entities other than shells

        AI:
            - Target Other Actors then Target all the other entities
            - Does not get too close to target

        */
        AIModuleEntityActorBasic aiModuleEntityActorBasic = new AIModuleEntityActorBasic(gameWorld);
//        aiModuleEntityActorBasic.setEntityTarget(tankPlayer);
        aiModuleEntityActorBasic.autoSelectNewEntityTarget(true);
        TankAI tankAIBasic = new TankAI(aiModuleEntityActorBasic, ID_TANK_AI_5, 900, 0, 0);

        /*
        Smarter AI that targets all entities and if you shot near this

        Tank
            - 2000 HP default

        AI:
            - Target Other Actors then Target all the other entities UNLESS you fire at or near this AI who will then
              reduce it's incoming damage by moving away and destroying incoming shells that might kill this tank and
              then target the tank who shot those shells.

        */
        AIModuleEntityActorSmart aiModuleEntityActorSmart = new AIModuleEntityActorSmart(gameWorld);
//        aiModuleEntityActorBasic.setEntityTarget(tankPlayer);
        aiModuleEntityActorSmart.autoSelectNewEntityTarget(true);
        TankAI tankAISmart = new TankAI(aiModuleEntityActorSmart, ID_TANK_AI_3, 400, 400, 0, AI_TANK_IMAGE_FILE, 2000);

        // Dummy Tank with Dummy AI ("He's just standing there... MENACINGLY")
        AIModuleEntityActorTestDummy aiModuleEntityActorTestDummy = new AIModuleEntityActorTestDummy(gameWorld);
        aiModuleEntityActorTestDummy.setEntityTarget(tankPlayer);
        TankAI tankAITestDummy = new TankAITestDummy(aiModuleEntityActorTestDummy, ID_TANK_AI_1, 500, 500, 0);

        gameWorld.addEntityToQueueForWorld(tankPlayer);
        gameWorld.addEntityToQueueForWorld(tankAITestDummy);
        gameWorld.addEntityToQueueForWorld(tankAICheatingFastGuy);
        gameWorld.addEntityToQueueForWorld(tankAICheatingBigGuy);
        gameWorld.addEntityToQueueForWorld(tankAIBasic);
        gameWorld.addEntityToQueueForWorld(tankAISmart);

    }

    /**
     * updateGame is repeatedly called in the gameplay loop. The code in this method should run a single frame of the
     * game. As long as it returns true, the game will continue running. If the game should stop for whatever reason
     * (e.g. the player tank being destroyed, escape being pressed), it should return false.
     * <p>
     * Time Complexity: n + n^2
     */
    private boolean updateGame() {

        for (Entity entity : gameWorld.getEntitiesFast()) {

            Entity entityCurrent = (Entity) entity;

            entityCurrent.doActionEntity(gameWorld);

            // FIXME: WILL NOT GUARANTEE AND DETERMINE THAT 2 OBJECTS HAVE COLLIDED BECAUSE THE SHELL COULD BE AHEAD IN THE COLLECTION OF ENTITIES
//            for (Entity entityOpposing : gameWorld.getEntitiesFast()) {
//                if (!entityCurrent.equals(entityOpposing)){
//
//                    entityCurrent.checkCollision(gameWorld, entityOpposing);
//                }
//            }

            runGameView.setSpriteLocationAndAngle(entity);
            runGameView.setSpriteImage(entity);

            if (entityCurrent instanceof EntityActor) {
                runGameView.drawInformationEntityPhysical((EntityPhysical) entityCurrent);
            }
        }


        /**
         * COLLISION CHECKING IS AFTER TO GUARANTEE THAT OBJECTS THAT COLLIDED ARE HANDLED
         *
         * TODO: IF THE BELOW CODE IS NOT COMMENTED OUT, THEN I DON'T HAVE TIME TO WRITE AN OPTIMAL COLLISION ALGORITHM
         */
        for (Entity entityHit : gameWorld.getEntitiesFast()) {
            for (Entity entityDoingTheHit : gameWorld.getEntitiesFast()) {
                if (!entityHit.equals(entityDoingTheHit)) {
                    boolean collision = entityHit.checkCollision(gameWorld, entityDoingTheHit);

                    if (collision) {
                        animationHandler(entityHit, entityDoingTheHit);
                    }
                }
            }
        }

        gameWorld.pushEntitiesFromQueueForWorldToWorld();
        gameWorld.popEntitiesFromQueueRemoveFromWorld();

        if (gameWorld.getEntity(ID_TANK_PLAYER) == null || gameWorld.getCountEntityActor() == 1) {
            gameDoneCounter -= 1;
            System.out.printf("Menu screen in %d\n", gameDoneCounter);
            if (gameDoneCounter <= 0) {
                return false;
            }
        }

        return true;
    }

    private void animationHandler(Entity entityHit, Entity entityDoingTheHit) {
        if (entityDoingTheHit instanceof Shell) {
            runGameView.addAnimation(SHELL_EXPLOSION_ANIMATION, SHELL_EXPLOSION_FRAME_DELAY, entityDoingTheHit.getX(), entityDoingTheHit.getY());

            if (entityHit instanceof Tank || entityHit instanceof Wall) {
                if (((EntityPhysical) entityHit).getHealth() <= 0) {
                    runGameView.addAnimation(BIG_EXPLOSION_ANIMATION, BIG_EXPLOSION_FRAME_DELAY, entityHit.getX(), entityHit.getY());
                }
            }
        }
    }

    private void initializeWWalls(GameWorld gameWorld) {
        List<WallInformation> wallInformationList = WallInformation.readWalls();
        for (WallInformation wallInformation : wallInformationList) {

            gameWorld.addEntityToQueueForWorld(new Wall(
                    ID_WALL_BASIC + gameWorld.getUniqueNumberForId(),
                    wallInformation.getX(),
                    wallInformation.getY(),
                    0,
                    wallInformation.getImageFile()
            ));
        }
    }

    /**
     * resetGame is called at the end of the game once the gameplay loop exits. This should clear any existing data from
     * the game so that if the game is restarted, there aren't any things leftover from the previous run.
     */
    private void resetGame() {
        // TODO: Implement.
        runGameView.reset();
    }


    public static void main(String[] args) {
        GameDriver gameDriver = new GameDriver();
        gameDriver.start();

    }
}
