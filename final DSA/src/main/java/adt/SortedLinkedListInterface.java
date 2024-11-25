/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

/**
 *
 * @author chink
 */
public interface SortedLinkedListInterface<T extends Comparable<T>> {

    public boolean add(T newEntry);

    public T search(T anEntry);

    public T search(int selectedIndex);


    public boolean remove(T anEntry);//remove


    public int getNumberOfEntries();

    public T getEntry(int index);

    public boolean isEmpty();
    
    public T get(int index);
    public T replace(int givenPosition, T newEntry);
    public T getLast();
    
    public void sort();
    public void reverseSort();
    public void reverse();
    public boolean contains(T anEntry);

    public void clear();
}