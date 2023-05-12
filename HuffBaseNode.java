//package com.company;

/**
 * @author Jimmy Tai
 * CS338
 * Huffman tree node implementation: Base class
 */
public interface HuffBaseNode<E> {
    public boolean isLeaf();
    public E element();
    public int weight();
    public HuffBaseNode<E> left();
    public HuffBaseNode<E> right();

}