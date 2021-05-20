package edu.csc413.tankgame;

public class Constants {
    public static final double TANK_WIDTH = 55.0;
    public static final double TANK_HEIGHT = 47.0;
    public static final double TANK_MOVEMENT_SPEED = 2.0;
    public static final double TANK_TURN_SPEED = Math.toRadians(3.0);

    public static final double SHELL_WIDTH = 12.0;
    public static final double SHELL_HEIGHT = 8.0;
    public static final double SHELL_MOVEMENT_SPEED = 4.0;

    public static final double WALL_WIDTH = 32.0;
    public static final double WALL_HEIGHT = 32.0;

    public static final String ID_TANK_PLAYER = "tank-player";
    public static final String ID_TANK_AI_1 = "tank-ai-1";
    public static final String ID_TANK_AI_2 = "tank-ai-2";
    public static final String ID_TANK_AI_3 = "tank-ai-3";
    public static final String ID_TANK_AI_4 = "tank-ai-4";
    public static final String ID_SHELL_STANDARD = "shell-standard-shell";
    public static final String ID_WALL_STANDARD = "wall-standard-shell";

    public static final double SHELL_DAMAGE_BASIC = 100;
    public static final double SHELL_HEALTH = 10;
    public static final double TANK_HEALTH_PLAYER = 250.0;
    public static final double TANK_HEALTH_AI = 500.0;
    public static final double TANK_HEALTH_AI_BOSS = 3000.0;
    public static final double WALL_HEALTH = 1000.0;

    public static final double TANK_PLAYER_INITIAL_X = 250.0;
    public static final double TANK_PLAYER_INITIAL_Y = 200.0;
    public static final double TANK_PLAYER_INITIAL_ANGLE = Math.toRadians(0.0);

    public static final double TANK_AI_1_INITIAL_X = 700.0;
    public static final double TANK_AI_1_INITIAL_Y = 500.0;
    public static final double TANK_AI_1_INITIAL_ANGLE = Math.toRadians(180.0);

    public static final double TANK_AI_2_INITIAL_X = 700.0;
    public static final double TANK_AI_2_INITIAL_Y = 200.0;
    public static final double TANK_AI_2_INITIAL_ANGLE = Math.toRadians(180.0);

    public static final double TANK_X_LOWER_BOUND = 30.0;
    public static final double TANK_X_UPPER_BOUND = 924.0;
    public static final double TANK_Y_LOWER_BOUND = 30.0;
    public static final double TANK_Y_UPPER_BOUND = 648.0;

    public static final double SHELL_X_LOWER_BOUND = -10.0;
    public static final double SHELL_X_UPPER_BOUND = 1024.0;
    public static final double SHELL_Y_LOWER_BOUND = -10.0;
    public static final double SHELL_Y_UPPER_BOUND = 768.0;

    public static final String IMAGE_TANK_PLAYER = "tank-player.png";
    public static final String IMAGE_TANK_AI = "tank-ai.png";
    public static final String IMAGE_SHELL_STANDARD = "shell.png";

    public static final int COOL_DOWN_PRIMARY_ACTION = 25;
    public static final int COOL_DOWN_SECONDARY_ACTION = 25;
    public static final int COOL_DOWN_TERTIARY_ACTION = 25;

    public static final double AI_ACCURACY = .80; // 80 Percent accuracy

}
