SkipList creates a sorted list where entries are connected to each other in multiple level. 

SkipList has two helper methods :

a.find	: Helper method find to set the last array as the entry where search ended.Starting from the head entry at current maximum level to level 0 iterate to next entry if the current entry is not tail or the element that is being searched for is greater than element at the current entry. Set the last array to the entry where both condition fails. Level down once reached the tail and again repeat the procedure. In the end, last array contains the entry where the search ended and last[0].next[0] is the element being searched for if it exists on the list.

b.chooseLevel : Helper method. Randomly choose a level for the new entry. Probability of choosing level i = 1/2 * Probability of choosing level (i-1). 

SkipList provides functions such as add, remove, contains, get, first, last, ceiling, floor, isEmpty as well as iterator that goes through element in the list in the sorted order.
The iterator has next and hasNext method.

Add : Adds element x to the list. Checks if the list contains the element x. If true, it rejects the value. Else, a random level is chosen for the new entry and a entry is created. For every level from the level 0 to the current level connect all the levels in the last to current entry and all the levels in the current entry to the last.next entries. The skiplist has prev field so similarly connect backward at the bottom level (only) to maintain the prev field. Increase the size per addition.

Remove:  Remove element from the list if it exists in the list. If the list doesn't contain that element x then return null. If the element is present in the list then bypass that element and connect all levels of previous entry to the next entry.
    
Contains:  Contains method checks if the element is in the list or not. Calls the helper method find to set the last array. Checks if the element is not null and is equal to the element being compared against.
     
Get: Get the element at index n. If the n is smaller than zero or greater than the size of the list then throws NSE exception. Element next to the head is considered to be at index 0. Starting from there search for the element until the required index. The element of the entry where the iteration stops is the element at index n.

First: return the first element in the list i.e next to the head entry.

Last: return the element before the tail of the list. tail is the dummy entry.

Ceiling: Returns the smallest element that is bigger than or equal the element x. Calls the helper method find. If the find ends at the last entry then there is no ceiling. Else, the element next to the entry where search ended is the ceiling of x. It could be x itself or the element greater than x. No need to check if the element is greater or equal to x as the find is designed to end where the current element is greater than x.
   
Floor: Returns the largest element that is less than or equal to x. Calls the helper method find. If the element next to where the search ended is equal to x then return that element else return where the search ended.
  
isEmpty:  returns if the size of the list is zero? size is increased during addition and decreased during remove.
     
