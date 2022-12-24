// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal,
// nor will I accept the actions of those who do.
// -- Mason Dooley (906230479)
package spacecolonies;

import student.TestCase;

/**
 * @author Mason Dooley
 * @version 2022.11.08
 *
 */
public class SpaceColonyDataExceptionTest extends TestCase {

    /**
     * This will test the SpaceColonyDataException
     */
    public void testDataException() {
        SpaceColonyDataException exception =
            new SpaceColonyDataException("Invalid");
        assertEquals(exception.getMessage(), "Invalid");
    }
}
