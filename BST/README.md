BinarySearchTree.java contains three classes:
a. BinarySearchTree that implements Iterable
b. static Entry class
c. BSTIterator class that implements Iterator

BinarySearchTree contains entry object which has element, left entry and right entry. Binarysearchtree also contains the stack of ancestors that is filled in when with entires when an element x is searched in the tree. 
This makes our algorithm efficient and one pass as we are not storing the parent of the entry. 

BinarySearchTree class has following methods :
a. contains (T x) : Finds if the entry that contains the element is in the tree and see if the element is the same. return true if it is the same and false otherwise.

b. find (T x): finds the element from the root and maintains the stack of ancestors.

c. helper method of find - find(Entry t, T x): If the value of x is smaller then value of entry t's element then search for the item in the left sub-tree.Search for the x until there is no further element left in the left subtree and break from the while loop if null is reached.
if the value of x is greater than the value of entry t's element then search for the item in the right sub-tree. Search until the there are no further element in the right sub tree and break from the while loop if null is reached. At the end of the while loop, return the entry t. Entry t is the entry where the x was found or where the search ended and x was not found.

d. get (T x): Search for the x in the tree. if x is found then return element of entry where the x was founded. If find returns the entry where the search ended but x was not found then return the null value.

e. add (T x): Adds element to the binary search tree. If root is null then add element to the root of the tree else find the item to be added in the binary search tree. if the item is found then duplicate elements can not be added to the tree. if x is smaller than the element in entry returned by find then add new element to the left. if x is greater than the element in entry returned by find then add new element to the right.

f. remove (T x): Remove the element from the list. To remove the element from the list first the entry where the element is at.If the entry is not found then return null. If the entry is found then check the number of children of that entry. If there is just one children then bypass that entry. If there are two children then find the successor of the element and bypass the successor.

g. bypass (Entry<T> t): Helper method to bypass the element.

h. min (): calls the helper method to find the minimum element of the tree.

i. min (Entry<T lEntry ): Helper method to find the minimum element in the tree. Goto the leftmost element in the left sub-tree and return the element.

j. max (): calls the helper method to find the maximum element of the tree.

k. max (Entry<T> rEntry): Helper method to find the maximum element in the tree. Goto the rightmost element in the right subtree and return the element.

l. toArray(): Converts the BST to an array. Uses the helper method : extractValues.

m. extractValues( Entry<T> entry, Comparable[] arr, int index ): Helper method to extract values from the bst and put the values in array by doing in-order traversal. A stack is created to store the elements in the BST and elements are visited in the level-order. current is the entry field. first if the root is not null push the root to the stack and keep pushing element to the list unless the leftmost element is found and make the element current on every left move. pop the current entry and add its value to the stack.

n. floor (T x): Find the floor of the element x. Floor is the largest key in the tree that is no bigger than x. Return null if there is no such key.
find the element x in the tree. find returns the entry where the element was found or the entry where search ended. ancestors has the path taken by
find method. if the element of the founded entry is bigger than x then keep popping ancestor from the stack of ancestors.
Check if the popped ancestor's element is smaller than x and return if it is smaller than x else keep popping until the end of the stack is reached.
if no such element is found in the stack of ancestors then return null.
if entry returned by find has the element bigger than x or equals to x then just return that entry's element.

o. ceiling (T x): Find the ceiling of the element x. Ceiling is the smallest key that is no smaller than x. Return null if there is no such key. Find the element x in the tree. find returns the entry where the x was found or where the search ended. ancestors has the path taken by find method. if the element of the founded entry is smaller than x then keep popping elements from the ancestors stack. if the popped entry has the element bigger than x then return that value else keep popping and checking until the null (root's parent) entry is reached. if such value is not found then return the null. else if the element of the founded entry is bigger or equal to x then return the element of that entry.

p. predecessor(T x): Return the maximum element in the left subtree of the entry that contained x or null if such thing doesn't exist.
if x is not in the tree then floor is returned. find the element in the tree. if the element is found then goto its left entry and traverse right every time until the rightmost entry is not found. if such entry is found then return its element else return null. if the element is not found in the tree then return floor.

q. successor(T x): Return the minimum element in the right subtree of the entry that contained x or null if such thing doesn't exist. If x is not in the tree then ceiling of x is returned. find the element in the tree. if the element is found then goto its right entry and traverse left every time until the leftmost entry is not found. if such entry is found then return its element else return null. if the element is not found in the tree then return ceiling.

BSTIterator class has following methods : 
a. hasNext(): that returns if the stack of ancestors is not empty. 

b. next(): Returns the next element in the binary search tree. ( traversal is in-order). if the top of the ancestor's stack (current) has right sub-tree then pop that entry and go to its leftmost entry if it exists. if the left entry doesn't exists then return the value of its right entry else return the value of its leftmost entry.

constructor of BSTIterator pushes every left entry to the stack of ancestors until the leftmost entry is reached. 


