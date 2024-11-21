package useCases;

import entities.BinarySearchTree;
import services.InputVar;

public class CreateCasesByLength {

    public InputVar csvToArray = new InputVar();

    public String[] data;
    public BinarySearchTree treeData;

    public CreateCasesByLength() {
        this.treeData = new BinarySearchTree(this::compareLength);
        this.data = csvToArray.arrayData();
        treeData.insertAll(data);
    }

    public int compareLength(String s1, String s2) {
        int length1 = csvToArray.getTamanhoSenha(s1);
        int length2 = csvToArray.getMesData(s2);

        return Integer.compare(length1, length2);
    }

    public String[] bestCase() {
        return treeData.inOrderAscending();
    }
    public String[] mediumCase() {
        return data;
    }
    public String[] worstCase() {
        return treeData.inOrderDescending();
    }

}
