package sort;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.StudyProgram;

public interface Sort {
	public ArrayList<StudyProgram> sortAscending(DefaultTableModel model);
	public ArrayList<StudyProgram> sortDescending(DefaultTableModel model);
}
