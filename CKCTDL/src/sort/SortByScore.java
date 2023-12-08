package sort;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.StudyProgram;
import view.ControllerView;

public class SortByScore implements Sort {
    private ControllerView controller;
    private DefaultTableModel tableModel;

    public SortByScore(ControllerView controller, DefaultTableModel model) {
        this.controller = controller;
        this.tableModel = model;
    }

    @Override
    public ArrayList<StudyProgram> sortAscending(DefaultTableModel model) {
        ArrayList<StudyProgram> programList = controller.getDataFromTable(model);
        mergeSort(programList, 0, programList.size() - 1, true);
        return programList;
    }

    @Override
    public ArrayList<StudyProgram> sortDescending(DefaultTableModel model) {
        ArrayList<StudyProgram> programList = controller.getDataFromTable(model);
        mergeSort(programList, 0, programList.size() - 1, false);
        return programList;
    }

    private void merge(ArrayList<StudyProgram> arr, int left, int mid, int right, boolean ascending) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        ArrayList<StudyProgram> leftArr = new ArrayList<>(arr.subList(left, left + n1));
        ArrayList<StudyProgram> rightArr = new ArrayList<>(arr.subList(mid + 1, mid + 1 + n2));

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            boolean comparison = ascending ?
                    leftArr.get(i).getAdmissionScore() <= rightArr.get(j).getAdmissionScore() :
                    leftArr.get(i).getAdmissionScore() >= rightArr.get(j).getAdmissionScore();

            if (comparison) {
                arr.set(k++, leftArr.get(i++));
            } else {
                arr.set(k++, rightArr.get(j++));
            }
        }

        while (i < n1) {
            arr.set(k++, leftArr.get(i++));
        }

        while (j < n2) {
            arr.set(k++, rightArr.get(j++));
        }
    }

    private void mergeSort(ArrayList<StudyProgram> arr, int left, int right, boolean ascending) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid, ascending);
            mergeSort(arr, mid + 1, right, ascending);

            merge(arr, left, mid, right, ascending);
        }
    }
}