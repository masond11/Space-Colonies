// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal,
// nor will I accept the actions of those who do.
// -- Mason Dooley (906230479)

package spacecolonies;

import student.TestCase;

/**
 * Will test the methods in the Planet class
 * 
 * @author Mason Dooley
 * @version 2022.11.08
 *
 */
public class PlanetTest extends TestCase {

    //Fields
    private Planet planet;
    
    /**
     * Runs before each test method and creates
     * a Planet object
     */
    public void setUp() {
        planet = new Planet("Earth", 1, 2, 3, 5);
    }
    
    /**
     * Will test the constructor in Planet
     */
    public void testPlanet() {
        
        //The constructor should create a Planet named "Earth"
        assertEquals(planet.getName(), "Earth");
        
        //Planet should have a skillSet of agriculture 1,
        //Medicine 2, and technology 3.
        assertEquals(planet.getSkills().getAgriculture(), 1);
        assertEquals(planet.getSkills().getMedicine(), 2);
        assertEquals(planet.getSkills().getTechnology(), 3);
        
        //The planet's capacity should be 5 with no population and therefore
        //an availability of 5.
        assertEquals(planet.getCapacity(), 5);
        assertEquals(planet.getPopulationSize(), 0);
        assertEquals(planet.getAvailability(), 5);
        
        //Testing the toString()
        assertEquals(planet.toString(),
            "Earth, population 0 (cap: 5), Requires: A >= 1, M >= 2, T >= 3");
    }
    
    /**
     * Will test the setName() method by
     * changing the planet name to Mars and
     * checking that the name was changed with getName()
     */
    public void testSetName() {
        planet.setName("Mars");
        assertEquals(planet.getName(), "Mars");
        
        //ToString() should also change due to the name change
        assertEquals(planet.toString(),
            "Mars, population 0 (cap: 5), Requires: A >= 1, M >= 2, T >= 3");
    }
    
    /**
     * Will test the addPerson() method.
     * A testPerson will be attempted to be added
     * to the planet, and since the testPerson will be
     * qualified and the planet is notFull() the addition
     * should be successful
     */
    public void testAddPerson() {
        //testPerson skills are all greater than or equal to
        //the Planet's minSkills
        Person testPerson = new Person("Mason", 1, 2, 3, "Earth");
        
        //Addition should be successful
        assertTrue(planet.addPerson(testPerson));
        
        //population size should be 1, availability should be 4
        //and capacity should remain unchanged (5)
        assertEquals(planet.getPopulationSize(), 1);
        assertEquals(planet.getAvailability(), 4);
        assertEquals(planet.getCapacity(), 5);
        
        //Get the planet population
        Person[] population = planet.getPopulation();
        
        //Population should contain 1 Person with a
        //toString that matches the attributes of the testPerson
        assertEquals(population[0].toString(),
            "Mason A:1 M:2 T:3 Wants: Earth");
    }
    
    /**
     * Will test the addPerson() method.
     * A testPerson will be attempted to be added
     * to the planet, but its skillset should result in
     * an unsuccessful addition (medicine score will be too low)
     */
    public void testAddPerson2() {
        //testPerson's medicine score needs to be 2 or greater
        //but is 1
        Person testPerson = new Person("Mason", 1, 1, 3, "Earth");
        
        //Addition should be unsuccessful
        assertFalse(planet.addPerson(testPerson));
        
        //Population Size, availability, and capacity should
        //remain unchanged
        assertEquals(planet.getPopulationSize(), 0);
        assertEquals(planet.getAvailability(), 5);
        assertEquals(planet.getCapacity(), 5);
        
        //Get the planet population
        Person[] population = planet.getPopulation();
        
        //Trying to call toString() on the first population element
        //should result in a NullPointerException
        Exception exception = null;
        try
        {
            population[0].toString();
            fail("toString() is not throwing an exception when it should");
        }
        catch (Exception e)
        {
            exception = e;
        }
        assertTrue("toString() is throwing the wrong type of exceptions",
                   exception instanceof NullPointerException);
    }
    
    /**
     * Will test the addPerson() method.
     * Attempting to add more than 5 persons should result
     * in failed attempts to add those persons
     */
    public void testPerson3() {
        //Creating 6 testPersons. All have the required
        //skills to join the planet
        Person testPerson = new Person("Mason", 4, 5, 3, "Earth");
        Person testPerson2 = new Person("Derek", 4, 5, 3, "Earth");
        Person testPerson3 = new Person("Zack", 3, 2, 5, "Earth");
        Person testPerson4 = new Person("Trevor", 5, 2, 4, "Earth");
        Person testPerson5 = new Person("Aidan", 4, 4, 4, "Earth");
        Person testPerson6 = new Person("Matt", 1, 5, 3, "Earth");
        
        //First 5 should be successful
        assertTrue(planet.addPerson(testPerson));
        assertTrue(planet.addPerson(testPerson2));
        assertTrue(planet.addPerson(testPerson3));
        assertTrue(planet.addPerson(testPerson4));
        assertTrue(planet.addPerson(testPerson5));
        
        //Population Size should be 5 and availibilty should
        //be 0.
        assertEquals(planet.getPopulationSize(), 5);
        assertEquals(planet.getAvailability(), 0);
        
        //Adding another Person should fail
        assertFalse(planet.addPerson(testPerson6));
        
        //Population Size should remain 5
        assertEquals(planet.getPopulationSize(), 5);
        
        //Get the planet population
        Person[] population = planet.getPopulation();
        
        //Will loop through the population to confirm
        //that testPerson6 is not located in it
        for (int i = 0; i < population.length; i++) {
            assertFalse(population[i].equals(testPerson6));
        } 
    }
    
    /**
     * Will test the equals() method.
     * planet will be compared for equality against itself
     * so true should be returned
     */
    public void testEquals() {
        assertTrue(planet.equals(planet));
    }
    
    /**
     * Will test the equals() method.
     * planet will be compared for equality against null
     * so false should be returned
     */
    public void testEquals2() {
        assertFalse(planet.equals(null));
    }
    
    /**
     * Will test the equals() method.
     * planet will be compared for equality against an Object
     * so false should be returned
     */
    public void testEquals3() {
        Object object = new Object();
        assertFalse(planet.equals(object));
    }
    
    /**
     * Will test the equals() method.
     * planet will be compared for equality against a Planet
     * with a different name so false should be returned
     */
    public void testEquals4() {
        Planet testPlanet = new Planet("NotEarth", 1, 2, 3, 5);
        assertFalse(planet.equals(testPlanet));
    }
    
    /**
     * Will test the equals() method.
     * planet will be compared for equality against a Planet
     * with a different agriculture score so false should be returned
     */
    public void testEquals5() {
        Planet testPlanet = new Planet("Earth", 2, 2, 3, 5);
        assertFalse(planet.equals(testPlanet));
    }
   
    /**
     * Will test the equals() method.
     * planet will be compared for equality against a Planet
     * with a different medicine score so false should be returned
     */
    public void testEquals6() {
        Planet testPlanet = new Planet("Earth", 1, 1, 3, 5);
       
        assertFalse(planet.equals(testPlanet));
    } 
   
    /**
     * Will test the equals() method.
     * planet will be compared for equality against a Planet
     * with a different technology score so false should be returned
     */
    public void testEquals7() {
        Planet testPlanet = new Planet("Earth", 1, 2, 4, 5);
       
        assertFalse(planet.equals(testPlanet));
    }
   
    /**
     * Will test the equals() method.
     * planet will be compared for equality against a Planet
     * with a different capacity so false should be returned
     */
    public void testEquals8() {
        Planet testPlanet = new Planet("Earth", 1, 2, 3, 6);
       
        assertFalse(planet.equals(testPlanet));
    }
   
    /**
     * Will test the equals() method.
     * planet will be compared for equality against a Planet
     * with a different population Size so false should be returned
     */
    public void testEquals9() {
        Planet testPlanet = new Planet("Earth", 1, 2, 3, 5);
        Person testPerson = new Person("Mason", 4, 5, 3, "Earth");
        assertTrue(planet.addPerson(testPerson));
       
        assertFalse(planet.equals(testPlanet));
    } 
   
    /**
     * Will test the equals() method.
     * planet will be compared for equality against a Planet
     * with a different people in its population so false should be returned
     */
    public void testEquals10() {
        Planet testPlanet = new Planet("Earth", 1, 2, 3, 5);
        Person testPerson = new Person("Mason", 4, 5, 3, "Earth");
        Person testPerson2 = new Person("Derek", 4, 5, 4, "Earth");
       
        assertTrue(planet.addPerson(testPerson));
        assertTrue(testPlanet.addPerson(testPerson2));
       
        assertFalse(planet.equals(testPlanet));
    }
   
    /**
     * Will test the equals() method.
     * planet will be compared for equality against a Planet
     * with the same Person in its population so true should be returned
     */
    public void testEquals11() {
        Planet testPlanet = new Planet("Earth", 1, 2, 3, 5);
        Person testPerson = new Person("Mason", 4, 5, 4, "Earth");
       
        assertTrue(planet.addPerson(testPerson));
        assertTrue(testPlanet.addPerson(testPerson));
       
        assertTrue(planet.equals(testPlanet));
    }
    
    /**
     * Will test the compareTo() method
     * planet will be compared to a testPlanet of 
     * smaller capacity
     */
    public void testCompareTo() {
        Planet testPlanet = new Planet("Earth", 1, 2, 3, 4);
       
        assertEquals(planet.compareTo(testPlanet), 1);
    }
   
    /**
     * Will test the compareTo() method
     * planet will be compared to a testPlanet of 
     * greater capacity
     */
    public void testCompareTo2() {
        Planet testPlanet = new Planet("Earth", 1, 2, 3, 6);
       
        assertEquals(planet.compareTo(testPlanet), -1);
    }
   
    /**
     * Will test the compareTo() method
     * planet will be compared to a testPlanet of 
     * the same capacity but smaller availability
     */
    public void testCompareTo3() {
        Planet testPlanet = new Planet("Earth", 1, 2, 3, 5);
        Person testPerson = new Person("Mason", 4, 5, 3, "Earth");
       
        assertTrue(testPlanet.addPerson(testPerson));
        
        assertEquals(planet.compareTo(testPlanet), 1);
    }
   
    /**
     * Will test the compareTo() method
     * planet will be compared to a testPlanet of 
     * the same capacity but greater availability
     */
    public void testCompareTo4() {
        Planet testPlanet = new Planet("Earth", 1, 2, 3, 5);
        Person testPerson = new Person("Mason", 4, 5, 3, "Earth");
       
        assertTrue(planet.addPerson(testPerson));
       
        assertEquals(planet.compareTo(testPlanet), -1);
    }
   
    /**
     * Will test the compareTo() method
     * planet will be compared to a testPlanet of 
     * the same capacity and availability, but the testPlanet
     * with have a smaller sum of skills than planet
     */
    public void testCompareTo5() {
        Planet testPlanet = new Planet("Earth", 1, 2, 2, 5);
       
        assertEquals(planet.compareTo(testPlanet), 1);
    }
    
    /**
     * Will test the compareTo() method
     * planet will be compared to a testPlanet of 
     * the same capacity and availability, but the testPlanet
     * with have a greater sum of skills than planet
     */
    public void testCompareTo6() {
        Planet testPlanet = new Planet("Earth", 1, 2, 4, 5);
       
        assertEquals(planet.compareTo(testPlanet), -1);
    }
   
    /**
     * Will test the compareTo() method
     * planet will be compared to a testPlanet of 
     * the same capacity, availability, and skills,
     * but the testPlanet will be smaller in
     * lexicographic order
     */
    public void testCompareTo7() {
        Planet testPlanet = new Planet("Aase", 1, 2, 3, 5);
       
        assertTrue(planet.compareTo(testPlanet) > 0);
    }
   
    /**
     * Will test the compareTo() method
     * planet will be compared to a testPlanet of 
     * the same capacity, availability, and skills,
     * but the testPlanet will be greater in
     * lexicographic order
     */
    public void testCompareTo8() {
        Planet testPlanet = new Planet("Saturn", 1, 2, 3, 5);
       
        assertTrue(planet.compareTo(testPlanet) < 0);
    }
}
