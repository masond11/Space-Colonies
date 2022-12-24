// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal,
// nor will I accept the actions of those who do.
// -- Mason Dooley (906230479)

package spacecolonies;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * @author weeks
 *
 */
public class ColonyReader {

    //Fields
    private Planet[] planets;
    private ArrayQueue<Person> queue;
    
    /**
     * Constructor for the ColonyReader class. Will create a new
     * SpaceWindow by creating an array of planets and an ArrayQueue
     * using the filenames passed as parameters
     * 
     * @param applicantFileName
     * @param planetFileName
     * @throws FileNotFoundException
     * @throws SpaceColonyDataException
     * @throws ParseException
     */
    public ColonyReader(String applicantFileName, String planetFileName)
        throws FileNotFoundException, SpaceColonyDataException, ParseException {
        
        new SpaceWindow(new ColonyCalculator
            (readQueueFile(applicantFileName), readPlanetFile(planetFileName)));
        
    }
    
    /**
     * This method will read the planet file and create an Array of Planets
     * 
     * @param fileName
     * @return Planet[] planets that will be colonized
     * @throws FileNotFoundException
     * @throws SpaceColonyDataException
     * @throws ParseException
     */
    private Planet[] readPlanetFile(String fileName)
        throws FileNotFoundException, SpaceColonyDataException, ParseException {
        
        //Create an array of planets of length 3
        Planet[] planets = new Planet[ColonyCalculator.NUM_PLANETS];
        int lineCounter = 0;
        int dataCounter = 0;
        
        Scanner file = new Scanner(new File(fileName));
        
        while (file.hasNextLine() && lineCounter < planets.length) {
            String line = file.nextLine();
            Scanner lineParser = new Scanner(line).useDelimiter(",\\s*");
            String[] token = new String[5];
            int tokenCounter = 0;
            
            while (lineParser.hasNext() && tokenCounter < 5) {
                token[tokenCounter++] = lineParser.next();
                }
            lineParser.close();
            lineCounter++;
            
            if (tokenCounter != 5) {
                throw new java.text.ParseException("Not enough arguments", 0);
            }
            
            //Creating a tempPlanet using file information
            Planet tempPlanet = new Planet(token[0], Integer.valueOf(token[1]),
                Integer.valueOf(token[2]), Integer.valueOf(token[3]),
                Integer.valueOf(token[4]));
            
            //Checking if planet skillset is valid
            Skillset skillset = tempPlanet.getSkills();
            if (!isInSkillRange(skillset.getAgriculture(), skillset.getMedicine(),
                skillset.getTechnology())) {
                throw new SpaceColonyDataException("Invalid Skillset");
            }

            //Add tempPlanet
            planets[dataCounter] = tempPlanet;
            dataCounter++;
            
            }
        
        //Not enough planets were in the file
        if (planets.length < 3) {
            throw new SpaceColonyDataException("Less than 3 Planets");
        }
        
        //Close file and assign field variable
        file.close();
        this.planets = planets;
        return planets;
            
    }
    
    /**
     * This method will create an ArrayQueue of people.
     * 
     * @param fileName
     * @return ArrayQueue<Person> queue of applicant for planets
     * @throws SpaceColonyDataException
     * @throws FileNotFoundException
     * @throws ParseException
     */
    private ArrayQueue<Person> readQueueFile(String fileName)
        throws SpaceColonyDataException, FileNotFoundException, ParseException {
        
        ArrayQueue<Person> queue = new ArrayQueue<Person>(ArrayQueue.DEFAULT_CAPACITY);
        
        Scanner file = new Scanner(new File(fileName));
        
        while (file.hasNextLine()) {
            
            //Acquire file information on one line
            String line = file.nextLine();
            Scanner lineParser = new Scanner(line).useDelimiter(",\\s*");
            String[] token = new String[5];
            int tokenCounter = 0;
            
            //Acquire file information on one line
            while (lineParser.hasNext() && tokenCounter < 5) {
                token[tokenCounter++] = lineParser.next();
                }

            //No planet preference was specified, so an empty
            //string needs to be assigned
            if (tokenCounter == 4) {
                token[tokenCounter++] = "";
            }
            
            //Nowhere indicates an empty string is needed
            if (token[4].equals("Nowhere")) {
                token[4] = "";
            }
            
            lineParser.close();
            
            //Not enough arguments were added
            if (tokenCounter != 5) {
                throw new java.text.ParseException("Not enough arguments", 0);
            }
            
            //Creating a tempPerson with the information from the scanner
            Person tempPerson = new Person(token[0], Integer.valueOf(token[1]),
                Integer.valueOf(token[2]), Integer.valueOf(token[3]), token[4]);

            //Determining if the skills in the file were valid skills
            Skillset skillset = tempPerson.getSkills();
            if (!isInSkillRange(skillset.getAgriculture(), skillset.getMedicine(),
                skillset.getTechnology())) {
                
                throw new SpaceColonyDataException("Invalid Skillset");
            }

            //Add tempPerson to queue
            queue.enqueue(tempPerson);
            
        }
        
        //Close file and assign field queue
        file.close();
        this.queue = queue;
        return queue;
        
    }
    
    /**
     * Determines if the 3 skills in a file are valid skillsets.
     * Valid Skillsets are between 1 and 5
     * 
     * @param skill1 Agriculture Score
     * @param skill2 Medicine Score
     * @param skill3 Technology Score
     * @return boolean if the skills are valid
     */
    private boolean isInSkillRange(int skill1, int skill2, int skill3) {
        return skill1 <= ColonyCalculator.MAX_SKILL_LEVEL
            && skill1 >= ColonyCalculator.MIN_SKILL_LEVEL
            && skill2 <= ColonyCalculator.MAX_SKILL_LEVEL
            && skill2 >= ColonyCalculator.MIN_SKILL_LEVEL
            && skill3 <= ColonyCalculator.MAX_SKILL_LEVEL
            && skill3 >= ColonyCalculator.MIN_SKILL_LEVEL;
    }
    
    /**
     * Getter method for the queue
     * 
     * @return ArrayQueue<Person> queue
     */
    public ArrayQueue<Person> getQueue() {
        return queue;
    }
    
    /**
     * Getter method for planets
     * 
     * @return Planet[] planets
     */
    public Planet[] getPlanets() {
        return planets;
    }
}
