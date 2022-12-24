// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal,
// nor will I accept the actions of those who do.
// -- Mason Dooley (906230479)
package spacecolonies;

import student.TestCase;

/**
 * This class will test the methods in the
 * Skillset class
 * 
 * @author Mason Dooley
 * @version 2022.11.04
 *
 */
public class SkillsetTest extends TestCase {

    //Fields
    private Skillset skillset;
    
    /**
     * Runs before each test method and creates a Skillset
     * object
     */
    public void setUp() {
        skillset = new Skillset(1, 2, 3);
    }
    
    /**
     * Will test the SkillSet constructor and 
     * getAgriculture(), getMedicine(), getTechnology(), and toString()
     */
    public void testSkillSet() {
        assertEquals(skillset.getAgriculture(), 1);
        assertEquals(skillset.getMedicine(), 2);
        assertEquals(skillset.getTechnology(), 3);
        assertEquals(skillset.toString(), "A:1 M:2 T:3");
    }
    
    /**
     * Will test the CompareTo() method
     * A testSkillset will be created with a sum of skills
     * less than skillset (6)
     */
    public void testCompareTo() {
        //Skillset with sum of 5
        Skillset testSkillset = new Skillset(1, 2, 2);
        
        //The this Skillset (skillset) has a greater sum
        //than the parameter skillset (testSkillset), so a positive integer (1)
        //should be returned
        assertEquals(skillset.compareTo(testSkillset), 1);
    }
    
    /**
     * Will test the CompareTo() method
     * A testSkillset will be created with a sum of skills
     * greater than skillset (6)
     */
    public void testCompareTo2() {
        //Skillset with sum of 7
        Skillset testSkillset = new Skillset(1, 2, 4);
        
        //The this Skillset (skillset) has a smaller sum
        //than the parameter skillset (testSkillset), so a negative integer (-1)
        //should be returned
        assertEquals(skillset.compareTo(testSkillset), -1);
    }
    
    /**
     * Will test the CompareTo() method
     * A testSkillset will be created with a sum of skills
     * that is equal than skillset (6)
     */
    public void testCompareTo3() {
        //Skillset with sum of 6
        Skillset testSkillset = new Skillset(3, 1, 2);
        
        //The this Skillset (skillset) has the same sum
        //than the parameter skillset (testSkillset), so 0
        //should be returned
        assertEquals(skillset.compareTo(testSkillset), 0);
    }
    
    /**
     * Will test the lessThanOrEqualTo() method
     * A testSkillset will be created with each skill being less
     * than skillset so False should be returned
     */
    public void testLessThanOrEqualTo() {
        //every skill in testSkillset is less than the corresponding
        //skill in skillset
        Skillset testSkillset = new Skillset(0, 1, 2);
        
        assertFalse(skillset.isLessThanOrEqualTo(testSkillset));
    }
    
    /**
     * Will test the lessThanOrEqualTo() method
     * A testSkillset will be created with a 
     * greater agriculture score but a less technology and medicine score, 
     * so False should still be returned
     */
    public void testLessThanOrEqualTo2() {
        //agriculture score is greater than skillset
        Skillset testSkillset = new Skillset(2, 1, 2);
        
        assertFalse(skillset.isLessThanOrEqualTo(testSkillset));
    }
    
    /**
     * Will test the lessThanOrEqualTo() method
     * A testSkillset will be created with a 
     * greater agriculture and medicine score but a less technology score, 
     * so False should still be returned
     */
    public void testLessThanOrEqualTo3() {
        //agriculture and medicine score is greater than skillset
        Skillset testSkillset = new Skillset(2, 3, 2);
        
        assertFalse(skillset.isLessThanOrEqualTo(testSkillset));
    }
    
    /**
     * Will test the lessThanOrEqualTo() method
     * A testSkillset will all skills being greater than skillset
     * so true should be returned
     */
    public void testLessThanOrEqualTo4() {
        //all scores are greater than skillset
        Skillset testSkillset = new Skillset(2, 3, 4);
        
        assertTrue(skillset.isLessThanOrEqualTo(testSkillset));
    }
    
    /**
     * Will test the equals() method
     * skillset will be compared for equality against
     * itself so true should be returned
     */
    public void testEquals() {
        assertTrue(skillset.equals(skillset));
    }
    
    /**
     * Will test the equals() method
     * skillset will be compared for equality against
     * null so false should be returned
     */
    public void testEquals2() {
        assertFalse(skillset.equals(null));
    }
    
    /**
     * Will test the equals() method
     * skillset will be compared for equality against
     * a testObject so false should be returned
     */
    public void testEquals3() {
        Object testObject = new Object();
        assertFalse(skillset.equals(testObject));
    }
    
    /**
     * Will test the equals() method
     * skillset will be compared for equality against
     * a testSkillset which has a different agriculture score
     * so false should be returned
     */
    public void testEquals4() {
        Skillset testSkillset = new Skillset(2, 2, 3);
        
        assertFalse(skillset.equals(testSkillset));
        
        //ToStrings should also differ
        assertNotSame(skillset.toString(), testSkillset.toString());
    }
    
    /**
     * Will test the equals() method
     * skillset will be compared for equality against
     * a testSkillset which has a different Medicine score
     * so false should be returned
     */
    public void testEquals5() {
        Skillset testSkillset = new Skillset(1, 3, 3);
        
        assertFalse(skillset.equals(testSkillset));
        
        //ToStrings should also differ
        assertNotSame(skillset.toString(), testSkillset.toString());
    }
    
    /**
     * Will test the equals() method
     * skillset will be compared for equality against
     * a testSkillset which has a different technology score
     * so false should be returned
     */
    public void testEquals6() {
        Skillset testSkillset = new Skillset(1, 2, 2);
        
        assertFalse(skillset.equals(testSkillset));
        
        //ToStrings should also differ
        assertNotSame(skillset.toString(), testSkillset.toString());
    }
    
    /**
     * Will test the equals() method
     * skillset will be compared for equality against
     * a testSkillset which has the same scores as skillset
     * so false should be returned
     */
    public void testEquals7() {
        Skillset testSkillset = new Skillset(1, 2, 3);
        
        assertTrue(skillset.equals(testSkillset));
        
        //ToStrings() should be equal
        assertEquals(skillset.toString(), testSkillset.toString());
    }
}
