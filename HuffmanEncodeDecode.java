//package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A program to read an input file and create a corresponding output file, going from
 * “uncompressed” version to encode it to the “compressed” version.  Then taking the “compressed” version
 * and decoding it to create the “uncompressed” version.
 *
 * @author Jimmy Tai
 */

public class HuffmanEncodeDecode {

    public static void main(String[] args) throws Exception {

        System.out.print("Enter the relative path to file you would like to encode: ");
        Scanner scan = new Scanner(System.in);
        String filePath = scan.nextLine();
        // File path is passed as parameter
        File file = new File(filePath
                /*"/Users/micha/IteliProj/HuffManE/src/com/company/test"*/);


        // Creating an object of BufferedReader class
        BufferedReader br
                = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String st;
        st = br.readLine();

        //calculate frequency using map
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < st.length(); i++) {
            char c = st.charAt(i);
            Integer val = map.get(c);
            if (val != null) {
                map.put(c, new Integer(val + 1));
            } else {
                map.put(c, 1);
            }
        }

        //System.out.println(map);
        //This is used for our minheap
        MinHeap<HuffTree<Character>> oheap = new MinHeap<HuffTree<Character>>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            HuffTree<Character> t = new HuffTree(entry.getKey(), entry.getValue());
            oheap.insert(t);
        }

        /**
         * Building the huffman tree
         */
        buildTree(oheap);
        HuffTree<Character> bTree = oheap.removemin();
        HuffBaseNode<Character> rootNode = bTree.root();
        Map<Character, String> HuffHeap = new HashMap<Character, String>();
        String s = "";
        enCode(rootNode, s, HuffHeap);
        //print out the content in HuffHeap
        int count = 0;
        System.out.print("Character encodings:\n{");
        for (Map.Entry<Character, String> entry : HuffHeap.entrySet()) {
            if (count > 0) {
                System.out.print(", ");
            }
            System.out.print(entry.getKey() + "=" + entry.getValue());
            count++;
        }
        System.out.println("}");

        //System.out.println(HuffHeap);
        //will add to the string
        String str = "";
        for (int i = 0; i < st.length(); i++) {
            char c = st.charAt(i);
            str += HuffHeap.get(c);
        }

        //System.out.println(str);
        //writes to the the file of choice.
        System.out.print("\nEnter the relative path to the file you would the encoded text written to: ");
        Scanner name = new Scanner(System.in);
        String fileName = name.next();
        FileWriter myWriter = new FileWriter(fileName);
        myWriter.write(str);
        myWriter.close();

        //after decode the file
        System.out.print("\nEnter the relative path to the file you would like to decode: ");
        name = new Scanner(System.in);
        String filename = name.nextLine();
        File file0 = new File(filename);
        name = new Scanner(file0);

        String str0;
        str0 = name.nextLine();
        String str1 = deCode(str0, rootNode);
        name.close();

        System.out.println("Decoded contents:\n" + str1);

    }

    /**
     * Encode all characters and store them in a map
     *
     * @param root it is the root of each node
     * @param s a String used to store the path
     * @param m a Map that contains the characters and their values
     */
    static void enCode(HuffBaseNode root, String s, Map<Character, String> m) {
        if (root.isLeaf()) {
            m.put((Character) root.element(), s);
        } else {
            enCode(root.left(), s + "0", m);
            enCode(root.right(), s + "1", m);
        }
    }

    /**
     * Decode file and return
     * @param s a String that contains the path
     * @param root it is the root of each node
     * @return rst it will return the result that is a String
     */
    static String deCode(String s, HuffBaseNode root){
        if (root == null) {
            return "";
        }
        char[] arr = s.toCharArray();
        int index = 0;
        String rst = "";
        while (index < arr.length) {
            HuffBaseNode node = root;
            while (node != null) {
                if (node.left() == null && node.right() == null) {
                    rst += node.element();
                    break;//break inner while
                } else {
                    char c = arr[index];
                    if (c == '0') {
                        node = node.left();
                    } else {
                        node = node.right();
                    }
                    index++;
                }
            }
        }

        return rst;
    }

    /**
     * Build a Huffman tree from list hufflist
     *
     * @param h a MinHeap
     */
    static void buildTree(MinHeap<HuffTree<Character>> h) {
        HuffTree tmp1, tmp2, tmp3 = null;
        while (h.heapsize() > 1) { // While two items left
            tmp1 = h.removemin();
            tmp2 = h.removemin();
            tmp3 = new HuffTree<Character>(tmp1.root(), tmp2.root(), tmp1.weight() + tmp2.weight());
            h.insert(tmp3); // Return new tree to heap
        }
// Return the tree
    }

}
