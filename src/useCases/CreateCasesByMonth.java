package useCases;

import entities.BinarySearchTree;
import services.InputVar;

public class CreateCasesByMonth {
    private InputVar csvToArray = new InputVar();

    private String[] data;

    private BinarySearchTree treeData;

    public CreateCasesByMonth() {
        this.treeData = new BinarySearchTree(this::compareMonth);
        this.data = csvToArray.arrayData();
        treeData.insertAll(data);
    }

    public int compareMonth(String s1, String s2) {
        int month1 = csvToArray.getMesData(s1);
        int month2 = csvToArray.getMesData(s2);

        return Integer.compare(month1, month2);
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
