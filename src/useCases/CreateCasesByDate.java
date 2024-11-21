package useCases;

import entities.BinarySearchTree;
import services.InputVar;

import java.text.*;
import java.util.Date;

public class CreateCasesByDate {
    private InputVar csvToArray = new InputVar();

    private String[] data;
    private BinarySearchTree treeData;

    public CreateCasesByDate() {
        this.treeData = new BinarySearchTree(this::compareDates);
        this.data = csvToArray.arrayData();
        treeData.insertAll(data);
    }

    public int compareDates(String s1, String s2) {
        Integer dataInt1 = csvToArray.getDataCompleta(s1);
        Integer dataInt2 = csvToArray.getDataCompleta(s2);

        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");

        try {
            Date data1 = formato.parse(dataInt1.toString());
            Date data2 = formato.parse(dataInt2.toString());

            return data1.compareTo(data2);
        } catch(Exception error) {
            error.printStackTrace();
            throw new Error("Linha no formato invalido");
        }

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
