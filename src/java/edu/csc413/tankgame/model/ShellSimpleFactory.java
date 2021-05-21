
/*
 * Created by Joseph Edradan
 *
 * Github: https://github.com/josephedradan
 *
 * Date created: 5/20/2021
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

package edu.csc413.tankgame.model;

import edu.csc413.tankgame.model.shell.Shell;
import edu.csc413.tankgame.model.shell.ShellBasic;

import static edu.csc413.tankgame.Constants.ID_SHELL_BASIC;

/**
 * Factory that makes objects
 * <p>
 * Reference:
 * <p>
 * Understanding newinstance
 * https://stackoverflow.com/questions/15941957/passing-class-type-as-parameter-and-creating-instance-of-it
 * <p>
 * Understanding newinstance
 * https://stackoverflow.com/questions/234600/can-i-use-class-newinstance-with-constructor-arguments
 * <p>
 * *Example newinstance
 * https://www.javainterviewpoint.com/java-constructor-newinstance-method-example/
 */
public class ShellSimpleFactory {

    private static final ShellSimpleFactory instance = new ShellSimpleFactory();

//    private final HashMap<String, Class> stringClassHashMap;

    public static ShellSimpleFactory instance() {
        return instance;

    }

    private ShellSimpleFactory() {
//        stringClassHashMap = new HashMap<>();
    }


//    public void registerShell(String classNameID, Class classGiven) {
////        stringClassHashMap.put(classNameID, classGiven);
//    }

//    /**
//     * Support for creating multiple different types of shells that is dynamic
//     * Too many throws to add...
//     *
//     * @param classGiven
//     * @param id
//     * @param x
//     * @param y
//     * @param angle
//     * @param entityParent
//     * @return
//     * @throws NoSuchMethodException
//     * @throws IllegalAccessException
//     * @throws InvocationTargetException
//     * @throws InstantiationException
//     */
//    public Shell getShell(Class<Shell> classGiven, String id, double x, double y, double angle, Entity entityParent) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        return classGiven.getConstructor(String.class, double.class, double.class, double.class, Entity.class).newInstance(
//                id, x, y, angle, entityParent
//        );
//
////        Class c = stringClassHashMap.get(classNameID);
////        return (Shell) stringClassHashMap.get(classNameID).getDeclaredConstructor(Shell.class).newInstance(
////            ID_SHELL + number,
////
////        );
//    }

    /**
     * Support for creating multiple different types of shells that is not dynamic RIP
     *
     * @param IdShellType
     * @param id
     * @param x
     * @param y
     * @param angle
     * @param entityParent
     * @return
     */
    public Shell getShell(String IdShellType, String id, double x, double y, double angle, Entity entityParent) {
        return switch (IdShellType) {
            case ID_SHELL_BASIC -> new ShellBasic(id, x, y, angle, entityParent);
            default -> new ShellBasic(id, x, y, angle, entityParent);
        };
    }
}
