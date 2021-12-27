/**
 * @author Nicholas Zukaitis
 * Node class that represents a single node in an expression tree
 * @param <T> The type of data stored in a node
 */
public class Node<T> {
    /**
     * Left child of the node
     */
    private Node<T> leftChild;
    /**
     * Right child of the node
     */
    private  Node<T> rightChild;
    /**
     * Value, aka key, of the node
     */
    private T value;

    /**
     * Default constructor of the Node class, sets all fields to null
     */
    public Node(){
        leftChild = null;
        rightChild = null;
        value = null;
    }

    /**
     * Overloaded constructor of the Node class, sets the value field to the param element
     * @param element The value, aka key, of the node
     */
    public Node(T element){
        leftChild = null;
        rightChild = null;
        value = element;
    }

    /**
     * Overloaded constructor of the Node class, sets the values of the left and right children
     * @param left The left child of the node
     * @param right The right child of the node
     */
    public Node(Node left, Node right){
        leftChild = left;
        rightChild = right;
        value = null;
    }

    /**
     * Overloaded constructor of the Node class, sets values for value, rightChild and leftChild fields
     * @param element The value, aka key, of the node
     * @param right The right child of the node
     * @param left The left child of the node
     */
    public Node(T element, Node right, Node left){
        leftChild = left;
        rightChild = right;
        value = element;
    }

    /**
     * Gets the left child of the node
     * @return The left child of the node
     */
    public Node getLeftChild() {
        return leftChild;
    }

    /**
     * Sets the left child of the node
     * @param left The new left child of then node
     */
    public void setLeftChild(Node left) {
        leftChild = left;
    }

    /**
     * Gets the right child of the node
     * @return The right child of the node
     */
    public Node getRightChild() {
        return rightChild;
    }

    /**
     * Sets the right child of the node
     * @param right The new right child of the node
     */
    public void setRightChild(Node right) {
        rightChild = right;
    }

    /**
     * Gets the value, aka key, of the node
     * @return The value, aka key, of the node
     */
    public T getValue() {
        return value;
    }

    /**
     * Overridden equals() for the Node class, it compares the value field of another node to the value field of this node
     * @param o The other node being compared
     * @return If the two nodes are equal
     */
    public boolean equals(Object o){
        o = (Node)o;
        return value.equals(((Node) o).getValue());
    }

    /**
     * Overridden toString(), the string representation of a node
     * @return The value, aka key, of this node
     */
    public String toString(){
        return value.toString();
    }
}
