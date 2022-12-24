// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal,
// nor will I accept the actions of those who do.
// -- Mason Dooley (906230479)

package spacecolonies;

import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * @author Mason Dooley
 * @version 2022.11.08
 *
 */
public class ProjectRunner {

    /**
     * main method with call a colony reader which will
     * create a SpaceWindow
     * 
     * @param args
     * @throws FileNotFoundException
     * @throws SpaceColonyDataException
     * @throws ParseException
     */
    public static void main(String[] args) 
        throws FileNotFoundException, SpaceColonyDataException, ParseException {
        new ColonyReader("input.txt", "planets.txt");
    }
}
