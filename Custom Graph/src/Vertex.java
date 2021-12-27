/**
 *Representation of a vertex in a graph
 * @author Nicholas Zukaitis
 * @version 1.0
 */
public class Vertex {
    //BFS,DFS fields
    /**
     * The color of the vertex (white, gray, or black) used in BFS and DFS
     */
    private String color;
    /**
     * Predecessor of the vertex, used in BFS and DFS
     */
    private Vertex predecessor;
    /**
     * Distance of the vertex, only used in BFS
     */
    private int distance;
    /**
     * Time that the vertex was discovered, only used in DFS
     */
    private int discovery;
    /**
     * Time that the vertex has been finished, only used in DFS
     */
    private int finish;

    /**
     * Value of the Vertex
     */
    private Object value;

    //Adjacency list fields
    /**
     * The next Vertex in the adjacency list
     */
    private Vertex next;

    /**
     * Constructs an empty vertex
     */
    public Vertex(){
        color = "white";
        predecessor = null;
        distance = -1;
        discovery = -1;
        finish = -1;

        value = null;

        next = null;
    }

    /**
     * Constructs a vertex with a value
     * @param val The value of the vertex
     */
    public Vertex(Object val){
        color = "white";
        predecessor = null;
        distance = -1;
        discovery = -1;
        finish = -1;

        value = val;

        next = null;
    }

    /**
     * Sets the color of the vertex
     * @param c The new color of the vertex
     */
    public void setColor(String c){
        color = c;
    }

    /**
     * Sets the predecessor of the vertex
     * @param p The new predecessor of the vertex
     */
    public void setPredecessor(Vertex p){
        predecessor = p;
    }

    /**
     * Sets the distance of the vertex
     * @param d
     */
    public void setDistance(int d){
        distance = d;
    }

    /**
     * Sets the time of discovery for the vertex
     * @param d The time of discovery
     */
    public void setDiscovery(int d){
        discovery = d;
    }

    /**
     * Sets the finishing time of the vertex
     * @param f The finishing time
     */
    public void setFinish(int f){
        finish = f;
    }

    /**
     * Sets the value of the vertex
     * @param val The new value
     */
    public void setValue(Object val){
        value = val;
    }

    /**
     * Sets the next vertex in the adjacency list
     * @param n The next vertex
     */
    public void setNext(Vertex n){
        next = n;
    }

    /**
     * Returns the color of the vertex
     * @return The color of the vertex
     */
    public String getColor(){
        return color;
    }

    /**
     * Returns the predecessor of the vertex
     * @return The predecessor of the vertex
     */
    public Vertex getPredecessor(){
        return predecessor;
    }

    /**
     * Returns the distance of the vertex
     * @return The distance of the vertex
     */
    public int getDistance(){
        return distance;
    }

    /**
     * Returns the time of discovery of the vertex
     * @return The time of discovery of the vertex
     */
    public int getDiscovery(){
        return discovery;
    }

    /**
     * Returns the finishing time of the vertex
     * @return The finishing time of the vertex
     */
    public int getFinish(){
        return finish;
    }

    /**
     * Returns the value of the vertex
     * @return The value of the vertex
     */
    public Object getValue(){
        return value;
    }

    /**
     * Returns the next vertex in the adjacency list
     * @return The next vertex in the adjacency list
     */
    public Vertex getNext(){
        return next;
    }

    /**
     * Checks if another vertex is equal to this vertex, i.e if the vertices have the same value
     * @param obj The other vertex
     * @return If the two vertices are equal
     */
    public boolean equals(Object obj){
        Vertex other = (Vertex)obj;
        return value.equals(other.value);
    }

    /**
     * Returns a string representation of the vertex (the value)
     * @return A string representation of the vertex
     */
    public String toString(){
        return value.toString();
    }
}
