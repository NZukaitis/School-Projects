/**
 * Implementation of an ADT Priority Queue
 * Uses ADT Heap as the primary data type
 * @author Nicholas Zukaitis
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Comparator;

public class ADT_Priority_Queue<E> {
    /**
     * Primary data type of the Priority Queue
     */
    private ADT_Heap heap;

    /**
     * Constructor for the ADT_Priority_Queue class
     * Instanciates an empty heap
     */
    public ADT_Priority_Queue() {
        heap = new ADT_Heap();
    }

    /**
     * Constructor for the ADT_Prioriy_Queue class
     * @param data Arraylist that the heap uses as storage
     */
    public ADT_Priority_Queue(ArrayList<E> data){
        heap = new ADT_Heap(data);
    }

    /**
     * Constructor for the ADT_Priorty_Queue class
     * @param compare Comparator used for heapsort
     */
    public ADT_Priority_Queue(Comparator compare){
        heap = new ADT_Heap(compare);
    }

    /**
     * Constructor for the ADT_Priorty_Queue class
     * @param data Arraylist that the heap uses as storage
     * @param compare Comparator used for heapsort
     */
    public ADT_Priority_Queue(ArrayList<E> data, Comparator compare){
        heap = new ADT_Heap(data, compare);
    }

    /**
     * Compares two elements of the heap based on a specified comparator
     * Ex: o1 is greater than o2 (returns positive number)
     * @param o1 First element in comparison
     * @param o2 Second element in comparison
     * @return Positive if o1>o2, negative if o1<o2 and 0 if o1==o2
     */
    public int compare(E o1, E o2) {
        return heap.compare(o1, o2);
    }

    /**
     * Checks if the heap is empty
     * @return if the heap is empty
     */
    public boolean isEmpty(){
        return heap.isEmpty();
    }

    /**
     * Returns and removes the largest element in the heap
     * @return The larges value in the heap
     */
    public E extractMax(){
        return (E) heap.extractMax();
    }

    /**
     * Uses the heapsort alorithm to sort the heap based on a specified comparator
     */
    public void sort(){
        heap.heapSort();
    }

    /**
     * Insterts an element into the heap
     * @param element The element inserted into the heap
     */
    public void insert(E element){
        heap.insert(element);
    }

    /**
     * Changes the type of comparator used for heapsort
     * @param compare The new type of comparator
     */
    public void setComparator(Comparator compare){
        heap.setComparator(compare);
    }

    /**
     * Prints the values of the heap to the console, used for testing and to show the funcionality of the code
     */
    public void printHeap(){
        heap.printHeap();
    }
}
