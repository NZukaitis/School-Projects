/**
 * Implementation of an ADT Heap
 * @author Nicholas Zukaitis
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Comparator;

public class ADT_Heap<E> {
    /**
     * Arraylist that functions as dynamic storage for the heap
     */
    private ArrayList<E> data;
    /**
     * Comparator that determines how to compare data in the heap
     */
    private Comparator comparator;

    /**
     * Constructor for the ADT_Heap class
     * Instanciates a blank comparator and an empty Arraylist
     */
    public ADT_Heap(){
        data = new ArrayList<E>();
        comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
    }

    /**
     * Constructor for the ADT_Heap class
     * @param inputData A predefined Arraylist that is used for the data field
     * Instanciates a blank comparator
     */
    public ADT_Heap(ArrayList<E> inputData){
        data = inputData;
        comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
    }

    /**
     * Constructor for the ADT_Heap class
     * @param compare Type of comparator used for Heapsort
     * Instanciates an empty Arraylist
     */
    public ADT_Heap(Comparator compare){
        data = new ArrayList<E>();
        comparator = compare;
    }

    /**
     * Constructor for the ADT_Heap class
     * @param inputData A predefined Arraylist that is used for the data field
     * @param compare Type of comparator used for Heapsort
     */
    public ADT_Heap(ArrayList<E> inputData, Comparator compare){
        data = inputData;
        comparator = compare;
    }

    /**
     * Makes any given index larger than its children
     * @param index Index of a specific value in the heap
     * @param heapSize Size of the heap
     */
    private void maxHeapify(int index, int heapSize){
        if(!isEmpty()){
            E temp;
            E largestElement = data.get(index);
            int largestIndex = index;

            if(2*index < heapSize){
                E leftChild = data.get(2*index);
                if(compare(leftChild,data.get(index)) > 0){
                    largestElement = leftChild;
                    largestIndex = 2*index;
                }
            }
            else largestElement = data.get(index);

            if((2*index)+1 < heapSize){
                E rightChild = data.get((2*index)+1);
                if(compare(rightChild,largestElement) > 0){
                    largestElement = rightChild;
                    largestIndex = (2*index)+1;
                }
            }

            if(!largestElement.equals(data.get(index))){
                temp = data.get(index);
                data.set(index, largestElement);
                data.set(largestIndex, temp);
                maxHeapify(largestIndex, heapSize);
            }
        }
    }

    /**
     * Generates a max heap
     */
    private void buildMaxHeap(){
        for(int i = (int) Math.ceil(data.size()/2.0); i > 0; i--){
            maxHeapify(i-1, data.size());
        }
    }

    /**
     * Uses the heapsort alogrithm to sort a heap based on the comparator field
     */
    protected void heapSort(){
        E temp;
        buildMaxHeap();
        for(int i = data.size()-1; i > 0; i--){
            temp = data.get(0);
            data.set(0,data.get(i));
            data.set(i,temp);
            maxHeapify(0, i);
        }
    }

    /**
     * Checks if the heap is empty
     * @return if the heap is empty
     */
    public boolean isEmpty(){
        return data.isEmpty();
    }

    /**
     * Inserts an element into the heap
     * @param element The element that is inserted into the heap
     */
    protected void insert(E element){
        if(element == null){
            System.out.println("Element is null and was not inserted");
        }
        else data.add(element);
    }

    /**
     * Compares two items in the heap based on the comparator field
     * Ex: o1 is greater than o2 (returns positive number)
     * @param o1 First item in comparison
     * @param o2 Second item in comparison
     * @return Positive if o1>o2, negative if o1<o2 and 0 if o1==o2
     * @throws NullPointerException If o1 or o2 are null
     * @throws ClassCastException If o1 and o2 are non comparable types (Ex: int and string)
     */
    protected int compare (E o1, E o2) throws NullPointerException, ClassCastException {
        try{
            return comparator.compare(o1, o2);
        }
        catch (Exception e){
            System.out.println("Unable to compare items");
            return -999;
        }

    }

    /**
     * Removes and returns the largest value in the heap
     * @return The largest value in the heap
     */
    protected E extractMax(){
        if(!isEmpty()){
            E item;
            buildMaxHeap();

            item = data.get(0);
            data.set(0,data.get(data.size()-1));
            data.set(data.size()-1,item);
            data.remove(data.size()-1);

            return item;
        }
        else return null;
    }

    /**
     * Changes the type of comparator used
     * @param compare The new type of comparator
     */
    protected void setComparator(Comparator compare){
        comparator = compare;
    }

    /**
     * Prints the values of the heap to the console, used for testing and to show the funcionality of the code
     */
    protected void printHeap(){
        System.out.println(data.toString());
    }
}
