# DataStructureProjects
This folder contains all programming assignments and projects that I did in SE3345 (Data Structures)class in the Fall of 2018. I gained a good amount of knowledge about different data structures in programming languages from this class, including but not limited to List, Hashing, Tree, Graph, and so on. 

## SinglyLinkedList.java and DoublyLinkedList.java: 

Doublylinked list class extends the singlylinked list class and also implements its own dll iterator interface.

Singlylinked list class has been modified to include several new methods such as :
a.get(index), set(index, x), add(index, x), remove(index)


## BoundedQueue.java : 
BoundedQueue allows adding element to the end of the queue and removing from the front. Both front and end of the queue wrap around back to index 0 when they reach the end of the array. BoundedQueue has following publicly available methods :
  a. BoundedQueue(int size)
  b. boolean offer(T x)
  c. T poll()        
  d. T peek()         
  e. int size()          
  f. boolean isEmpty()   
  g. void clear()    
  h. void toArray(T[] a)
  
## IA2.java: 
IA2 class has publicly available methods : intersect, union and difference that takes sorted list as parameter and executes the method accordingly. Intersect method finds common elements in both lists and enters them into the result list. Union lists all the elements in the list removing the duplicates. Difference finds the elements that are present in first list but not in the second list. 

## IA31.java : TreeMap Implemenatation
IA31 takes an input number from the user and returns the total number of elements in the list that sums to the given number. This is to show one of the useful cases of TreeMap implementation. 
IA31 contains two public methods (including main) and one private helper method. Public Method:
a. howMany: takes an integer array and an int as parameters. The program calculates how many pair of elements from the array sums up to form the integer that is passed as parameter. This method returns the total pair of elements as an integer data type.
Private Method:
a. copies: takes integer array as a parameter. The program creates TreeMap where key is
the element of the given array and value is the number of occurrences of that specific element. This method returns TreeMap data type.

## IA6_3.java : HashSet Implementation
IA6_3 java contains method called longestStreak.
The methodalculates the length of longest streak of consecutive integers that occur in A
Adds the elements in the array to set. For element in the set checks if the next element is an increment of 1
compared to the previous element, if it is then increment the counter of consecutive
element found (current). Keep track of the maximum consecutive elements found in the separate variable called
max. if the current length is larger than max then replace
max with current. Max is the length of longest streak of consecutive integers.
@param A array of an integer

## IA7 : Comparison of PriorityQueue and Java's Array.sort() to find the largest element in the list.
IA7.java contains two methods : select(int[] arr, int k) and sortandSelect(int[] arr, int k).
Based on the run-time from the Timer class select method is efficient than sortandSelect method.

## IA8: Insertion Sort vs Merge Sort Comaprison
		
## IA9 : Implementaiton of the diameter of a tree. ( Graph class is not included in this repo because it was Professor's code)

Methods of IA9 class:
a.diameter(g : Graph, s: int) : int
Finds the maximum for the source node. Sets the node at maximum distance to to be a new source and runs the bfs algorithm from the
new source to find the new maximum distance from there. Diameter is of a tree is the maximum distance of any node from the second source.
Uses the helper method getFarthest_vertex to find the vertex that is farthest from the source node.
@param g : Graph on which the algorithm is run.
@param s : initial source node.
@return : diameter of the tree (g).


b. getFarthest_vertex(bfsoo : BFSOO, graph : Graph) : Vertex
Helper method to find the vertex that is farthest from the source.
Also updates the maximum distance between two vertices
@param bfsoo : object to call bfsoo method getdistance().
@param graph : graph on which bfsoo is run.
@return : vertex that is farthest from the source node.



