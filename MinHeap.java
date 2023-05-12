//package com.company;

import java.util.*;
import java.math.*;
/** 
  *@author Jimmy Tai
  * CS338
  * Min-heap implementation 
  */
public class MinHeap<E extends Comparable<? super E>> {
  private ArrayList<E> heap;
  private int n;
  
// Pointer to the heap array
// Minimum size of the heap
// Number of things in heap
  /** Constructor supporting preloading of heap contents */
  public MinHeap(){
    heap = new ArrayList<E>();
    n = 0;
  }

  /**
   * Constructor
   * @param h it is a ArrayList
   */
  public MinHeap(ArrayList<E> h){
    n = h.size();
    heap = h;
    buildheap();
  }

  /**
   * swap values
   * @param pos1 it is an Integer
   * @param pos2 it is an Integer
   */
  public void swap(int pos1, int pos2){
    E temp = heap.get(pos1);
    heap.set(pos1, heap.get(pos2));
    heap.set(pos2,temp);
  }

  /**
   * covert the values in the heap to a String
   * @return
   */
  public String toString(){
    String str = "";
    for(int i = 0; i < heapsize(); i++){
      str += heap.get(i) + ", ";
    }
    return "[" + str.substring(0, str.length()-2) + "]";
    
  }

  /**
   * check is empty
   * @return
   */
  public boolean isEmpty(){
    return n == 0;
  }
  
  /** @return Current size of the heap */
  public int heapsize() {
    return n; }
  
  /** @return True if pos a leaf position, false otherwise */
  public boolean isLeaf(int pos){
    return (pos >= n/2) && (pos < n); }
  
  /** @return Position for left child of pos */
  public int leftchild(int pos) {
    assert pos < n/2 : "Position has no left child";
    return 2*pos + 1;
  }
  
  /** @return Position for right child of pos */
  public int rightchild(int pos) {
    assert pos < (n-1)/2 : "Position has no right child";
    return 2*pos + 2;
  }
  
  /** @return Position for parent */
  public int parent(int pos) {
    assert pos > 0 : "Position has no parent";
    return (pos-1)/2;
  }
  
  /** Insert val into heap */
  public void insert(E val) {
    heap.add(val);
    //"Heap is full";
    int curr = n++;
    heap.set(curr, val);             // Start at end of heap
    // Now sift up until curr’s parent’s key > curr’s key
    while ((curr != 0)  &&
           (heap.get(curr).compareTo(heap.get(parent(curr))) < 0)) {
      swap(curr, parent(curr));
      curr = parent(curr);
    } 
  }
  
  /** Heapify contents of Heap */
  public void buildheap()
  { for (int i=n/2-1; i>=0; i--) siftdown(i); }
  
  /** Put element in its correct place */
  private void siftdown(int pos) {
    assert (pos >= 0) && (pos < n) : "Illegal heap position";
    while (!isLeaf(pos)) {
      int j = leftchild(pos);
      if ((j<(n-1)) && (heap.get(j).compareTo(heap.get(j+1)) > 0))
        j++; // j is now index of child with less value
      if (heap.get(pos).compareTo(heap.get(j)) <= 0) return;
      swap(pos, j);
      pos = j;  // Move down
    } 
  }
  
  /** Remove and return minimum value */
  public E removemin() {
    assert n > 0 : "Removing from empty heap";
    swap(0, --n); // Swap minimum with last value
    if (n != 0)      // Not on last element
      siftdown(0);   // Put new heap root val in correct place
    return heap.get(n);
  }
  
  /** Remove and return element at specified position */
  public E remove(int pos) {
    assert (pos >= 0) && (pos < n) : "Illegal heap position";
    if (pos == (n-1)) n--; // Last element, no work to be done
    else
    {
      swap(pos, --n); // Swap with last value
      // If we just swapped in a big value, push it up
      while ((pos > 0) &&
             (heap.get(pos).compareTo(heap.get(parent(pos))) < 0)) {
        swap(pos, parent(pos));
        pos = parent(pos);
      }
      if (n != 0) siftdown(pos); // If it is little, push down
    }
    return heap.get(n);
  }
  
}