// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal,
// nor will I accept the actions of those who do.
// -- Mason Dooley (906230479)

package spacecolonies;

import java.util.Arrays;

/**
 * @author Mason Dooley
 * @version 2022.11.08
 */
public class Planet implements Comparable<Planet> {

    //Fields
    private String name;
    private Skillset minSkills;
    private Person[] population;
    private int populationSize;
    private int capacity;
    
    /**
     * Constructor for the Planet class. Initializes the
     * 5 fields based off of the parameters.
     * 
     * @param planetName String name
     * @param planetAgri Will create the Planet's minSkills
     * @param planetMedi Will create the Planet's minSkills
     * @param planetTech Will create the Planet's minSkills
     * @param planetCap Maximum occupants allowed on the planet
     */
    public Planet(String planetName,  int  planetAgri,
        int  planetMedi,  int  planetTech,  int  planetCap) {
        name = planetName;
        minSkills = new Skillset(planetAgri, planetMedi, planetTech);
        capacity = planetCap;
        populationSize = 0;
        population = new Person[planetCap];
    }
    
    /**
     * Setter method for the planet Name
     * 
     * @param name String new Planet name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Getter method for the Planet name
     * 
     * @return String na,e
     */
    public String getName() {
        return name;
    }
    
    /**
     * Getter method for the required skills
     * to live on the planet
     * 
     * @return Skillset minSkills
     */
    public Skillset getSkills() {
        return minSkills;
    }
    
    /**
     * Getter method for the planet Population
     * 
     * @return Person[] People living on the planet
     */
    public Person[] getPopulation() {
        return population;
    }
    
    /**
     * Getter method for current planet population
     * 
     * @return int populationSize
     */
    public int getPopulationSize() {
        return populationSize;
    }
    
    /**
     * Getter method for planet capacity
     * 
     * @return int capacity
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * Getter method for planet availability.
     * 
     * @return int Amount of available spots in the planet
     */
    public int getAvailability() {
        return capacity - populationSize;
    }
    
    /**
     * Will determine if the planet is full.
     * 
     * @return boolean If the planet is full
     */
    public boolean isFull() {
        return capacity == populationSize;
    }
    
    /**
     * This method will try to add a Person to the planet's
     * population. A person can be addded to a Planet if 
     * the planet if not full and the person's skillset deems
     * them qualified to join the planet
     * 
     * @param newPerson Person that will possibly be added to the Planet
     * @return boolean If the person was added
     */
    public boolean addPerson(Person newPerson) {
        
        if (isQualified(newPerson) && !isFull()) {
            population[populationSize] = newPerson;
            populationSize++;
            return true;
        }
        
        return false;
    }
    
    /**
     * Determines if a Person is qualified to be added
     * to a Planet's population. A person if qualified
     * for a planet if the planet's minSkills are all less
     * than the person's skillset
     * 
     * @param newPerson Will have their skillset checked
     * @return boolean If the person if qualified
     */
    public boolean isQualified(Person newPerson) {
        return minSkills.isLessThanOrEqualTo(newPerson.getSkills());
    }
    
    /**
     * Displays the contents of a Planet
     * 
     * @return String of planets attributes
     */
    public String toString() {
        StringBuilder entries = new StringBuilder();
        entries.append(name + ", population " + populationSize);
        entries.append(" (cap: " + capacity + "), Requires: ");
        entries.append("A >= " + minSkills.getAgriculture() + ", M >= " );
        entries.append(minSkills.getMedicine() + ", T >= "
            + minSkills.getTechnology());
        return entries.toString();
    }
    
    /**
     * Examines if two planets are equal to each other. Two planets
     * are equal if all their attributes are equal
     * 
     * @param object Will be compared against the this planet for equality
     * @return boolean If the planets are equal
     */
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        
        if (object == null) {
            return false;
        }
        
        if (object.getClass() == this.getClass()) {
            Planet other = (Planet) object;
            
            return other.name.equals(this.name)
                && other.getSkills().equals(this.getSkills())
                && other.capacity == this.capacity
                && other.populationSize == this.populationSize
                && Arrays.equals(other.getPopulation(), this.getPopulation());
        }
        
        return false;
    }
    
    /**
     * This method compares to Planets by examining attributes
     * in the following order; capacity, availability, skillset,
     * and name.
     * 
     * @param other Planet that wil be compared against the this planet
     * @return int If the this Planet is less than or greater than the parameter
     *      
     */
    @Override
    public int compareTo(Planet other) {
        if (this.capacity < other.capacity) {
            return -1;
        }
        else if (other.capacity < this.capacity) {
            return 1;
        }
        else {
            if (this.getAvailability() < other.getAvailability()) {
                return -1;
            }
            else if (other.getAvailability() < this.getAvailability()) {
                return 1;
            }
            
            else {
                if (this.minSkills.compareTo(other.minSkills) != 0) {
                    return this.minSkills.compareTo(other.minSkills);
                }
                else {
                    return (this.name).compareTo(other.name);
                }
            }
        }
    }
}
