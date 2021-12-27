/**
 * Object used to test the implementation of ADT_Heap and ADT_Priority_Queue
 */
public class Employee {
    /**
     * Used for checking int comparisons in heapsort
     */
    private int pay;
    /**
     * Used for checking string comparisons in heapsort
     */
    private String name;

    /**
     * Constructor for the Employee class
     * Instanciates an empty name and 0 for the pay field
     */
    public Employee(){
        name = "";
        pay = 0;
    }

    /**
     * Constructor for the Employee class
     * @param employeeName String for the name field
     * Instanciates 0 for the pay field
     */
    public Employee(String employeeName){
        name = employeeName;
        pay = 0;
    }

    /**
     * Constructor for the Employee class
     * @param payRate int for the pay field
     * Instanciates an empty name
     */
    public Employee(int payRate){
        name = "";
        pay = payRate;
    }

    /**
     * Constructor for the Employee class
     * @param employeeName string for the name field
     * @param payRate int for the pay field
     */
    public Employee(String employeeName, int payRate){
        name = employeeName;
        pay = payRate;
    }

    /**
     * Returns the pay field
     * @return The pay field
     */
    public int getPay(){
        return pay;
    }

    /**
     * Sets the pay field
     * @param payRate int for the pay field
     */
    public void setPay(int payRate){
        pay = payRate;
    }

    /**
     * Returns the name field
     * @return The name field
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name field
     * @param employeeName string for the name field
     */
    public void setName(String employeeName){
        name = employeeName;
    }

    /**
     * Returns the name and pay fields seperated by a comma
     * @return The name and play fields seperated by a comma
     */
    public String toString(){
        return String.format("%s, $%d", name, pay);
    }
}
