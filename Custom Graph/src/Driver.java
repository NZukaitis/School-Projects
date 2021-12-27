/**
 * Runs and tests the ADT_Graph, Edge, and Vertex classes
 * @author Nicholas Zukaitis
 * @version 1.0
 */
public class Driver {
    /**
     * Main method, runs and tests code
     * @param args Console arguments
     */
    public static void main(String[] args){
        ADT_Graph graph1 =  new ADT_Graph();

        System.out.println("Testing ADT_Graph(), isEmpty, numOfVertices, numOfEdges, ifEdgeExists, edgeWeight, insertEdge, deleteEdge and findEdge");
        System.out.println(graph1.isEmpty());
        System.out.println(graph1.numOfVertices());
        System.out.println(graph1.numOfEdges());
        System.out.println(graph1.ifEdgeExists(new Vertex("H"), new Vertex("I")));
        System.out.println(graph1.edgeWeight(new Vertex("H"), new Vertex("I")));

        graph1.insertEdge(new Vertex("H"), new Vertex("I"),10);
        System.out.println(graph1.isEmpty());
        System.out.println(graph1.numOfVertices());
        System.out.println(graph1.numOfEdges());
        System.out.println(graph1.ifEdgeExists(new Vertex("H"), new Vertex("I")));
        System.out.println(graph1.edgeWeight(new Vertex("H"), new Vertex("I")));
        System.out.println(graph1.findEdge(new Vertex("H"), new Vertex("I")));
        graph1.printVertices();

        graph1.deleteEdge(new Vertex("H"), new Vertex("I"));
        System.out.println(graph1.isEmpty());
        System.out.println(graph1.numOfVertices());
        System.out.println(graph1.numOfEdges());
        System.out.println(graph1.findEdge(new Vertex("H"), new Vertex("I")));

        System.out.println("");

        Helper h = new Helper();
        h.start();

    }
}
