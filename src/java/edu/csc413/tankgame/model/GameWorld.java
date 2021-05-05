package edu.csc413.tankgame.model;

import edu.csc413.tankgame.view.RunGameView;

import java.util.*;
import java.util.stream.Collectors;

/**
 * GameWorld holds all of the model objects present in the game. GameWorld tracks all moving entities like tanks and
 * shells, and provides access to this information for any code that needs it (such as GameDriver or entity classes).
 */
public class GameWorld {
    // TODO: Implement. There's a lot of information the GameState will need to store to provide contextual information.
    //       Add whatever instance variables, constructors, and methods are needed.


    // Using hash map because there is 1 thread for the game, I think it should be fine...
    private final HashMap<String, Entity> entityHashMap;
    private final RunGameView runGameView;

    private final LinkedList<Entity> entityLinkedList;

    public GameWorld(RunGameView runGameView) {
        this.runGameView = runGameView;
        entityHashMap = new HashMap<>();
        entityLinkedList = new LinkedList<>();
    }

    /**
     * Returns a list of all entities in the game.
     * Constantly make a list using a stream here would be slow...
     */
    public List<Entity> getEntities() {
        return new ArrayList<>(entityHashMap.values());
    }

    public Collection<Entity> getEntitiesFast() {
        return entityHashMap.values();
    }

    public Collection<Entity> getEntitiesSlow() {
        // Slower
        // return entityHashMap.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());

        // Faster
        return entityHashMap.values().stream().collect(Collectors.toList());
    }

    /**
     * Adds a new entity to the game.
     */
    public void addEntity(Entity entity) {
        entityHashMap.put(entity.getTypeId(), entity);
        runGameView.addSprite(entity.getTypeId(), entity.getImage(), entity.getX(), entity.getY(), entity.getAngle());

    }

    public void addEntityToQueue(Entity entity) {
        System.out.println(entity.getTypeId());
        entityLinkedList.add(entity);
    }

    public void pushEntitiesFromQueueToWorld() {
        while (!entityLinkedList.isEmpty()) {
            addEntity(entityLinkedList.pop());
        }
    }

    /**
     * Returns the Entity with the specified ID.
     */
    public Entity getEntity(String id) {
        return entityHashMap.get(id);
    }

    /**
     * Removes the entity with the specified ID from the game.
     */
    public void removeEntity(String id) {
        entityHashMap.remove(id);
    }
}
