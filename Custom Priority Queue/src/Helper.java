/**
 * Helper class used to test the ADT_Heap and ADT_Priority_Queue
 * @author Nicholas Zukaitis
 * @version 1.0
 */
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;

public class Helper {
    /**
     * Arraylist used for testing the heap
     */
    private ArrayList<Employee> employeeData = new ArrayList<Employee>();
    /**
     * File holding test data
     */
    private File file;
    /**
     * Reads in test data from file
     */
    private BufferedReader reader;
    /**
     * Instance of ADT_Priority_Queue
     */
    private ADT_Priority_Queue queue;
    /**
     * Instance of PayComparator
     */
    private PayComparator payComparator = new PayComparator();
    /**
     * Instance of NameComparator
     */
    private NameComparator nameComparator = new NameComparator();

    /**
     * Constructor for Helper class
     * Instanciates the file and reader fields
     */
    public Helper() {
        try{
            file = new File("test_data.txt");
            reader = new BufferedReader(new FileReader(file));

        }
        catch(IOException e){
            System.out.println("Test file does not exist");
        }

    }

    /**
     * Reads in test data from file and shows heapsort functioning with both int and string comparators
     */
    public void start() {
        try{
            String line;
            String name;
            Integer pay;
            while((line = reader.readLine()) != null){
                String[] text = line.split(" ");
                name = text[0].concat(" ");//This assumes format "Firstname Lastname Pay"
                name = name.concat(text[1]);
                pay = Integer.parseInt(text[text.length-1].substring(0,text[text.length-1].length()-3)); //removes the .00 in the pay

                employeeData.add(new Employee(name, pay));
            }
            reader.close();
        }
        catch(IOException e){
            System.out.println("error reading in data");
        }

        queue = new ADT_Priority_Queue(employeeData, nameComparator);

        queue.printHeap();
        queue.setComparator(payComparator);

        System.out.println("");

        queue.sort();
        queue.printHeap();

        System.out.println("");

        queue.setComparator(nameComparator);
        queue.sort();

        queue.printHeap();

        System.out.println("");

        System.out.println(queue.extractMax());

        System.out.println("");

        queue.printHeap();

        System.out.println("");

        queue.insert(new Employee("Kid A", 1300));
        queue.sort();

        queue.printHeap();
    }
}
