// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal,
// nor will I accept the actions of those who do.
// -- Mason Dooley (906230479)

package spacecolonies;

import queue.EmptyQueueException;
import queue.QueueInterface;

/**
 * This class creates a queue where entries are removed
 * from the front and added to the back. The queue array
 * has a length of one more than the capacity of entries
 * permitted
 * 
 * @author Mason Dooley
 * @version 2022.11.04
 * @param <T> Generic
 *
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    //Fields
    private T[] queue;
    private int enqueueIndex;
    private int dequeueIndex;
    private int size;
    /**
     * Field to create a Default Capacity for the queue if
     * one is not specified
     */
    public static final int DEFAULT_CAPACITY = 20;
    
    /**
     * Constructor for the ArrayQueue class. Will create
     * an array with a size of the queue capacity + 1.
     * DequeueIndex (front) will be at 0 and enqueueIndex (back) will
     * be at the last Index in the array
     * @param capacity Amount of entries in the queue
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        queue = (T[]) new Object[capacity + 1];
        enqueueIndex = queue.length - 1;
        dequeueIndex = 0;
    }
    
    /**
     * This class checks to see if a queue
     * has reached capacity. If it has, the capacity will
     * be doubled to allow for more entries to be entered.
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (isFull()) {
            //Queue is full, so the queue capacity must be
            //doubled by changing the array length to
            //2 times the old length minus 1.
            T[] oldQueue = queue;
            int oldLength = oldQueue.length;
            int newLength = 2 * oldLength - 1;
            queue = (T[]) new Object[newLength];
            
            int j = dequeueIndex;
            for (int i = 0; i < oldLength - 1; i++) {
                queue[i] = oldQueue[j];
                j = (j + 1) % oldLength;
            }
            
            //Reset the front and back index
            dequeueIndex = 0;
            enqueueIndex = oldLength - 2;
        }
    }
    
    /**
     * Getter method for the array length
     * 
     * @return int array length
     */
    public int getLengthOfUnderlyingArray() {
        return queue.length;
    }
    
    /**
     * Getter method for the size of the queue
     * 
     * @return current amount of entries in queue
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Checks to see if the queue is full (has reached
     * max capacity)
     * 
     * @return boolean if the queue is full
     */
    private boolean isFull() {
        return (enqueueIndex + 2) % queue.length == dequeueIndex;
    }
    
    /**
     * Clears the queue by dequeuing each entry.
     */
    @Override
    public void clear() {
        while (!isEmpty()) {
            dequeue();
        }
        
        //Resetting the front and back index
        enqueueIndex = queue.length - 1;
        dequeueIndex = 0;
    }


    /**
     * 
     */
    @Override
    public T dequeue() {
        T front = getFront();
        queue[dequeueIndex] = null;
        dequeueIndex = (dequeueIndex + 1) % queue.length;
        size--;
        return front;
    }

    /**
     * Adds an entry to the back of the queue (enqueue/back index)
     * 
     * @param newEntry Will be added to the queue
     */
    @Override
    public void enqueue(T newEntry) {
        //Make sure queue is not full
        ensureCapacity();
        
        //Add entry and increment size
        enqueueIndex = (enqueueIndex + 1) % queue.length;
        queue[enqueueIndex] = newEntry;
        size++;
    }

    /**
     * Getter method for the front entry in the queue
     * 
     * @return T Front entry of the queue
     * @throws EmptyQueueException if the queue is empty
     */
    @Override
    public T getFront() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        
        return queue[dequeueIndex];
    }


    /**
     * Checks to see if the queue is empty
     * 
     * @return boolean if the queue is empty
     */
    @Override
    public boolean isEmpty() {
        return ((enqueueIndex + 1) % queue.length) == dequeueIndex;
    }

    /**
     * Copies the entries in the array to an array of Objects[]
     * 
     * @return Object[] contains the queue's contents
     * @throws EmptyQueueException if the queue is empty
     */
    public Object[] toArray() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        
        Object[] contents = new Object[size];
        
        //Copy each entry from queue into the
        //contents array that will be returned
        for (int i = 0; i < size; i++) {
            contents[i] = queue[(dequeueIndex + i) % queue.length];
        }
        
        return contents;
    }
    
    /**
     * Will display the entries in queue in order from front
     * to back
     * 
     * @return String entries in the queue
     * 
     */
    public String toString() {
        
        if (isEmpty()) {
            //No entries. Returned empty brackets
            return "[]";
        }
        
        StringBuilder entries = new StringBuilder();
        entries.append("[");
        
        //Add each entry to the StringBuilder
        for (int i = 0; i < size; i++) {
            entries.append(queue[(dequeueIndex + i) % queue.length].toString());
            entries.append(", ");
        }
        
        //Remove the ", " at the end of the StringBuilder
        entries.delete(entries.length() - 2, entries.length());
        entries.append("]");
        
        return entries.toString();
    }
    
    /**
     * Checks to see if two queues are equal.
     * Two queues are equal if they have the same entries
     * in the same order
     * 
     * @param obj Will be checked for equality against the this queue
     * @return boolean if the two queues are equal
     */
    @SuppressWarnings("unchecked")
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
            ArrayQueue<T> other = (ArrayQueue<T>) obj;
            
            
            if (other.getSize() != this.getSize()) {
                //Amount of entries in the two queues differ, so
                //cannot be equal
                return false;
            }
            
            //Iterate through each entry in both queues in order
            //from front to back
            for (int i = 0; i < size; i++) {
                //Obtain the entries
                T thisEntry = queue[(dequeueIndex + i) % queue.length];
                T otherEntry = other.queue[(other.dequeueIndex + i)
                                           % other.queue.length];
                
                //If two corresponding entries are not equal, then
                //the queues cannot be equal
                if (!thisEntry.equals(otherEntry)) {
                    return false;
                }
            }
            
            //The corresponding entries in the two arrays were
            //equal at each entry. Queues are equal
            return true;
        }
        
        //Classes are different so cannot be equal
        return false;
    }
}
