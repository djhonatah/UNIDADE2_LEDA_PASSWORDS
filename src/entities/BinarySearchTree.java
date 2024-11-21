package entities;



import interfaces.ITwoParamFunction;

import java.util.*;

class Node {

    public String data;
    public Node left;
    public Node right;

    public Node(String data) {
        this.data = data;
    }

}

public class BinarySearchTree {
    private Node root;

    ITwoParamFunction<String, String, Integer> comparaTo;

    public BinarySearchTree(ITwoParamFunction<String, String, Integer> comparaTo) {
        this.comparaTo = comparaTo;
        root = null;
    }

    public String[] inOrderAscending() {
        var current = root;
        ArrayList<String> result = new ArrayList<>();

        inOrderAscending(current, result);

        String[] finalArray = new String[result.size()];

        for (int x = 0; x < result.size(); x++) {
            finalArray[x] = result.get(x);
        }

        return finalArray;
    }

    private void inOrderAscending(Node node, List<String> result) {
        if (node == null)
            return;

        inOrderAscending(node.left, result);
        result.add(node.data);
        inOrderAscending(node.right, result);
    }

    public String[] inOrderDescending() {
        var current = root;
        List<String> result = new ArrayList<>();

        inOrderDescending(current, result);

        String[] finalArray = new String[result.size()];

        for (int x = 0; x < result.size(); x++) {
            finalArray[x] = result.get(x);
        }

        return finalArray;
    }

    private void inOrderDescending(Node node, List<String> result) {
        if (node == null)
            return;

        inOrderDescending(node.right, result);
        result.add(node.data);
        inOrderDescending(node.left, result);
    }


    public void insertAll(String[] data) {

        for (int x = 0; x < data.length; x++) {
            insert(data[x]);
        }

    }

    public void insert(String data) {
        root = insert(root, data);
    }


    private Node insert(Node node, String data) {
        if (node == null) {
            return new Node(data);
        }

        int comparisonResult = comparaTo.apply(data, node.data);

        if (comparisonResult > 0) {
            node.right = insert(node.right, data);
        } else {
            node.left = insert(node.left, data);
        }

        return node;
    }


}
