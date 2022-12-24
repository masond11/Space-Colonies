// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal,
// nor will I accept the actions of those who do.
// -- Mason Dooley (906230479)

package spacecolonies;

import list.AList;
import student.TestCase;

/**
 * @author Mason Dooley
 * @version 2022.11.08
 *
 */
public class ColonyCalculatorTest extends TestCase {
    
    //Fields
    private ColonyCalculator colonyCalculator;
    private Planet[] planets;
    private ArrayQueue<Person> queue;
    
    /**
     * The setUp() method runs before each test method
     * and creates a colonyCalculator with an inputQueue and inputArray
     * of planets. The inputQueue and inputArray are assigned to their
     * corresponding fields to be used for testing using
     * getPlanets() and getQueue(). 
     * 
     * The queue is equal to [Mason, Derek, Zack, Trevor, Aidan, null]
     * planets is equal [Uranus, Mars, Earth, Saturn, Mercury]
     */
    public void setUp() {
        
        ArrayQueue<Person> inputQueue = new ArrayQueue<Person>(5);
        Person testPerson = new Person("Mason", 4, 5, 3, "Earth");
        Person testPerson2 = new Person("Derek", 1, 5, 3, "Earth");
        Person testPerson3 = new Person("Zack", 3, 2, 5, "");
        Person testPerson4 = new Person("Trevor", 5, 2, 5, "Jupiter");
        Person testPerson5 = new Person("Aidan", 4, 4, 4, "Saturn");
        inputQueue.enqueue(testPerson);
        inputQueue.enqueue(testPerson2);
        inputQueue.enqueue(testPerson3);
        inputQueue.enqueue(testPerson4);
        inputQueue.enqueue(testPerson5);
        
        
        Planet[] inputPlanets = new Planet[5];
        Planet planet = new Planet("Uranus", 1, 2, 3, 5);
        Planet planet2 = new Planet("Mars", 5, 3, 2, 10);
        Planet planet3 = new Planet("Earth", 3, 2, 2, 5);
        Planet planet4 = new Planet("Saturn", 2, 2, 2, 15);
        Planet planet5 = new Planet("Mercury", 5, 2, 5, 25);
        inputPlanets[0] = planet;
        inputPlanets[1] = planet2;
        inputPlanets[2] = planet3;
        inputPlanets[3] = planet4;
        inputPlanets[4] = planet5;
        
        //Creater colonyCalculator
        colonyCalculator = new ColonyCalculator(inputQueue, inputPlanets);
        
        //get the colonyCalculator's planets and queue to use for testing
        this.planets = colonyCalculator.getPlanets();
        this.queue = colonyCalculator.getQueue();
    }
    
    /**
     * Test method for the ColonyCalculator Constructor
     * planets and queue order should be what was initialized
     * in the setUp() method.
     */
    public void testColonyCalculator() {
        
        //Testing if the planets in colonyCalculator are in the
        //proper order by testing their name and capacity
        assertEquals(planets[0].getName(), "Uranus");
        assertEquals(planets[1].getCapacity(), 10);
        assertEquals(planets[2].getName(), "Earth");
        assertEquals(planets[3].getName(), "Saturn");
        assertEquals(planets[4].getSkills().getAgriculture(), 5);
        
        assertEquals(queue.dequeue().getName(), "Mason");
        assertEquals(queue.dequeue().getName(), "Derek");
        assertEquals(queue.dequeue().getName(), "Zack");
        assertEquals(queue.dequeue().getName(), "Trevor");
        assertEquals(queue.dequeue().getName(), "Aidan");
    }
    
    /**
     * Test method for the ColonyCalculator Constructor
     * Null will be passed in so an IllegalArgumentException
     * should be thrown
     */
    public void testColonyCalculator2() {
        
        Exception exception = null;
        try
        {
            new ColonyCalculator(null, null);
            fail("ColonyCalculator() "
                + "is not throwing an exception when it should");
        }
        catch (Exception e)
        {
            exception = e;
        }
        assertTrue("ColonyCalculator()"
            + " is throwing the wrong type of exceptions",
                   exception instanceof IllegalArgumentException);
    }
    
    /**
     * Will test the getPlanetForPerson() method.
     * The first testPerson should get Earth as their
     * planet, but testPerson2 should return null since they
     * have an invalid agriculture score.
     */
    public void testGetPlanetForPerson() {
        
        //Planet Index should be 2 as that
        //is where Earth is located in the array of planets
        assertEquals(colonyCalculator.getPlanetIndex(
            queue.getFront().getPlanetPreference()), 2);
        
        //Confirm that the names are equal
        assertEquals(queue.getFront().getPlanetPreference(),
            planets[2].getName());
        
        //Getting a planet for the front Person in the
        //queue should return Earth (planets[2])
        //Since Earth is not full and the Person is qualified
        assertEquals(colonyCalculator.getPlanetForPerson(
            queue.dequeue()).getName(), "Earth");
        
        //The next Person (Derek) in the queue should not get Earth as their
        //planet as their agriculture score is too low
        assertTrue(queue.getFront().getSkills().getAgriculture()
            < planets[2].getSkills().getAgriculture());
        assertNull(colonyCalculator.getPlanetForPerson(queue.getFront()));
    }
    
    /**
     * Will test the getPlanetForPerson() method
     * Firstly the queue will be dequeued twice leaving
     * a Person with no planet preference (Zack) at the front.
     * Calling getPlanetForPerson() should result in the
     * planets[] being sorted by capacity, and the front
     * person being added to the planet with the second
     * highest capacity (Saturn) as they do not qualify for
     * Mercury (highest capacity)
     */
    public void testGetPlanetForPerson2() {
        
        queue.dequeue();
        queue.dequeue();
        
        //Confirm front of queue has no planetPreference
        assertEquals(queue.getFront().getPlanetPreference(), "");
        
        //GetPlanetPerson should return "Saturn"
        assertEquals(colonyCalculator.getPlanetForPerson(
            queue.dequeue()).getName(), "Saturn");
        
        //Get the array of planets to see if they were sorted
        planets = colonyCalculator.getPlanets();
        
        //Planets should be in order from lowest capacity to highest
        assertEquals(planets[0].getCapacity(), 5);
        assertEquals(planets[1].getCapacity(), 5);
        assertEquals(planets[2].getCapacity(), 10);
        assertEquals(planets[3].getCapacity(), 15);
        assertEquals(planets[4].getCapacity(), 25);
        
        //Person at the front of the queue (Trevor) has a planet preference
        //that is not valid, so getPlanetPreference() should return
        //-1
        assertEquals(colonyCalculator.getPlanetIndex(queue.getFront()
            .getPlanetPreference()), -1);
        
        //Trevor can be accepted to the highest capacity planet Mercury
        //so getPlanetPreference should return Mercury
        assertEquals(colonyCalculator.getPlanetForPerson(
            queue.dequeue()).getName(), "Mercury");
        
        //planets array should remain unchanged
        planets = colonyCalculator.getPlanets();
        
        assertEquals(planets[0].getCapacity(), 5);
        assertEquals(planets[1].getCapacity(), 5);
        assertEquals(planets[2].getCapacity(), 10);
        assertEquals(planets[3].getCapacity(), 15);
        assertEquals(planets[4].getCapacity(), 25);
    }
    
    /**
     * Test method for GetPlanetForPerson()
     * Will test that null is returned when a person
     * without a planet preference cannot find 
     * a planet they are qualified for 
     */
    public void testGetPlanetForPerson3() {
        
        //Remove all entries in queue
        int size = queue.getSize();
        for (int i = 0; i < size; i++) {
            queue.dequeue();
        }
          
        //Add unqualified Person to queue
        queue.enqueue(new Person("Eric", 1, 1, 1, ""));
        
        //Trying to find Eric a planet should return null
        assertNull(colonyCalculator.getPlanetForPerson(queue.getFront()));
    }

    /**
     * Test method for GetPlanetForPerson()
     * Will test that null is return when null is passed
     * as the person parameter.
     */
    public void testGetPlanetForPerson4() {
        assertNull(colonyCalculator.getPlanetForPerson(null));
    }
    
    /**
     * Will attempt to add all the applicants to a planet using accept()
     * Every testPerson outside of Derek (testPerson2)
     * should be added to a planet
     * and accept() should return true. Reject() will be called
     * to add Derek to the rejectBus.
     */
    public void testAddandReject() {
        //Mason prefers Earth and is qualified, so should be added
        //to it. queue size should decrease
        assertEquals(queue.getSize(), 5);
        assertEquals(colonyCalculator.getPlanetForPerson(
            queue.getFront()).getName(), "Earth");
        assertTrue(colonyCalculator.accept());
        assertEquals(queue.getSize(), 4);
        
        //Derek prefers Earth but is not qualified. False should be returned
        //when calling accept(), so Derek should be rejected
        assertNull(colonyCalculator.getPlanetForPerson(queue.getFront()));
        assertFalse(colonyCalculator.accept());
        //Size is unchanged
        assertEquals(queue.getSize(), 4);
        colonyCalculator.reject();
        assertEquals(queue.getSize(), 3);
        
        //Zack
        assertEquals(colonyCalculator.getPlanetForPerson(
            queue.getFront()).getName(), "Saturn");
        assertTrue(colonyCalculator.accept());
        assertEquals(queue.getSize(), 2);
        
        //Trevor
        assertEquals(colonyCalculator.getPlanetForPerson(
            queue.getFront()).getName(), "Mercury");
        assertTrue(colonyCalculator.accept());
        assertEquals(queue.getSize(), 1);
        
        //Aidan
        assertEquals(colonyCalculator.getPlanetForPerson(
            queue.getFront()).getName(), "Saturn");
        assertTrue(colonyCalculator.accept());
        assertEquals(queue.getSize(), 0);
        
        //No more people in queue, so False should be returned
        assertFalse(colonyCalculator.accept());
        
        //Reject bus should contain one person (Derek)
        AList<Person> rejectBus = colonyCalculator.getBus();        
        assertEquals(rejectBus.getLength(), 1);
        assertEquals(rejectBus.toString(), "[Derek A:1 M:5 T:3 Wants: Earth]");
    }
    
    /**
     * Will test accept(), reject(), and getPlanetPreference()
     * when the preferred Planet has no availability (is Full)
     */
    public void testAcceptAndReject2() {
        //Will replace the old Earth with an Earth that has
        //0 capacity (isFull())
        planets[2] = new Planet("Earth", 3, 1, 1, 0);
        
        //Due to Earth being full, front of queue (Mason) who prefers
        //Earth should not be accepted.
        assertNull(colonyCalculator.getPlanetForPerson(queue.getFront()));
        
        
        assertFalse(colonyCalculator.accept());
        //Size unchanged
        assertEquals(queue.getSize(), 5);
        colonyCalculator.reject();
        assertEquals(queue.getSize(), 4);
        
        //Reject bus should contain one person (Mason)
        AList<Person> rejectBus = colonyCalculator.getBus();        
        assertEquals(rejectBus.getLength(), 1);
        assertEquals(rejectBus.toString(), "[Mason A:4 M:5 T:3 Wants: Earth]");
    }
    
}
