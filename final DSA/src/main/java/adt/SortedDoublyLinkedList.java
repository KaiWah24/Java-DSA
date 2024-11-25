/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

/**
 *
 * @author chinkhang,fungpin,kaiwah,mingking
 */
public class SortedDoublyLinkedList<T extends Comparable<T>> implements SortedLinkedListInterface<T> {

    private class Node {

        private T data;
        private Node previous;
        private Node next;

        private Node(T data) {
            this.data = data;
            next = null;
            previous = null;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public SortedDoublyLinkedList() {
        firstNode = null;
        numberOfEntries = 0;
    }
    private Node firstNode;
    private Node lastNode;
    private int numberOfEntries;

    @Override
    public boolean add(T newEntry) {
        Node currentNode = firstNode;
        Node nodeBefore = null;

        Node newNode = new Node(newEntry);

        while (currentNode != null && newEntry.compareTo(currentNode.data) > 0) {
            nodeBefore = currentNode;
            currentNode = currentNode.next;
        }

        if (nodeBefore == null) { // CASE 1: add at beginning

            newNode.next = firstNode;
            if (firstNode != null) {
                firstNode.previous = newNode;
            }
            firstNode = newNode; //first node == null then make the first node not null by setting it as the new entry


            if (lastNode == null){
                lastNode = newNode;
            }

        } else {    // CASE 2: add in the middle or at the end, i.e. after nodeBefore
            newNode.next = currentNode;
            newNode.previous = nodeBefore;
            nodeBefore.next = newNode;

            if(currentNode == null){ // if its last node
                lastNode = newNode;
            }


        }
        numberOfEntries++;
        return true;
    }


      @Override
    public T search(T target) {
        if (!isEmpty()) {
            Node currentNode = firstNode;

            while (currentNode != null && currentNode.data.compareTo(target) < 0) {
                currentNode = currentNode.next;
            }

            if (currentNode != null && currentNode.data.compareTo(target) == 0) {
                return currentNode.data;
            }
        }
        return null;
    }

    @Override
    public T search(int selectedIndex) {
        if (selectedIndex < 0) {
            return null; //invalid index
        }
        Node currentNode = firstNode;
        int currentIndex = 0;

        while (currentNode != null && currentIndex < selectedIndex){
            currentNode = currentNode.next;
            currentIndex++;
        }

        //check if the wanted index is reach
        if (currentIndex == selectedIndex && currentNode != null){
            return currentNode.data; //return the data
        }
        else
            return  null; // entry not found
    }

    @Override
    public T getEntry(int givenPosition){
        T result = null;
        Node currentNode = firstNode;

        if((givenPosition >=1) && (givenPosition<= numberOfEntries)){
            for (int i=0; i<givenPosition-1; i++){
                currentNode = currentNode.next;
            }
            result =currentNode.data;
        }
        return result;
    }

    public boolean remove(T anEntry) {
        if (firstNode == null) {
            return false; // No nodes to remove
        } else {
            Node currentNode = firstNode;
            Node beforeNode = null;
            // Traverse until find the node or pass the position it should be
            while (currentNode != null && currentNode.data.compareTo(anEntry) < 0) {
                beforeNode = currentNode;
                currentNode = currentNode.next;
            }

            if (currentNode != null && currentNode.data.compareTo(anEntry)== 0) {
                if (currentNode == firstNode) { // Remove first node
                    firstNode = firstNode.next; // Move first node to next node
                    if (firstNode != null) {
                        firstNode.previous = null; // Update previous of new first node
                    } else {
                        lastNode = null; // If list becomes empty, update lastNode as well
                    }
                } else if (currentNode == lastNode) { // Remove last node
                    lastNode = beforeNode; // Update lastNode to the previous node
                    lastNode.next = null; // Update next of new last node
                } else { // Middle node
                    beforeNode.next = currentNode.next; // Link previous node to next node
                    if (currentNode.next != null) {
                        currentNode.next.previous = beforeNode; // Link next node back to previous node
                    }
                }
                numberOfEntries--; // Decrement the count of entries
                return true; // Node was found and removed
            }
        }
        return false; // Node not found
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

   @Override
    public T get(int index) {
        if (index < 0 || index >= numberOfEntries) {
            return null;
        }
        Node currentNode = firstNode;
        for (int currentIndex = 0; currentIndex < index; currentIndex++) {
            currentNode = currentNode.next;
        }
        return (T) currentNode.data;
    }


    @Override
    public boolean isEmpty(){
        return numberOfEntries == 0;
    }


    public String toString() {
        String outputStr = "";
        Node currentNode = firstNode;
        while (currentNode != null) {
            outputStr += currentNode.data + "\n";;
            currentNode = currentNode.next;
        }
        return outputStr;
    }
    
    public T replace(int givenPosition, T newEntry){
        T result = null;

        if((givenPosition >= 1) && (givenPosition <= numberOfEntries-1)){
            Node currentNode = firstNode;
            for (int i=1; i<givenPosition-1; i++){
                currentNode = currentNode.next;
            }
                currentNode.data = newEntry;
                result = currentNode.data;
        }
        return result;
    }
    
    @Override
    public T getLast(){
        if(lastNode != null){
            return lastNode.data;
        }else{
            return null;
        }
    }
    
    @Override
    public void sort() {
        boolean swapped;
        do {
            Node currentNode = firstNode;
            swapped = false;
            while (currentNode != null && currentNode.next != null) {
                if (currentNode.data.compareTo(currentNode.next.data) > 0) {
                    T temp = currentNode.data;
                    currentNode.data = currentNode.next.data;
                    currentNode.next.data = temp;
                    swapped = true;
                }
                currentNode = currentNode.next;
            }
        } while (swapped);
    }
    
    @Override
    public void reverseSort() {
        boolean swapped;
        do {
            Node currentNode = firstNode;
            swapped = false;
            while (currentNode != null && currentNode.next != null) {
                if (currentNode.data.compareTo(currentNode.next.data) < 0) {
                    T temp = currentNode.data;
                    currentNode.data = currentNode.next.data;
                    currentNode.next.data = temp;
                    swapped = true;
                }
                currentNode = currentNode.next;
            }
        } while (swapped);
    }


    //Code from : https://www.geeksforgeeks.org/reverse-a-doubly-linked-list/
    //reverse sort by node
    public void reverse()
    {
        Node temp = null;
        Node current = firstNode;

        /* swap next and prev for all nodes of
         doubly linked list */
        while (current != null) {
            temp = current.previous;
            current.previous = current.next;
            current.next = temp;
            current = current.previous;
        }

        /* Before changing head, check for the cases like
         empty list and list with only one node */
        if (temp != null) {
            firstNode = temp.previous;
        }
    }
    
        public boolean contains(T anEntry) {
        boolean found = false;
        Node tempNode = firstNode;

        while (!found && (tempNode != null)) {
          if (anEntry.compareTo(tempNode.data) <= 0) {
            found = true;
          } else {
            tempNode = tempNode.next;
          }
        }
        if (tempNode != null && tempNode.data.equals(anEntry)) {
          return true;
        } else {
          return false;
        }
      }

    public final void clear() {
      firstNode = null;
      numberOfEntries = 0;
  }
    
    
}
