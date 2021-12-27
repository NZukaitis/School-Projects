/**
 * Representation of an edge in a graph
 * @author Nicholas Zukaitis
 * @version 1.0
 */
public class Edge {
    /**
     * The first vertex of the edge
     */
    private final Vertex V1;
    /**
     * The second vertex of the edge
     */
    private final Vertex V2;
    /**
     * The weight of the edge
     */
    private final int WEIGHT;

    /**
     * Constructs and empty edge
     */
    public Edge(){
        V1 = null;
        V2 = null;
        WEIGHT = -1;
    }

    /**
     * Constructs an unweighted edge between two vertices
     * @param vertex1 The first vertex of the edge
     * @param vertex2 The second vertex of the edge
     */
    public Edge(Vertex vertex1, Vertex vertex2){
        V1 = vertex1;
        V2 = vertex2;
        WEIGHT = 1;
    }

    /**
     * Constructs a weighted edge between two vertices
     * @param vertex1 The first vertex of the edge
     * @param vertex2 The second vertex of the edge
     * @param w The weight of the edge
     */
    public Edge(Vertex vertex1, Vertex vertex2, int w){
        V1 = vertex1;
        V2 = vertex2;
        WEIGHT = w;
    }

    /**
     * Returns the weight of the edge
     * @return The weight of the edge
     */
    public int getWeight(){
        return WEIGHT;
    }

    /**
     * Checks if the given vertex is one of the edge's vertices
     * @param vertex The given vertex
     * @return If the given vertex is one of the edge's vertices
     */
    public boolean contains(Vertex vertex){
        if((V1.equals(vertex) || V2.equals(vertex)) && vertex != null) return true;
        else return false;
    }

    /**
     * Checks if another edge is equal to this edge, i.e if the edges have the same vertices
     * @param obj The other edge
     * @return If the two edges are equal
     */
    public boolean equals(Object obj){
        Edge other = (Edge) obj;

        if(V1.equals(V2)){
            if(contains(other.V1) && contains(other.V2)) return true;
            else return false;
        }

        else{
            if((contains(other.V1) && contains(other.V2)) && !other.V1.equals(other.V2)){
                return true;
            }
            else return false;
        }
    }

    /**
     * Returns a string representation of the edge (the value of the two edges and the weight)
     * @return A string representation of the edge
     */
    public String toString(){
        String s = "Vertex 1: " + V1.toString() + "\n" + "Vertex 2: " + V2.toString() + "\n" + "Weight: " + WEIGHT;
        return s;
    }
}
