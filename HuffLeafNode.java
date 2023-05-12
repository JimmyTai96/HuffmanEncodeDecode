//package com.company;

/**
 * @author Jimmy Tai
 * CS338
 * Huffman tree node: Leaf class
 */
public class HuffLeafNode<E> implements HuffBaseNode<E> {
    private final E element; // Element for this node
    private final int weight; // Weight for this node

    /**
     * Constructor
     */
    public HuffLeafNode(E el, int wt) {
        element = el;
        weight = wt;
    }

    /**
     * @return The element value
     */
    public E element() {
        return element;
    }

    /**
     * @return The weight
     */
    public int weight() {
        return weight;
    }

    @Override
    public HuffBaseNode<E> left() {
        return null;
    }

    @Override
    public HuffBaseNode<E> right() {
        return null;
    }

    /**
     * Check if the current position is a leaf node.
     * Return true
     */
    public boolean isLeaf() {
        return true;
    }
}
