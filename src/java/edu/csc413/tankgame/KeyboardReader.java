/*
 * Created by Joseph Edradan
 *
 * Github: https://github.com/josephedradan
 *
 * Date created: 4/24/2021
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
 *      Class KeyEvent
 *          Notes:
 *          Reference:
 *              https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html
 *
 *      Get all keys from KeyEvent to map
 *          Notes:
 *              Getting KeyEvent field, used as reference
 *          Reference:
 *              https://stackoverflow.com/questions/14303157/get-all-keys-from-keyevent-to-map
 *
 *      Obtaining Field Types
 *          Notes:
 *              Getting a field's type
 *          Reference:
 *              https://docs.oracle.com/javase/tutorial/reflect/member/fieldTypes.html
 *
 *      How to find out if a field is instanceof a type via reflection?
 *          Notes:
 *              isAssignableFrom as an alternative to equals()
 *          Reference:
 *              https://stackoverflow.com/questions/2262322/how-to-find-out-if-a-field-is-instanceof-a-type-via-reflection
 *
 *      Reflection generic get field value, Field get() method in Java with Examples
 *          Notes:
 *              Understanding .get()
 *          Reference:
 *              https://stackoverflow.com/questions/13400075/reflection-generic-get-field-value
 *              https://www.geeksforgeeks.org/field-get-method-in-java-with-examples/
 *
 *      Java reflection - impact of setAccessible(true)
 *          Notes:
 *              Purpose of setAccessible()
 *          Reference:
 *              https://stackoverflow.com/questions/10638826/java-reflection-impact-of-setaccessibletrue
 *
 *
 */

package edu.csc413.tankgame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

/**
 * The KeyboardReader is a singleton class that provides information about the state of the keyboard. You can ask the
 * instance if a key is currently being pressed by calling isKeyPressed
 */
public class KeyboardReader implements KeyListener {

    // Early Initialization (Lazy initialization shouldn't be necessary here)
    private static final KeyboardReader instance = new KeyboardReader();

    public static KeyboardReader instance() {
        return instance;
    }

    // Only 1 thread has access to KeyboardReader which is the game
    private final HashMap<Integer, Boolean> hashMapKeyCode;

    private KeyboardReader() {
        // We define this private constructor to enforce the Singleton pattern.

        // Set default capacity of hash map to 199 which is the amount of (static int) in Class KeyEvent
        hashMapKeyCode = new HashMap<>(199);

        try {
            InitializeKeyCodes();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

//        for (Integer i : hashMapKeyCode.keySet()) {
//            System.out.println(i);
//        }
    }

    /**
     * Use reflection to get all the KeyEvent fields that are (static int) in Class KeyEvent and put them into
     * the hashMapKeyCode
     */
    private void InitializeKeyCodes() throws IllegalAccessException {
        Field[] fields = KeyEvent.class.getDeclaredFields();
        for (Field field : fields) {

            // Check if static and int
            if ((Modifier.isStatic(field.getModifiers())) && (field.getType().equals(Integer.TYPE))) {
                field.setAccessible(true); // Prevent IllegalAccessException

                // Get the value (key code) of the field
                int keyCode = (int) field.get(field.getType());
//                System.out.println(field.getName());
//                System.out.println(field.getClass());
//                System.out.println(keyCode);

                hashMapKeyCode.put(keyCode, false);
            }
        }
    }

    public boolean isKeyPressed(int keyCode) {
        return hashMapKeyCode.get(keyCode);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // Ignored.
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        handleKeyEvent(keyEvent.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        handleKeyEvent(keyEvent.getKeyCode(), false);
    }

    private void handleKeyEvent(int keyCode, boolean pressed) {
//        System.out.printf("%s: %s%n", keyCode, pressed);

        // Catch and add key codes that don't exist
        if (!hashMapKeyCode.containsKey(keyCode)) {
            System.out.printf("Key Code %s didn't exist until now!\n", keyCode);
            hashMapKeyCode.put(keyCode, pressed);
        } else {
            hashMapKeyCode.replace(keyCode, pressed);
        }
    }
}
