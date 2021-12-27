import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Helps run and test the ADT_Graph, Edge, and Vertex classes
 * @author Nicholas Zukaitis
 * @version 1.0
 */
public class Helper {
    /**
     * Reads in data from test_data.txt
     */
    private BufferedReader reader;
    /**
     * ArrayList of vertices that gets passed into an ADT_Graph
     */
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    /**
     * ArrayList used to populate adjacency lists
     */
    private ArrayList<String[]> listOfAdjLists = new ArrayList<String[]>();
    /**
     * Vertex used to populate adjacency lists and edges
     */
    private Vertex vertex;
    /**
     * Graph used to test the ADT_Graph class
     */
    private ADT_Graph adt_graph;

    /**
     * Reads in data from test_data.txt, instantiates adt_graph, and tests the implementation of BFS and DFS
     */
    public void start(){
        try{
            reader = new BufferedReader(new FileReader("test_data.txt"));

            String line = reader.readLine();
            while(line != null){
                listOfAdjLists.add(line.split(","));

                line = reader.readLine();
            }
            reader.close();

            //making list of Vertices and their Adjacency lists
            for(int i = 0; i < listOfAdjLists.size(); i++){
                vertices.add(new Vertex(listOfAdjLists.get(i)[0]));
            }

            for(int i = 0; i < vertices.size(); i ++){
                vertex = vertices.get(i);
                for(int j = 1; j < listOfAdjLists.get(i).length;j++){
                    vertex.setNext(new Vertex(listOfAdjLists.get(i)[j]));
                    vertex = vertex.getNext();
                }
            }

            adt_graph = new ADT_Graph(vertices);

            //making edges list
            for(int i = 0; i < vertices.size(); i ++){
                vertex = vertices.get(i);
                for(int j = 1; j < listOfAdjLists.get(i).length;j++){
                    adt_graph.insertEdge(vertex,new Vertex(listOfAdjLists.get(i)[j]),j);
                }
            }

            System.out.println("Testing traverseBFS");
            adt_graph.printVertices();
            adt_graph.traverseBFS(vertices.get(0));
            System.out.println("");
            adt_graph.printVertices();

            System.out.println("");
            System.out.println("Testing traverseDFS");
            adt_graph.setVerticesColorWhite();
            adt_graph.printVertices();
            adt_graph.traverseDFS();
            System.out.println("");
            adt_graph.printVertices();
        }
        catch(IOException e){
            System.out.println("Unable to read data from file");
        }

    }
}
