## PERT : Program Evaluation and Review Technique implemenatiton using Java.

The pert algorithm finds out what is the fastest time in which a project can be completed, given infinite resources. 
Here, the vertices of the graph are tasks and the precedence contraints = directed edges. 

enum Color denotes the color of the vertex. A vertex has three color based on the action. 
White means the vertex has not been visited yet.
Grey means that the vertex has been discovered.
Black means that the vertex has been visited.

PERTVertex is the inner class that stores the attributes of a task such as its color, precedent task (parent), duration of the task, earliest completion time of the task, latest completion time of the task, discovery time, finished time and topological order of the task.

Methods of PERT classes are :

PERT pert(Graph g, int[] duration) : creates a pert instance on g, runs the algorithm. return PERT instance if successfull, else returns null if G is not a DAG.

Setters and getters for setting the vertex properites.

pert(): boolean
    Set the critical path length to zero.
    If the graph is directed then proceed to perform the pert algorithm.
    Call depth first search on graph g. Initialize the earliest completion for every vertex to be the duration of the vertex (task) itself.
    For all the vertices in the topological order if the earliest completion time of the next task is smaller than est of current task + duration of next task
    then update the est of the next task.
    Update the critical path length accordingly.
    To calculate the latest completion time, initialize the latest completion time to be critical path length of the tasks.
    Reverse the topological ordering and check if the latest completion time of current task is greater than lct of the previous task - duration of previous task.
    If true, update lc accordingly.
    @return : True on Success, False on Failure.
    @throws Exception
     
topologicalOrder() : LinkedList<Vertex>
     Find the topological ordering using DFS algorithm.
     If the graph is acyclic then return the linkedlist containing the list of vertices in the topological ordering else
     throws exception.
     @return : LinkedList with topologically ordered vertices.
     @throws Exception

initialize(g : Graph) : void
  	Initializing the color of a vertex in the graph g to be white and its parent to be null.
  	@param g : Graph.

dfs(g: Graph) : void
	Initialize the vertices of the graph to be of color white and their parents to null using helper method initialize.
	For every vertex in graph if the color is white then call helper function dfsVisit for visiting the vertex.
	@param g : Graph.

dfsVisit(u: Vertex ) : void
    LI : node's color is white.
    Set the color of the node to be grey to indicate that the node has been discovered. Similarly, increase the discovery time.
    For every vertex coming out of the vertex u, if their color is white then set their parent to be u and call visit them.
    If they are already discovered then the graph is acyclic.
    Set the finish time accordingly and change the color to black to indicate that the node has been visited.
    Similarly, set the topological number of the vertex and update the number for the next vertex.
    Add the vertex to the finishList linked list.
    @param u : vertex to be visited.

ec(u: Vertex): int 
	returns the earliest completion time of the task u.

lc(u: Vertex): int
	returns the latest completion time of the task u.

slack(u: Vertex) : int
	returns the slack of the task u i.e lc(u) - ec(u).

criticalPath(): int  
	returns length of a critical path (time taken to complete project)  

critical(u: Vertex): boolean
	retuns true if the slack of the given task is zero else false.

numCritical(): int
	calculates and returns the number of critical vertices i.e whose slack is zero.

### Note : Graph class included in PERT.java is not included in this repo because it was Professor's Code.
