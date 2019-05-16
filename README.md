# DataStructureProjects
This folder contains all programming assignments and projects that I did in SE3345  (Data Structures) class. 

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
