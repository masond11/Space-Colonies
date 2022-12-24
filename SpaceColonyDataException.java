// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal,
// nor will I accept the actions of those who do.
// -- Mason Dooley (906230479)

package spacecolonies;

/**
 * @author Mason Dooley
 * @version 2022.11.08
 *
 */
@SuppressWarnings("serial")
public class SpaceColonyDataException extends Exception {

    /**
     * Constructor for SpaceColonyDataException
     * 
     * @param message Exception message
     */
    public SpaceColonyDataException(String message) {
        super(message);
    }
}
