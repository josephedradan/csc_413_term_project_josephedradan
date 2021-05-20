package edu.csc413.tankgame.controller;

import edu.csc413.tankgame.WallInformation;
import edu.csc413.tankgame.model.Entity;
import edu.csc413.tankgame.model.EntityPhysical;
import edu.csc413.tankgame.model.GameWorld;
import edu.csc413.tankgame.model.ai.AIModuleEntityActorBasic;
import edu.csc413.tankgame.model.ai.AIModuleEntityActorSmartCheating;
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


    private final int deathTimer = 200;
    private int deathTimerCounter = deathTimer;

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
        deathTimerCounter = deathTimer;

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

        KeyboardInterpreter keyboardInterpreter = new KeyboardInterpreter(
                KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);

        TankPlayer tankPlayer = new TankPlayer(keyboardInterpreter, ID_TANK_PLAYER, TANK_PLAYER_INITIAL_X, TANK_PLAYER_INITIAL_Y, TANK_PLAYER_INITIAL_ANGLE);

        // Hacker
        AIModuleEntityActorSmartCheating aiModuleTankSpinHack2 = new AIModuleEntityActorSmartCheating(gameWorld);
        aiModuleTankSpinHack2.setAccuracy(.99);
        aiModuleTankSpinHack2.setTurnLefTurnSpeedSpinning(Math.PI / 60);
        aiModuleTankSpinHack2.setEntityPhysicalTarget(tankPlayer);
        aiModuleTankSpinHack2.autoSelectNewEntityTarget(true);
        TankAIHacked tankAICheating2 = new TankAIHacked(aiModuleTankSpinHack2, ID_TANK_AI_4, TANK_X_UPPER_BOUND, TANK_Y_UPPER_BOUND, 0);
        tankAICheating2.enableNoCooldown(true);
        tankAICheating2.enableGodMode(false);

        // An ally until the target is dead...
        AIModuleEntityActorSmartCheating aiModuleTankSpinHack = new AIModuleEntityActorSmartCheating(gameWorld);
        aiModuleTankSpinHack.setAccuracy(.98);
        aiModuleTankSpinHack.setTurnLefTurnSpeedSpinning(Math.PI / 12);
        aiModuleTankSpinHack.setEntityPhysicalTarget(tankAICheating2);
        aiModuleTankSpinHack.autoSelectNewEntityTarget(true);
        TankAIHacked tankAICheating = new TankAIHacked(aiModuleTankSpinHack, ID_TANK_AI_2, 0, 0, 0);
        tankAICheating.enableNoCooldown(true);
        tankAICheating.enableGodMode(false);

        // Basic AI that targets all entities other than shells
        AIModuleEntityActorBasic aiModuleEntityActorBasic = new AIModuleEntityActorBasic(gameWorld);
//        aiModuleEntityActorBasic.setEntityPhysicalTarget(tankPlayer);
        aiModuleEntityActorBasic.autoSelectNewEntityTarget(true);
        TankAI tankAIAutoTarget = new TankAI(aiModuleEntityActorBasic, ID_TANK_AI_3, 400, 400, 0);

        // Dummy Tank with Dummy AI
        AIModuleEntityActorTestDummy aiModuleEntityActorTestDummy = new AIModuleEntityActorTestDummy(gameWorld);
        aiModuleEntityActorTestDummy.setEntityPhysicalTarget(tankPlayer);
        TankAI tankAITestDummy = new TankAITestDummy(aiModuleEntityActorTestDummy, ID_TANK_AI_1, 500, 500, 0);

        // Basic AI that targets all entities other than shells
        AIModuleEntityActorSmart aiModuleEntityActorSmart = new AIModuleEntityActorSmart(gameWorld);
//        aiModuleEntityActorBasic.setEntityPhysicalTarget(tankPlayer);
        aiModuleEntityActorSmart.autoSelectNewEntityTarget(true);
        TankAI tankAIAutoTarget2 = new TankAI(aiModuleEntityActorSmart, ID_TANK_AI_3, 400, 400, 0);


        gameWorld.addEntityToQueueForWorld(tankPlayer);
        gameWorld.addEntityToQueueForWorld(tankAITestDummy);
        gameWorld.addEntityToQueueForWorld(tankAICheating);
        gameWorld.addEntityToQueueForWorld(tankAICheating2);
//        gameWorld.addEntityToQueueForWorld(tankAIAutoTarget);
        gameWorld.addEntityToQueueForWorld(tankAIAutoTarget2);


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
        }


        /**
         * COLLISION CHECKING IS AFTER TO GUARANTEE THAT 2 OBJECTS HAVE COLLIDED AT THE COST OF SPEED
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

        if (gameWorld.getEntity(ID_TANK_PLAYER) == null) {
            deathTimerCounter -= 1;
            System.out.printf("Menu screen in %d\n", deathTimerCounter);
            if (deathTimerCounter <= 0) {
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
                    ID_WALL_STANDARD + gameWorld.getUniqueNumberForId(),
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
