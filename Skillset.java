// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal,
// nor will I accept the actions of those who do.
// -- Mason Dooley (906230479)

package spacecolonies;

/**
 * This class creates a Person's skillset
 * which consists of an agriculture, medicine, and technology score
 * 
 * @author Mason Dooley
 * @version 2022.11.04
 */
public class Skillset implements Comparable<Skillset> {

    //Fields
    private int agriculture;
    private int medicine;
    private int technology;
    
    /**
     * Constructor for the Skillset class.
     * Initializes the 3 scores
     * 
     * @param agriculture Person's agriculture score
     * @param medicine Person's medicine score
     * @param technology Person's technology score
     */
    public Skillset(int agriculture, int medicine, int technology) {
        this.agriculture = agriculture;
        this.medicine = medicine;
        this.technology = technology;
    }
    
    /**
     * Getter method for agriculture
     * 
     * @return int agriculture score
     */
    public int getAgriculture() {
        return agriculture;
    }
    
    /**
     * Getter method for medicine
     * 
     * @return int medicine score
     */
    public int getMedicine() {
        return medicine;
    }
    
    /**
     * Getter method for technology
     * 
     * @return int technology score
     */
    public int getTechnology() {
        return technology;
    }
    
    /**
     * Compares the sum of two Skillsets (the this Skillset and the parameter)
     * 
     * @param skills Skillset
     * @return int which Skillset had a greater sum
     */
    @Override
    public int compareTo(Skillset skills) {
        //Calculating sum of parameter skillset
        int skillsSum = skills.getAgriculture() + skills.getMedicine()
            + skills.getTechnology();
        
        //Calculating sum of the this skillset
        int currentSum = agriculture + medicine + technology;
        
        //This skillset sum is less than the parameter sum, so a
        //negative integer (-1) is returned
        if (currentSum < skillsSum) {
            return -1;
        }
       
        //This skillset sum is greater than the parameter sum, so a
        //positive integer (1) is returned
        if (currentSum > skillsSum) {
            return 1;
        }
        
        //Skillset sums are equal
        return 0;
    }

    /**
     * Method will see if a parameter every skill in the this
     * Skillset is less than the parameter Skillset
     * 
     * @param skills Skillset
     * @return boolean if the other skillset is less than the this skillset
     */
    public boolean isLessThanOrEqualTo(Skillset skills) {
        return this.agriculture <= skills.getAgriculture() && 
            this.medicine <= skills.getMedicine() && 
                this.technology <= skills.getTechnology();
    }
    
    /**
     * Method will display a Skillset of a Person.
     * 
     * @return String of Skillset
     */
    public String toString() {
        return "A:" + agriculture + " M:" + medicine + " T:" + technology;
    }
    
    /**
     * Method wills see if two Skillsets are equal.
     * Two Skillsets are equal if each of their agriculture, medicine,
     * and technology scores are equal
     * 
     * @param obj Will be tested for equality against the this skilset
     * @return boolean if the two Skillset are equals
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
        
        if (this.getClass() == obj.getClass()) {
            Skillset other = (Skillset) obj;
            
            //Determining If each skill is equal in value
            return other.agriculture == this.agriculture &&
                other.medicine == this.medicine && 
                other.technology == this.technology;
        }
        
        //Classes are different so cannot be equal
        return false;
    }
    
    
}
