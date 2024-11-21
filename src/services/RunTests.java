package services;

import drivers.*;
import entities.SortAlgorithm;
import useCases.CreateCasesByDate;
import useCases.CreateCasesByLength;
import useCases.CreateCasesByMonth;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.Supplier;

public class RunTests {
    private final HashMap<String, SortAlgorithm> algorithms = new LinkedHashMap<>();
    private final HashMap<String, HashMap<String, Supplier<String[]>>> cases = new LinkedHashMap<>();

    public void setCases() {
        var byLength = new CreateCasesByLength();
        var byDate = new CreateCasesByDate();
        var byMonth = new CreateCasesByMonth();

        cases.put("Tamanho da Senha", new HashMap<>());
        cases.put("Mês", new HashMap<>());
        cases.put("Data", new HashMap<>());

        cases.get("Tamanho da Senha").put("melhor caso", byLength::bestCase);
        cases.get("Tamanho da Senha").put("médio caso", byLength::mediumCase);
        cases.get("Tamanho da Senha").put("pior caso", byLength::worstCase);

        cases.get("Mês").put("melhor caso", byMonth::bestCase);
        cases.get("Mês").put("médio caso", byMonth::mediumCase);
        cases.get("Mês").put("pior caso", byMonth::worstCase);

        cases.get("Data").put("melhor caso", byDate::bestCase);
        cases.get("Data").put("médio caso", byDate::mediumCase);
        cases.get("Data").put("pior caso", byDate::worstCase);
    }

    private void setAlgorithms() {
        algorithms.put("SelectionSort", new SelectionSort());
        algorithms.put("InsertionSort", new InsertionSort());
        algorithms.put("MergeSort", new MergeSort());
        algorithms.put("QuickSort", new QuickSort());
        algorithms.put("QuickSort com Mediana 3", new QuickSortMedianaTres());
        algorithms.put("HeapSort", new HeapSort());
        algorithms.put("CountingSort", new CountingSort());
    }

    private void showResults() {

        for (var entry : cases.entrySet()) {
            String tipoOrdenacao = entry.getKey();
            HashMap<String, Supplier<String[]>> tipoCasos = entry.getValue();

            System.out.printf("-----------Analise a partir do [%s] -----------\n", tipoOrdenacao);

            for (var entry2 : algorithms.entrySet()) {
                String nameAlgorithm = entry2.getKey();
                SortAlgorithm algorithm = entry2.getValue();

                System.out.printf("--------- Para o Algoritmo [%s]-------------\n\n", nameAlgorithm);

                for (var entry3 : tipoCasos.entrySet()) {
                    String caseName = entry3.getKey();
                    String[] caseArray = entry3.getValue().get();

                    algorithm.toggleSort(caseArray, caseName, tipoOrdenacao);
                }

                System.out.println();
            }

            System.out.println();
            System.out.println();
        }


    }

    public void run() {
        setAlgorithms();
        setCases();
        showResults();
    }
}
