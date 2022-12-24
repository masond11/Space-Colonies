// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal,
// nor will I accept the actions of those who do.
// -- Mason Dooley (906230479)

package spacecolonies;

import queue.EmptyQueueException;
import student.TestCase;

/**
 * Will test the methods in the ArrayQueue class
 * 
 * @author Mason Dooley
 * @version 2022.11.04
 *
 */
public class ArrayQueueTest extends TestCase {

    //Fields
    private ArrayQueue<Person> queue;
    
    /**
     * Runs before each test method and creates an ArrayQueue object
     * object
     */
    public void setUp() {
        queue = new ArrayQueue<Person>(4);
    }
    
    /**
     * Will test the Constructor of ArrayQueue
     * queue should be Empty() with a length of 5, an empty toString,
     * and size of 0. Trying to dequeue and calling toArray()
     * should throw an EmptyStackException.
     */
    public void testArrayQueue() {
        assertTrue(queue.isEmpty());
        assertEquals(queue.getLengthOfUnderlyingArray(), 5);
        assertEquals(queue.getSize(), 0);
        assertEquals(queue.toString(), "[]");
        
        //Trying to dequeue should throw an EmptyQueueException
        Exception exception = null;
        try
        {
            queue.dequeue();
            fail("dequeue() is not throwing an exception when it should");
        }
        catch (Exception e)
        {
            exception = e;
        }
        assertTrue("dequeue() is throwing the wrong type of exception",
                   exception instanceof EmptyQueueException);
        
        
        //Trying to call toArray() should throw an EmptyQueueException
        Exception exception2 = null;
        try
        {
            queue.toArray();
            fail("toArray() is not throwing an exception when it should");
        }
        catch (Exception e)
        {
            exception2 = e;
        }
        assertTrue("toArray() is throwing the wrong type of exceptions",
                   exception2 instanceof EmptyQueueException);
    }
    
    /**
     * Will test the enqueue() method
     * Will enqueue a testPerson object that should
     * be at the front when calling getFront().
     * Size should increase by 1.
     */
    public void testEnqueue() {
        Person testPerson = new Person("Mason", 1, 2, 3, "Earth");
        queue.enqueue(testPerson);
        
        assertEquals(queue.getFront(), testPerson);
        assertEquals(queue.getSize(), 1);
        assertEquals(queue.toString(), "[Mason A:1 M:2 T:3 Wants: Earth]");
    }
    
    /**
     * Will test the enqueue() method
     * Will enqueue a testPerson object past capacity
     * to ensure the queue capacity is doubled and the length
     * is now oldLength*2 - 1.
     */
    public void testEnqueue2() {
        //Create and add 6 testPersons
        Person testPerson = new Person("Mason", 1, 2, 3, "Earth");
        Person testPerson2 = new Person("Derek", 2, 2, 4, "Mars");
        Person testPerson3 = new Person("Matt", 1, 5, 3, "Jupiter");
        Person testPerson4 = new Person("Cam", 4, 3, 3, "");
        Person testPerson5 = new Person("Flip", 2, 3, 1, "Earth");
        Person testPerson6 = new Person("Bert", 1, 2, 3, "");
        queue.enqueue(testPerson);
        queue.enqueue(testPerson2);
        queue.enqueue(testPerson3);
        queue.enqueue(testPerson4);
        queue.enqueue(testPerson5);
        queue.enqueue(testPerson6);
        
        //Length should be 9, testPerson should be the front,
        //and the size should be 6
        assertEquals(queue.getLengthOfUnderlyingArray(), 9);
        assertEquals(queue.getFront(), testPerson);
        assertEquals(queue.getSize(), 6);
    }
    
    /**
     * Will test the enqueue() method
     * Will test the enqueue() method works
     * properly when wrapping around
     */
    public void testEnqueue3() {
        Person testPerson = new Person("Mason", 1, 2, 3, "Earth");
        Person testPerson2 = new Person("Derek", 2, 2, 4, "");
        Person testPerson3 = new Person("Matt", 1, 5, 3, "Jupiter");
        Person testPerson4 = new Person("Flip", 2, 3, 1, "Earth");
        queue.enqueue(testPerson);
        queue.enqueue(testPerson2);
        queue.enqueue(testPerson3);
        queue.dequeue();
        queue.enqueue(testPerson);
        queue.dequeue();
        queue.enqueue(testPerson4);
        queue.enqueue(testPerson2);
        //Array should be [person2, null, person3, person1, person4]
        
        //Queue length should remain the same
        assertEquals(queue.getLengthOfUnderlyingArray(), 5);
        
        //Size should be 4
        assertEquals(queue.getSize(), 4);
        
        //Front should be testPerson3
        assertEquals(queue.getFront(), testPerson3);
        
        //Testing toString()
        assertEquals(queue.toString(), "[Matt A:1 M:5 T:3 Wants: Jupiter, "
            + "Mason A:1 M:2 T:3 Wants: Earth, Flip A:2 M:3 T:1 Wants: Earth, "
            + "No-Planet Derek A:2 M:2 T:4]");
        
        //Will test that toArray() works properly and that the
        //dequeue() properly emptys the queue
        Object[] contents = queue.toArray();
        for (int i = 0; i < queue.getSize(); i++) {
            assertEquals(contents[i], queue.dequeue());
        }
    }
    
    /**
     * Will test the equals() method
     * queue will be test for equality against itself
     * so should return true
     */
    public void testEquals() {
        assertTrue(queue.equals(queue));
    }
    
    /**
     * Will test the equals() method
     * queue will be test for equality against null
     * so should return false
     */
    public void testEquals2() {
        assertFalse(queue.equals(null));
    }
    
    /**
     * Will test the equals() method
     * queue will be test for equality against an Object
     * so should return false
     */
    public void testEquals3() {
        Object object = new Object();
        assertFalse(queue.equals(object));
    }
    
    /**
     * Will test the equals() method
     * queue will be tested for equality against 
     * a testQueue of different size so should return false
     */
    public void testEquals4() {
        ArrayQueue<Person> testQueue = new ArrayQueue<Person>(5);
        testQueue.enqueue(new Person("Mason", 1, 2, 3, "Earth"));
        
        //Sizes are different
        assertNotSame(queue.getSize(), testQueue.getSize());
        
        //So should not be equal
        assertFalse(queue.equals(testQueue));
    }
    
    /**
     * Will test the equals() method
     * queue will be tested for equality against 
     * a testQueue of the same size so but different
     * entries so false should be return
     */
    public void testEquals5() {
        ArrayQueue<Person> testQueue = new ArrayQueue<Person>(5);
        testQueue.enqueue(new Person("Mason", 1, 2, 3, "Earth"));
        queue.enqueue(new Person("Mason", 1, 3, 3, "Earth"));
        
        //Sizes are the same
        assertEquals(queue.getSize(), testQueue.getSize());
        
        //But entries are different (different medicine score)
        //so False should be returned
        assertFalse(queue.equals(testQueue));
        
        //ToStrings should also differ
        assertNotSame(queue.toString(), testQueue.toString());
    }
    
    /**
     * Will test the equals() method
     * queue will be tested for equality against 
     * a testQueue of the same size and the same
     * entry so true should be returned
     */
    public void testEquals6() {
        ArrayQueue<Person> testQueue = new ArrayQueue<Person>(5);
        testQueue.enqueue(new Person("Mason", 1, 2, 3, "Earth"));
        queue.enqueue(new Person("Mason", 1, 2, 3, "Earth"));
        
        //Sizes are the same
        assertEquals(queue.getSize(), testQueue.getSize());
        
        //and Entries are the same so true should be returned
        assertTrue(queue.equals(testQueue));
        
        //ToStrings should also differ
        assertNotSame(queue.toString(), testQueue.toString());
    }
    
    /**
     * Will test the equals() method
     * Will added 30 entries that are the same to both queues
     * so true should be returned
     */
    public void testEquals7() {
        ArrayQueue<Person> testQueue = new ArrayQueue<Person>(5);
        for (int i = 0; i < 30; i++) {
            testQueue.enqueue(new Person("Mason", 1, 2, 3, "Earth"));
            queue.enqueue(new Person("Mason", 1, 2, 3, "Earth"));
        }
        //Sizes are the same
        assertEquals(queue.getSize(), testQueue.getSize());
        
        //and Entries are the same so true should be returned
        assertTrue(queue.equals(testQueue));
        
        //ToStrings should also be the same
        assertEquals(queue.toString(), testQueue.toString());
    }
    
    /**
     * Will test the clear() method
     * 3 entries will be added and removed with clear
     */
    public void testClears() {
        Person testPerson = new Person("Mason", 1, 2, 3, "Earth");
        Person testPerson2 = new Person("Derek", 2, 2, 4, "");
        Person testPerson3 = new Person("Matt", 1, 5, 3, "Jupiter");
        queue.enqueue(testPerson);
        queue.enqueue(testPerson2);
        queue.enqueue(testPerson3);
        
        //Size should be 3 and queue should not be empty
        assertEquals(queue.getSize(), 3);
        assertFalse(queue.isEmpty());
        
        queue.clear();
        
        //Size should be 0 and queue should be empty
        assertEquals(queue.getSize(), 0);
        assertTrue(queue.isEmpty());
    }
}
