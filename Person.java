// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal,
// nor will I accept the actions of those who do.
// -- Mason Dooley (906230479)

package spacecolonies;

/**
 * This class creates a Person who will have
 * a name, a Skillset, and a planet preference
 * 
 * @author Mason Dooley
 * @version 2022.11.04
 *
 */
public class Person {

    //Fields
    private String name;
    private Skillset skills;
    private String planetPreference;
    
    /**
     * Constructor for the Person class. Will initializes the 
     * Person's name and planet along with creating their skillset
     * with the 3 scores.
     * 
     * @param name Person's name
     * @param agriculture score to be added to Skillset
     * @param medicine score to be added to Skillset
     * @param technology score to be added to Skillset
     * @param planet Person's preferred planet
     */
    public Person(String name,  int  agriculture,  int  medicine,
        int technology,  String  planet) {
        
        this.name = name;
        this.planetPreference = planet;
        
        skills = new Skillset(agriculture, medicine, technology);
    }
    
    /**
     * Getter method for name
     * @return String Person's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Getter method for planetPreference
     * @return String Person's planet preference
     */
    public String getPlanetPreference() {
        return planetPreference;
    }
    
    /**
     * Getter method for skills
     * @return Skillset Person's skills
     */
    public Skillset getSkills() {
        return skills;
    }
    
    /**
     * Displays the Person's attributes
     * @return String Person attributes
     */
    public String toString() {
        StringBuilder entries = new StringBuilder();
        
        if (planetPreference.equals("")) {
            //Person does not have a planet preference
            entries.append("No-Planet " + name + " ");
            entries.append(skills.toString());
        }
        else {
            //Person has a planet Preference
            entries.append(name + " ");
            entries.append(skills.toString() + " ");
            entries.append("Wants: " + planetPreference);
        }
        
        return entries.toString();
    }
    
    /**
     * Will determine if two Person objects are equal.
     * Two people are equal if their, name, skillset, and planet preference
     * are equal
     * 
     * @param obj Object that will be tested for equality
     * @return boolean If the two Person objects are equal
     */
    public boolean equals(Object obj) {
        
        if (this == obj) {
            //Both referring to the same place in memory
            //and therefore are equal
            return true;
        }
        
        if (obj == null) {
            //Parameter is null and therefore is not equal
            //to the this Skillset
            return false;
        }
        
        if (obj.getClass() == this.getClass()) {
            Person other = (Person) obj;
            
            //Determining if the name, skillset, and planet preference are equal
            return this.name.equals(other.name) && 
                this.skills.equals(other.skills) &&
                this.planetPreference.equals(other.planetPreference);
        }
        
        //Classes are different so cannot be equal
        return false;
    }
}
