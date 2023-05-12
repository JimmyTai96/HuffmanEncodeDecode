//package com.company;

/**
 * @author Jimmy Tai
 * CS338
 * A Huffman coding tree
 */
public class HuffTree<E> implements Comparable<HuffTree<E>> {
    private final HuffBaseNode<E> root; // Root of the tree

    /**
     * Constructors
     */
    public HuffTree(E el, int wt) {
        root = new HuffLeafNode<E>(el, wt);
    }

    public HuffTree(HuffBaseNode<E> l, HuffBaseNode<E> r, int wt) {
        root = new HuffInternalNode<E>(l, r, wt);
    }

    public HuffBaseNode<E> root() {
        return root;
    }

    public int weight() // Weight of tree is weight of root
    {
        return root.weight();
    }

    /**
     *this is the compare method will be used in the huffman tree.
     *@param that
     */
    public int compareTo(HuffTree<E> that) {
        if (root.weight() < that.weight()) {
            return -1;
        } else if (root.weight() == that.weight()) {
            return 0;
        } else {
            return 1;
        }
    }

}