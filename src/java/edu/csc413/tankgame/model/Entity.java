package edu.csc413.tankgame.model;

import java.util.ArrayList;

/**
 * A general concept for an entity in the Tank Game. This includes everything that can doActionComplete or be interacted with, such
 * as tanks, shells, walls, power ups, etc.
 */
public abstract class Entity {

    /*
     * All objects are entities
     *
     * */
    // TODO: Implement. A lot of what's below is relevant to all Entity types, not just Tanks. Move it accordingly to
    //       Entity class.

    private String image;

    private final String id;
    protected double x;
    protected double y;
    protected double angleRelativeToWorld; // In radians

    public Entity(String id, double x, double y, double angleRelativeToWorld, String image) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.angleRelativeToWorld = angleRelativeToWorld;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngleRelativeToWorld() {
        return angleRelativeToWorld;
    }

    /**
     * Euclidean Distance between 2 entities.
     *
     * @param entity Entity given
     * @return distance
     */
    public double getDistanceFromEntity(Entity entity) {
        return getDistanceFromEntity(entity, 0, 0);
    }

    public double getDistanceFromEntity(Entity entity, double xOffset, double yOffset) {
        return getDistanceFromPosition(entity.getX(), entity.getY(), xOffset, yOffset);
    }

    public double getDistanceFromPosition(double xGiven, double yGiven) {
        return getDistanceFromPosition(xGiven, yGiven, 0, 0);
    }

    public double getDistanceFromPosition(double xGiven, double yGiven, double xOffset, double yOffset) {
        return Math.sqrt(Math.pow(xGiven - x + xOffset, 2) + Math.pow(yGiven - y + yOffset, 2));
    }


    /**
     * Get the Quadrant relative to this entity based on this entity's angle
     *
     * @return Quadrant
     */
    public short getQuadrantBasedOnAngleRad() {

        double xThisRelative = Math.cos(angleRelativeToWorld);
        double yThisRelative = Math.sin(angleRelativeToWorld);

        if (xThisRelative >= 0 && yThisRelative >= 0) {
            return 1;
        } else if (xThisRelative < 0 && yThisRelative >= 0) {
            return 2;
        } else if (xThisRelative < 0 && yThisRelative < 0) {
            return 3;
        } else if (xThisRelative >= 0 && yThisRelative < 0)
            return 4;
        return 0;
    }

    /**
     * Get the b value in b = y -mx
     * Notes:
     * This is not as helpful since both the slope and the b value are used.
     *
     * @return
     */
    public double getBValue() {
        double xThisRelative = Math.cos(angleRelativeToWorld);
        double yThisRelative = Math.sin(angleRelativeToWorld);

        double slope = yThisRelative / xThisRelative;
        double bThis = (y - (slope * x));
        return bThis;
    }

    /**
     * Get the Quadrant relative to this entity that the given entity is in.
     * The trick is to check if the difference in x or y is a negative or a positive.
     *
     * @param entity Entity given
     * @return Quadrant
     */
    public short getQuadrantEntityRelative(Entity entity) {
        return getQuadrantPositionRelative(entity.getX(), entity.getY());
    }

    public short getQuadrantPositionRelative(double xGiven, double yGiven) {
        double yDiff = yGiven - this.getY();
        double xDiff = xGiven - this.getX();

        if (xDiff >= 0 && yDiff >= 0) {
            return 1;
        } else if (xDiff < 0 && yDiff >= 0) {
            return 2;
        } else if (xDiff < 0 && yDiff < 0) {
            return 3;
        } else if (xDiff >= 0 && yDiff < 0)
            return 4;
        return 0;
    }

    /**
     * Check if this entity has a given entity in line of sight by checking if the b value for both calculated
     * y = mx + b values are the same.
     *
     * @param entity    Entity to check
     * @param tolerance Increments to this entity's angle by a factor of .01 radians
     * @return boolean
     */
    public boolean checkIfInLineOfSightSlowUsingLines(Entity entity, int tolerance) {

        // Use b = y - mx
        // Check if the b value is the same for both lines

        double angleRadStart = angleRelativeToWorld - ((tolerance / 2) * .01);

        for (int i = 0; i < tolerance; i++) {

            double angleRadSweep = angleRadStart + (i * .01);

            double xThisRelative = Math.cos(angleRadSweep);
            double yThisRelative = Math.sin(angleRadSweep);

            double slope = yThisRelative / xThisRelative;
            double bThis = (this.y - (slope * this.x));

            double bEntityRelativeToThis = (entity.getY() - (slope * entity.getX()));

            if (Math.round(bThis) == Math.round(bEntityRelativeToThis)) {
                ArrayList<Integer> integerArrayList = getQuadrantsEntityRelative(entity);
                for (int j = 0; j < 1; i++) {
                    if (integerArrayList.get(i) == this.getQuadrantBasedOnAngleRad()) {
                        return true;
                    }
                }
//                if (this.getQuadrantEntityRelative(entity) == this.getQuadrantBasedOnAngleRad()) {
//                    return true;
//                }
            }
        }
        return false;
    }

    /**
     * Check if this entity has a given entity in line of sight by checking if the b value for this entity in
     * y = mx + b is within a certain allowed range.
     *
     * @param entity
     * @param differenceAllowed
     * @return
     */
    public boolean checkIfInLineOfSightFastUsingLines(Entity entity, int differenceAllowed) {

        double xThisRelative = Math.cos(angleRelativeToWorld);
        double yThisRelative = Math.sin(angleRelativeToWorld);
        double slope = yThisRelative / xThisRelative;

        double b = (this.y - (slope * this.x));

        double yEntityRelativeToThis = slope * entity.getX() + b;

        double difference = Math.abs(yEntityRelativeToThis - entity.getY());

//        System.out.println(difference);

        if (difference <= differenceAllowed) {
            ArrayList<Integer> integerArrayList = getQuadrantsEntityRelative(entity);
//            System.out.println(integerArrayList);

            for (int i = 0; i < 1; i++) {
                if (integerArrayList.get(i) == this.getQuadrantBasedOnAngleRad()) {
//                    System.out.println(entity.getQuadrantBasedOnAngleRad());
                    return true;
                }
            }

//            if (this.getQuadrantsEntityRelative(entity).get(0) == this.getQuadrantBasedOnAngleRad()) {
//                return true;
//            }

//            if (this.getQuadrantEntityRelative(entity) == this.getQuadrantBasedOnAngleRad()) {
//                return true;
//            }

        }
        return false;
    }


    /**
     * Get Cosine similarity between this entity's line of sight and the given entity
     *
     * @param entity
     * @return
     */
    public double getCosineSimilarityBetweenLineOfSightAndEntity(Entity entity) {
        return getCosineSimilarityBetweenPointAngleAndEntity(this.getAngleRelativeToWorld(), this.getX(), this.getY(), entity);
    }

    /**
     * Get angle between this entity's line of sight and the Given entity.
     *
     * @param entity
     * @return
     */
    public double getAngleRadBetweenLineOfSightAndEntity(Entity entity) {
        return Math.acos(getCosineSimilarityBetweenLineOfSightAndEntity(entity));
    }

    /**
     * Check if given entity is in line of sight using the Cosine similarity of 2 vectors
     *
     * @param entity
     * @param accuracy
     * @return
     */
    public boolean checkIfInLineOfSightFastUsingVectors(Entity entity, double accuracy) {
        double cosineSimilarity = getCosineSimilarityBetweenLineOfSightAndEntity(entity);
//        System.out.println(cosineSimilarity);
        if (cosineSimilarity >= accuracy) {
            return true;
        }
        return false;
    }

    /**
     * Get the percentage of how close a the given entity is to the quadrant relative to this entity.
     * Notes:
     * Streams are too slow
     * <p>
     * Quadrants
     * Q3  Q4
     * <p>
     * Q2  Q1
     *
     * @param entity Entity given
     * @return ArrayList of percentages for each relative quadrant
     */
    public ArrayList<Double> getQuadrantEntityRelativeDistances(Entity entity) {

        // Literally any value.
        int someValue = 1;

        ArrayList<Double> temp = new ArrayList<>(4);

        double distanceQ1 = getDistanceFromEntity(entity, someValue * -1, someValue * -1);
        double distanceQ2 = getDistanceFromEntity(entity, someValue, someValue * -1);
        double distanceQ3 = getDistanceFromEntity(entity, someValue, someValue);
        double distanceQ4 = getDistanceFromEntity(entity, someValue * -1, someValue);

        temp.add(0, distanceQ1); // Quad 1
        temp.add(1, distanceQ2); // Quad 2
        temp.add(2, distanceQ3); // Quad 3
        temp.add(3, distanceQ4); // Quad 4

        double min = Math.min(distanceQ1, Math.min(distanceQ2, Math.min(distanceQ3, distanceQ4)));

        double distanceQ1RelativeToAll = (distanceQ1 - min);
        double distanceQ2RelativeToAll = (distanceQ2 - min);
        double distanceQ3RelativeToAll = (distanceQ3 - min);
        double distanceQ4RelativeToAll = (distanceQ4 - min);

        double denominator = distanceQ1RelativeToAll + distanceQ2RelativeToAll + distanceQ3RelativeToAll + distanceQ4RelativeToAll;

        temp.set(0, 1 - (distanceQ1RelativeToAll / denominator)); // Quad 1
        temp.set(1, 1 - (distanceQ2RelativeToAll / denominator)); // Quad 2
        temp.set(2, 1 - (distanceQ3RelativeToAll / denominator)); // Quad 3
        temp.set(3, 1 - (distanceQ4RelativeToAll / denominator)); // Quad 4

        return temp;
    }

    /**
     * Get the quadrants in the order of how close the given entity is to each quadrant. The quadrants are relative
     * to this entity.
     *
     * @param entity Entity given
     * @return Array of quadrants relative to this entity in the order of how close the given
     * entity is to each quadrant
     */
    public ArrayList<Integer> getQuadrantsEntityRelative(Entity entity) {
        ArrayList<Double> arrayListQuadrantEntityRelativeDistance = getQuadrantEntityRelativeDistances(entity);

        ArrayList<Integer> quadrants = new ArrayList<>();
        double temp = -1;
        int index = -1;
        int size = arrayListQuadrantEntityRelativeDistance.size();

        for (int j = 0; j < size; j++) {
            for (int i = 0; i < arrayListQuadrantEntityRelativeDistance.size(); i++) {
                if (arrayListQuadrantEntityRelativeDistance.get(i) > temp) {
                    index = i;
                    temp = arrayListQuadrantEntityRelativeDistance.get(i);
                }
            }
            quadrants.add(index + 1);
            arrayListQuadrantEntityRelativeDistance.set(index, (double) -1);
            temp = -1;
        }

        return quadrants;
    }



    /**
     * Get Cosine similarity between a point with an angle and the given entity
     * @param angle
     * @param x
     * @param y
     * @param entity
     * @return
     */
    public static double getCosineSimilarityBetweenPointAngleAndEntity(double angle, double x, double y, Entity entity) {
        double xThisRelative = Math.cos(angle);
        double yThisRelative = Math.sin(angle);

        double xEntityRelativeToThis = entity.getX() - x;
        double yEntityRelativeToThis = entity.getY() - y;

        // Dot product
        double numerator = (xThisRelative * xEntityRelativeToThis) + (yThisRelative * yEntityRelativeToThis);

        // Multiply Magnitudes
        double denominator = ((Math.sqrt(Math.pow(xThisRelative, 2) + Math.pow(yThisRelative, 2))) *
                (Math.sqrt(Math.pow(xEntityRelativeToThis, 2) + Math.pow(yEntityRelativeToThis, 2))
                ));

        return numerator / denominator;
    }


    public abstract double getWidth();
    public abstract double getHeight();


    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}

