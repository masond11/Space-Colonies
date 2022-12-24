// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal,
// nor will I accept the actions of those who do.
// -- Mason Dooley (906230479)

package spacecolonies;

import java.util.Arrays;
import list.AList;

/**
 * @author Mason Dooley
 * @version 2022.11.08
 *
 */
public class ColonyCalculator {
 
    //Fields
    /**
     * This field declares the number of planets that can
     * be read from a file
     */
    public static final int NUM_PLANETS = 3;
    
    /**
     * This field defines the minimum allowable input skillset
     */
    public static final int MIN_SKILL_LEVEL = 1;
    
    /**
     * This field defines the maximum allowable input skillset
     */
    public static final int MAX_SKILL_LEVEL = 5;
    private ArrayQueue<Person> applicantQueue;
    private AList<Person> rejectBus;
    private Planet[] planets;
    
    /**
     * Constructor for ColonyCalculator. Will initialize
     * the fields and throw an IllegalArgumentException
     * if the queue is null
     * 
     * @param queue ArrayQueue<Person>
     * @param planets Planet[]
     */
    public ColonyCalculator(ArrayQueue<Person> queue, Planet[] planets) {
        
        if (queue == null) {
            throw new IllegalArgumentException();
        }
        
        this.applicantQueue = queue;
        this.planets = planets;
        this.rejectBus = new AList<Person>();
    }
    
    /**
     * Getter method for planets
     * 
     * @return Planet[] planets
     */
    public Planet[] getPlanets() {
        return planets;
    }
    
    /**
     * Getter method for queue
     * 
     * @return ArrayQueue<Person> applicantQueue
     */
    public ArrayQueue<Person> getQueue() {
        return applicantQueue;
    }
    
    /**
     * Getter method for rejectBus
     * 
     * @return AList<Person> rejectBus
     */
    public AList<Person> getBus() {
        return rejectBus;
    }
    
    /**
     * Assigns a planet to a person. A person will be attempted
     * to be added to their preferred planet. If that planet is not
     * valid or the person does not have a preferred planet then
     * they will be assigned the planet with the highest capacity
     * that the person is qualified for. Failure to find a valid
     * planet for a person will result in null being returned.
     * 
     * @param nextPerson Person that is trying to find a planet
     * @return Planet assigned planet for person
     */
    public Planet getPlanetForPerson(Person nextPerson) {
        
        if (nextPerson == null) {
            //Person entry is invalid
            return null;
        }
        
        if (nextPerson.getPlanetPreference().equals("")) {
            //Person does not have a preferred planet
            //and will be assigned to the planet with the highest
            //capacity that they qualify for
            return getHighestCapacityPlanet(nextPerson);
        }
        
        else {
            //Determining if the person's preferred planet is valid
            //(-1 if not valid)
            int index = getPlanetIndex(nextPerson.getPlanetPreference());

            
            if (index != -1) {
                //Preferred planet is valid
                if (canAccept(planets[index], nextPerson)) {
                    
                    //Preferred planet is not full and person
                    //is Qualified to join
                    return planets[index];
                }
                
                else {
                    //Person was either not qualified to join the preferred
                    //planet or the preferred planet was full
                    return null;
                }
            }
            
            else {
                
                //Person's preferred planet was not valid
                return getHighestCapacityPlanet(nextPerson);
            }
        }
    }
    
    /**
     * This method will get the planet that has the highest capacity
     * for which a particular person is qualified for
     * 
     * @param person Person that is trying to find a planet
     * @return Planet Person's assigned planet
     */
    public Planet getHighestCapacityPlanet(Person person) {
        //Planets are sorted from lowest capacity to highest capacity
        Arrays.sort(planets);
        
        //Will loop through planets from highest capacity to lowest
        //The first planet that can accept the person will be returned
        for (int i = planets.length - 1; i > -1; i--) {
            
            if (canAccept(planets[i], person)) {
                return planets[i];
            }
        }
        
        //Unable to find a planet for the person
        return null;
    }
    
    /**
     * Will determined if the person can be accepted to a particular
     * planet. A planet can accept a person is the planet is not
     * full and the person is qualified for the planet
     * 
     * @param planet Planet 
     * @param person Person
     * @return boolean is person can be accepted to planet
     */
    private boolean canAccept(Planet planet, Person person) {
        
        return !planet.isFull() && planet.isQualified(person);
    }
    
    /**
     * This method gets the index that a planetName is located
     * in the array planets. Will be used to determine if
     * a person's preferred planet is valid (exists)
     * 
     * @param planetName Person's preferred planet
     * @return int Index of planet (-1 if it does not exist)
     */
    public int getPlanetIndex(String planetName) {
        
        //Loop through each entry in planets to see if there
        //is a match with planetName
        for (int i = 0; i < planets.length; i++) {
            
            if (planets[i].getName().equals(planetName)) {
                return i;
            }
        }
        
        //No planet is the array matched planetName
        return -1;
    }
    
    /**
     * This method rejects a person from joining
     * a planet when a planet could not be found for a person.
     * Will add the person to the rejectBus
     * and dequeue them from the applicantQueue
     */
    public void reject() {
        rejectBus.add(applicantQueue.dequeue());
    }
    
    /**
     * This method attempts to accept a Person to a planet.
     * A person can be accepted to a planet if the getPlanetForPerson
     * method for that person does not return null;
     * 
     * @return boolean if a Person was accepted
     */
    public boolean accept() {
        if (applicantQueue.isEmpty()) {
            //No more people in the queue
            return false;
        }
        
        Planet planet = getPlanetForPerson(applicantQueue.getFront());
        
        if (planet == null) {
            //Unable to find a planet for a person
            return false;
        }
        
        return planet.addPerson(applicantQueue.dequeue());
    }
}
