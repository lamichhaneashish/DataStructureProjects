package axl173530;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import rbk.Graph;
import rbk.Graph.*;

/**
 * @author: Ashish Lamichhane
 * @version: 1.0
 * @Date: 11/25/18
 * @projectname: IA9
 */

public class IA9 {
    /**
     * Finds the maximum for the source node. Sets the node at maximum distance to to be a new source and runs the bfs algorithm from the
     * new source to find the new maximum distance from there. Diameter is of a tree is the maximum distance of any node from the second source.
     * Uses the helper method getFarthest_vertex to find the vertex that is farthest from the source node.
     * @param g : Graph on which the algorithm is run.
     * @param s : initial source node.
     * @return : diameter of the tree (g).
     */
    int diameter(Graph g, int s) {
        BFSOO b = BFSOO.breadthFirstSearch(g,s);
        BFSOO b1 = BFSOO.breadthFirstSearch(g,getFarthest_vertex(b,g));
        return b1.getDistance(getFarthest_vertex(b1,g));
    }

    /**
     * Helper method to find the vertex that is farthest from the source.
     * Also updates the maximum distance between two vertices
     * @param bfsoo : object to call bfsoo method getdistance().
     * @param graph : graph on which bfsoo is run.
     * @return : vertex that is farthest from the source node.
     */
    Vertex getFarthest_vertex(BFSOO bfsoo, Graph graph){
        int maxDistance = Integer.MIN_VALUE;// maximum distance between source and a vertex u i.e d(s,u).
        Vertex f= null; // stores the vertex that is farthest from the initial source node.
        int dist; // distance of a node from the source.
        for (Vertex u : graph){
            dist = bfsoo.getDistance(u);
            if (dist >= maxDistance){ // finding maximum distance from the source vertex. updating the vertex at the same time.
                maxDistance = dist;
                f = u;
            }
        }
        return f;
    }

    public static  void  main(String[] args) throws  Exception{ // assume that g is an acyclic, connected graph (tree).
        IA9 test = new IA9() ;
        String string = "12 22   1 2 2   2 1 2  1 3 3  3 1 3   1 4 5  4 1 5   5 1 1  1 5 1  2 6 2  6 2 2  2 7 5  7 2 5   8 4 2  4 8 2  9 5 1  5 9 1  10 5 1  5 10 1  11 7 2   7 11 2  12 7 2  7 12 2  4";
        Scanner in;
        // If there is a command line argument, use it as file from which
        // input is read, otherwise use input from string.
        in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(string);
        // Read graph from input
        Graph g = Graph.readDirectedGraph(in);
        int s = in.nextInt();
        int d = test.diameter(g,s);
        System.out.println("Diameter is : "+ d) ;


    }
}
