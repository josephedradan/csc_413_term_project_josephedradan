package edu.csc413.tankgame.model;

import edu.csc413.tankgame.view.RunGameView;

import java.util.*;
import java.util.stream.Collectors;

/**
 * GameWorld holds all of the model objects present in the game. GameWorld tracks all moving entities like tanks and
 * shells, and provides access to this information for any code that needs it (such as GameDriver or entity classes).
 */
public class GameWorld {

    private static long uniqueNumberForIDCounter = 0;

    // TODO: Implement. There's a lot of information the GameState will need to store to provide contextual information.
    //       Add whatever instance variables, constructors, and methods are needed.


    // Using hash map because there is 1 thread for the game, I think it should be fine...
    private final HashMap<String, Entity> entityHashMap;
    private final RunGameView runGameView;

    private final LinkedList<Entity> entityLinkedListQueueForWorld;
    private final LinkedList<Entity> entityLinkedListQueueRemoveFromWorld;

    private int countEntityActor = 0;

    public GameWorld(RunGameView runGameView) {
        this.runGameView = runGameView; // TODO Is this even allowed...

        entityHashMap = new HashMap<>();
        entityLinkedListQueueForWorld = new LinkedList<>();
        entityLinkedListQueueRemoveFromWorld = new LinkedList<>();
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
     * "Too powerful to be left out public..."
     */
    private void addEntity(Entity entity) {
        entityHashMap.put(entity.getId(), entity);
        runGameView.addSprite(entity.getId(), entity.getImage(), entity.getX(), entity.getY(),
                entity.getAngleRelativeToWorld());

        if (entity instanceof EntityActor) {
            runGameView.addInformationEntityPhysical(entity.getId(), entity.getX(), entity.getY(),
                    ((EntityPhysical) entity).getHealth(), entity.getWidth(), entity.getHeight());

            countEntityActor++;
        }

    }

    public void addEntityToQueueForWorld(Entity entity) {
//        System.out.println(entity.getId());
        entityLinkedListQueueForWorld.add(entity);
    }

    public void pushEntitiesFromQueueForWorldToWorld() {
        while (!entityLinkedListQueueForWorld.isEmpty()) {
            addEntity(entityLinkedListQueueForWorld.pop());
        }
    }

    public void addEntityToQueueRemoveFromWorld(Entity entity) {
        entityLinkedListQueueRemoveFromWorld.add(entity);
    }

    public void popEntitiesFromQueueRemoveFromWorld() {
        while (!entityLinkedListQueueRemoveFromWorld.isEmpty()) {
            removeEntity(entityLinkedListQueueRemoveFromWorld.pop());
        }
    }

    /**
     * Returns the Entity with the specified id.
     */
    public Entity getEntity(String id) {
        return entityHashMap.get(id);
    }

    /**
     * Removes the entity with the specified id from the game.
     * "Too powerful to be left out public..."
     */
    private void removeEntity(Entity entity) {
        runGameView.removeSprite(entity.getId());
        if (entity instanceof EntityActor) {
            runGameView.removeInformationEntityPhysical(entity.getId());

            countEntityActor--;
        }
        entityHashMap.remove(entity.getId());
    }

    public long getUniqueNumberForId() {
        long number = uniqueNumberForIDCounter;
        uniqueNumberForIDCounter++;
        return number;
    }

    public int getCountEntityActor() {
        return countEntityActor;
    }
}
