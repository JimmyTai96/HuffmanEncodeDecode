//package com.company;

/**
 * @author Jimmy Tai
 * CS338
 * Huffman tree node: Internal class
 */
public class HuffInternalNode<E> implements HuffBaseNode<E> {
    private final int weight; // Weight (sum of children)
    private final HuffBaseNode<E> left; // Pointer to left child
    private final HuffBaseNode<E> right; // Pointer to right child
    private E element;

    /**
     * Constructor
     */
    public HuffInternalNode(HuffBaseNode<E> l, HuffBaseNode<E> r, int wt) {
        left = l;
        right = r;
        weight = wt;
    }

    /**
     * @return The left child
     */
    public HuffBaseNode<E> left() {
        return left;
    }

    /**
     * @return The right child
     */
    public HuffBaseNode<E> right() {
        return right;
    }

    /**
     * @return The weight
     */
    public int weight() {
        return weight;
    }

    /**
     * Checking if the current pos is a leaf
     * Return false
     */
    public boolean isLeaf() {
        return false;
    }

    //returning the element at the position
    @Override
    public E element() {
        return element;
    }
}
