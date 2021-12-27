import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Implementation of an ADT Graph
 * @author Nicholas Zukaitis
 * @version 1.0
 */
public class ADT_Graph {
    /**
     * Dynamic storage for the graph's vertices
     */
    private ArrayList<Vertex> vertices;
    /**
     * Dynamic storage for the edges between vertices
     */
    private ArrayList<Edge> edges;
    /**
     * Time counter used for the DFS algorithm
     */
    private int time;

    /**
     * Constructs an empty ADT_Graph
     */
    public ADT_Graph(){
        vertices = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
    }

    /**
     * Constructs an ADT_Graph with vertices and no edges
     * @param vertexList The list of vertices in the graph
     */
    public ADT_Graph(ArrayList<Vertex> vertexList){
        vertices = vertexList;
        edges = new ArrayList<Edge>();
    }

    /**
     * Constructs an ADT_Graph with vertices and edges
     * @param vertexList The list of vertices in the graph
     * @param edgeList The list of edges in the graph
     */
    public ADT_Graph(ArrayList<Vertex> vertexList, ArrayList<Edge> edgeList){
        vertices = vertexList;
        edges = edgeList;
    }

    /**
     * Checks if the graph is empty by checking the number of vertices
     * @return If the graph is empty
     */
    public boolean isEmpty(){
        return vertices.isEmpty();
    }

    /**
     * Returns the number of vertices in the graph
     * @return The number of vertices in the graph
     */
    public int numOfVertices(){
        return vertices.size();
    }

    /**
     * Returns the number of edges in the graph
     * @return The number of edges in the graph
     */
    public int numOfEdges(){
        return edges.size();
    }

    /**
     * Checks if the graph contains an edge between two given vertices. It always returns a weight because the assignment specified that the ADT Graph has to be weighted
     * @param v1 The first vertex of the edge
     * @param v2 The second vertex of the edge
     * @return The weight of the edge, if the edge does not exist in the graph -1 is returned
     */
    public int ifEdgeExists(Vertex v1, Vertex v2){
        Edge edgeV1V2 = new Edge(v1, v2);

        for(int i = 0; i < edges.size(); i++){
            if(edges.get(i).equals(edgeV1V2)){
                return edges.get(i).getWeight();
            }
        }
        return -1;
    }

    /**
     * Returns the weight of the edge between two given vertices
     * @param v1 The first vertex of the edge
     * @param v2 The second vertex of the edge
     * @return The weight of the edge, if the edge does not exist in the graph -1 is returned
     */
    public int edgeWeight(Vertex v1, Vertex v2){
        return ifEdgeExists(v1, v2);
    }

    /**
     * Inserts an edge between two given vertices into the graph. The graph is always weighted, so every edge must have a weight
     * @param v1 The first vertex of the edge
     * @param v2 The second vertex of the edge
     * @param weight The weight of the edge
     */
    public void insertEdge(Vertex v1, Vertex v2, int weight){
        //if the edge does not exist in the graph
        if(ifEdgeExists(v1,v2) == -1){

            //if both vertices already exist in the graph
            if(vertices.contains(v1) && vertices.contains(v2)){

                //add v2 to the adjacency list of v1
                Vertex temp = v1;
                while(temp.getNext() != null){
                    temp = temp.getNext();
                }
                temp.setNext(new Vertex(v2.getValue()));

                //add v1 to the adjacency list of v2
                temp = v2;
                while(temp.getNext() != null){
                    temp = temp.getNext();
                }
                temp.setNext(new Vertex(v1.getValue()));

                edges.add(new Edge(v1,v2,weight));
            }

            //if v1 is not in the graph, but v2 is
            else if(!vertices.contains(v1) && vertices.contains(v2)){

                //adds v1 to the array of vertices and adds v2 to the adjacency list of v1
                v1.setNext(v2);
                vertices.add(v1);

                //adds v1 to the adjacency list of v2
                Vertex temp = v2;
                while (temp.getNext() != null){
                    temp = temp.getNext();
                }
                temp.setNext(new Vertex(v1.getValue()));

                edges.add(new Edge(v1,v2,weight));
            }

            //if v2 is not in the graph, but v1 is
            else if(vertices.contains(v1) && !vertices.contains(v2)){

                //adds v2 to the adjacency list of v1
                Vertex temp = v1;
                while(temp.getNext() != null){
                    temp = temp.getNext();
                }
                temp.setNext(new Vertex(v2.getValue()));

                //adds v2 to the vertices matrix and adds v1 to the adjacency list of v2
                v2.setNext(v1);
                vertices.add(v2);

                edges.add(new Edge(v1,v2,weight));
            }

            //if both vertices are not in the graph
            else if(!vertices.contains(v1) && !vertices.contains(v2)){
                //adds v1 to the vertices matrix and adds v2 to the adjacency list of v1
                v1.setNext(v2);
                vertices.add(v1);

                //adds v2 to the vertices matrix and adds v1 to the adjacency list of v2
                v2.setNext(v1);
                vertices.add(v2);

                edges.add(new Edge(v1,v2,weight));
            }
        }
    }

    /**
     * Deletes an edge between two given vertices from the graph
     * @param v1 The first vertex of the edge
     * @param v2 The second vertex of the edge
     */
    public void deleteEdge(Vertex v1, Vertex v2){
        //if the edge exists in the graph
        if(ifEdgeExists(v1,v2) > -1 && vertices.contains(v1) && vertices.contains(v2)){

            v1 = vertices.get(vertices.indexOf(v1));
            v2 = vertices.get(vertices.indexOf(v2));

            Edge edgeV1V2 = new Edge(v1,v2);

            //removing edge from edges array
            for(int i = 0; i < edges.size(); i++){
                if(edges.get(i).equals(edgeV1V2)){
                    edges.remove(edges.get(i));
                    break;
                }
            }

            //removing v2 from the adjacency list of v1
            Vertex parent = v1;
            while(!parent.getNext().equals(v2)){
                parent = parent.getNext();
            }
            Vertex child = parent.getNext().getNext();
            parent.setNext(child);

            //removing v1 from the adjacency list of v2
            parent = v2;
            while(!parent.getNext().equals(v1)){
                parent = parent.getNext();
            }
            child = parent.getNext().getNext();
            parent.setNext(child);
        }
    }

    /**
     * Finds and returns an edge between two given vertices
     * @param v1 The first vertex of the edge
     * @param v2 The second vertex of the edge
     * @return The edge between the two given vertices, if the edge does not exist in the graph null is returned
     */
    public Edge findEdge(Vertex v1, Vertex v2){

        Edge edgeV1V2 = new Edge(v1,v2);

        for(int i = 0; i < edges.size(); i++){
            if(edges.get(i).equals(edgeV1V2)){
                return edges.get(i);
            }
        }
        return null;
    }

    /**
     * Traverses the graph using the Breadth-First Search algorithm
     * @param source The starting point of the traversal
     */
    public void traverseBFS(Vertex source){
        if(vertices.contains(source)){
            source = vertices.get(vertices.indexOf(source));
            //initializing all vertices, distance = -1 is being treated as distance = infinity
            for(int i = 0; i < vertices.size(); i++){
                vertices.get(i).setColor("white");
                vertices.get(i).setDistance(-1);
                vertices.get(i).setPredecessor(null);
            }
            //initializing source vertex
            source.setColor("gray");
            source.setDistance(0);
            source.setPredecessor(null);

            Queue<Vertex> queue = new LinkedList<Vertex>();
            queue.add(source);

            while(!queue.isEmpty()){
                Vertex u = queue.remove();

                //for each v in G.Adj[u]
                Vertex v = u.getNext();
                while(v != null){
                    if(vertices.get(vertices.indexOf(v)).getColor().equals("white")){
                        vertices.get(vertices.indexOf(v)).setColor("gray");
                        vertices.get(vertices.indexOf(v)).setDistance(u.getDistance() + 1);
                        vertices.get(vertices.indexOf(v)).setPredecessor(u);
                        queue.add(vertices.get(vertices.indexOf(v)));
                    }
                    v = v.getNext();
                }
                u.setColor("black");
            }
        }
    }

    /**
     * Traverses the graph using the Depth-First Search algorithm
     */
    public void traverseDFS(){
        //initializing all vertices, distance = -1 is being treated as distance = infinity
        for(int i = 0; i < vertices.size(); i++){
            vertices.get(i).setColor("white");
            vertices.get(i).setPredecessor(null);
        }

        time = 0;

        for(int i = 0; i < vertices.size(); i++){
            if(vertices.get(i).getColor().equals("white")){
                DFS_Visit(vertices.get(i));
            }
        }
    }

    /**
     * Recursive method used for the Depth-First Search algorithm
     * @param u Vertex that has already been discovered in the DFS algorithm
     */
    private void DFS_Visit(Vertex u){
        time++;
        u.setDiscovery(time);
        vertices.get(vertices.indexOf(u)).setColor("gray");

        //for each v in G.Adj[u]
        Vertex v = u.getNext();
        while(v != null){
            if((vertices.get(vertices.indexOf(v)).getColor().equals("white"))){
                v.setPredecessor(u);
                DFS_Visit(vertices.get(vertices.indexOf(v)));
            }
            else break;

        }

        u.setColor("black");
        time++;
        u.setFinish(time);
    }

    /**
     * Prints the value and color of each vertex in the graph. This is used to test traverseBFS and traverseDFS
     */
    public void printVertices(){
        String s;
        for(int i = 0; i < vertices.size(); i++){
            s = vertices.get(i).toString() + ": " + vertices.get(i).getColor();
            System.out.println(s);
        }
    }

    /**
     * Sets the color of every vertex in the graph to white. This is used to test traverseBFS and traverseDFS
     */
    public void setVerticesColorWhite(){
        for(int i = 0; i < vertices.size(); i++){
            vertices.get(i).setColor("white");
        }
    }
}
