// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal,
// nor will I accept the actions of those who do.
// -- Mason Dooley (906230479)

package spacecolonies;

import student.TestCase;

/**
 * This class will test the methods in the Person
 * class
 * 
 * @author Mason Dooley
 * @version 2022.11.04
 *
 */
public class PersonTest extends TestCase {

    //Fields
    private Person person;
    
    /**
     * Runs before each test method and creates a Person
     * object
     */
    public void setUp() {
        person = new Person("Mason", 1, 2, 3, "Earth");
    }
    
    /**
     * Will test the Person constructor class. Will also 
     * test getName(), getSkillset(), getPlanetPreference(),
     * and toString()
     */
    public void testPerson() {
        assertEquals(person.getName(), "Mason");
        assertEquals(person.getPlanetPreference(), "Earth");
        assertEquals(person.getSkills().getAgriculture(), 1);
        assertEquals(person.getSkills().getMedicine(), 2);
        assertEquals(person.getSkills().getTechnology(), 3);
        assertEquals(person.toString(), "Mason A:1 M:2 T:3 Wants: Earth");
    }
    
    /**
     * Will test the equals() method
     * Person will be tested for equality against itself so 
     * true should be returned
     */
    public void testEquals() {
        assertTrue(person.equals(person));
    }
    
    /**
     * Will test the equals() method
     * Person will be tested for equality against null so 
     * false should be returned
     */
    public void testEquals2() {
        assertFalse(person.equals(null));
    }
    
    /**
     * Will test the equals() method
     * Person will be tested for equality against an Object
     * so false should be returned
     */
    public void testEquals3() {
        Object object = new Object(); 
        
        assertFalse(person.equals(object));
    }
    
    /**
     * Will test the equals() method
     * Person will be tested for equality against a testPerson
     * with a different name so false should be returned
     */
    public void testEquals4() {
        Person testPerson = new Person("NotMason", 1, 2, 3, "Earth");
        
        assertFalse(person.equals(testPerson));
        
        //ToStrings should also differ
        assertNotSame(person.toString(), testPerson.toString());
    }
    
    /**
     * Will test the equals() method
     * Person will be tested for equality against a testPerson
     * with a different agriculture value so false should be returned
     */
    public void testEquals5() {
        Person testPerson = new Person("Mason", 4, 2, 3, "Earth");
        
        assertFalse(person.equals(testPerson));
        
        //ToStrings should also differ
        assertNotSame(person.toString(), testPerson.toString());
    }
    
    /**
     * Will test the equals() method
     * Person will be tested for equality against a testPerson
     * with a different medicine value so false should be returned
     */
    public void testEquals6() {
        Person testPerson = new Person("Mason", 1, 3, 3, "Earth");
        
        assertFalse(person.equals(testPerson));
        
        //ToStrings should also differ
        assertNotSame(person.toString(), testPerson.toString());
    }
    
    /**
     * Will test the equals() method
     * Person will be tested for equality against a testPerson
     * with a different technology value so false should be returned
     */
    public void testEquals7() {
        Person testPerson = new Person("Mason", 1, 2, 4, "Earth");
        
        assertFalse(person.equals(testPerson));
        
        //ToStrings should also differ
        assertNotSame(person.toString(), testPerson.toString());
    }
    
    /**
     * Will test the equals() method
     * Person will be tested for equality against a testPerson
     * with a different Planet Preference value so false should be returned
     */
    public void testEquals8() {
        Person testPerson = new Person("Mason", 1, 2, 3, "NotEarth");
        
        assertFalse(person.equals(testPerson));
        
        //ToStrings should also differ
        assertNotSame(person.toString(), testPerson.toString());
    }
    
    /**
     * Will test the equals() method
     * Person will be tested for equality against a testPerson
     * with the same name, skill values, and planet preference
     */
    public void testEquals9() {
        Person testPerson = new Person("Mason", 1, 2, 3, "Earth");
        
        assertTrue(person.equals(testPerson));
        
        //ToStrings should be equal
        assertEquals(person.toString(), testPerson.toString());
    }
    
    /**
     * Will test the toString() method with a Person
     * who does not have a planet Preference
     */
    public void testToString() {
        Person noPlanetPerson = new Person("Mason", 1, 2, 3, "");
        
        assertEquals(noPlanetPerson.getPlanetPreference(), "");
        assertEquals(noPlanetPerson.toString(), "No-Planet Mason A:1 M:2 T:3");
    }
}
