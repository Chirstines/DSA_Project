package view;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import file.ReadFile;
import model.SMModel;
import model.StudyProgram;
import model.University;
import search.Search;
import sort.Sort;
import sort.SortByScore;
import sort.SortByTuition;

public class ControllerView {
	
	SMView view;
	SMModel model;
	ReadFile readFile;
	file.SaveFile saveFile;
	Search search;
	SortByScore sortByScore;
	SortByTuition sortByTuition;
	DefaultTableModel modelTable;
	public ControllerView(SMView view) {
		this.view = view;
		modelTable = (DefaultTableModel) view.table.getModel();
		model = new SMModel();
		readFile = new ReadFile();
		saveFile = new file.SaveFile();
		sortByScore = new SortByScore(this, modelTable);
		sortByTuition = new SortByTuition(this, modelTable);
		search = new Search(this, model, view);
	}
	
	public ArrayList<StudyProgram> getDataFromTable(DefaultTableModel model) {
	    ArrayList<StudyProgram> studyProgramList = new ArrayList<StudyProgram>();
	    for (int i = 0; i < model.getRowCount(); i++) {
	        String programCode = model.getValueAt(i, 0) + "";
	        String programName = model.getValueAt(i, 1) + "";
	        double admissionScore = Double.valueOf(model.getValueAt(i, 2) + "");
	        String universityCode = model.getValueAt(i, 3) + "";
	        String universityName = model.getValueAt(i, 4) + "";
	        String universityAddress = model.getValueAt(i, 5) + "";
	        University university = new University(universityCode, universityName, universityAddress);
	        long tuitionFee = Long.valueOf((String) model.getValueAt(i, 6));
	        StudyProgram studyProgram = new StudyProgram(programCode, programName, university, tuitionFee, admissionScore);
	        studyProgramList.add(studyProgram);
	    }
	    return studyProgramList;
	}

	public void addDataToTable(ArrayList<StudyProgram> studyProgramList) {
	    for (int i = 0; i < studyProgramList.size(); i++) {
	        modelTable.addRow(new Object[] {
	                studyProgramList.get(i).getProgramCode(),
	                studyProgramList.get(i).getProgramName(),
	                studyProgramList.get(i).getAdmissionScore() + "",
	                studyProgramList.get(i).getUniversity().getUniversityCode() + "",
	                studyProgramList.get(i).getUniversity().getUniversityName() + "",
	                studyProgramList.get(i).getUniversity().getAddress() + "",
	                studyProgramList.get(i).getTuitionFee() + ""});
	    }
	}
	public void addDataToTable(HashMap<String, StudyProgram> existingProgramsMap) {
	    for (Map.Entry<String, StudyProgram> entry : existingProgramsMap.entrySet()) {
	        StudyProgram program = entry.getValue();
	        modelTable.addRow(new Object[] {
	            program.getProgramCode(),
	            program.getProgramName(),
	            program.getAdmissionScore() + "",
	            program.getUniversity().getUniversityCode() + "",
	            program.getUniversity().getUniversityName() + "",
	            program.getUniversity().getAddress() + "",
	            program.getTuitionFee() + ""
	        });
	    }
	}

	public StudyProgram getSelectedProgram() {
	    int rowIndex = view.table.getSelectedRow();

	    String programCode = modelTable.getValueAt(rowIndex, 0) + "";
	    String programName = modelTable.getValueAt(rowIndex, 1) + "";
	    double admissionScore = Double.valueOf(modelTable.getValueAt(rowIndex, 2) + "");
	    String universityCode = University.getUniversityByCode(modelTable.getValueAt(rowIndex, 3) + "");
	    String universityName = University.getUniversityByName(modelTable.getValueAt(rowIndex, 4) + "");
	    String universityAddress = University.getUniversityByAddress(modelTable.getValueAt(rowIndex, 5) + "");
	    long tuitionFee = Long.valueOf((String) modelTable.getValueAt(rowIndex, 6));
	    University university = new University(universityCode, universityName, universityAddress);
	    StudyProgram program = new StudyProgram(programCode, programName, university, tuitionFee, admissionScore);
	    return program;
	}

	public void displayProgram() {
	    StudyProgram program = getSelectedProgram();

	    view.textField_ProgramCode.setText(program.getProgramCode());
	    view.textField_ProgramName.setText(program.getProgramName());
	    view.textField_AdmissionScore.setText(program.getAdmissionScore() + "");
	    view.textField_UniversityCode.setText(program.getUniversity().getUniversityCode());
	    view.textField_UniversityName.setText(program.getUniversity().getUniversityName() + "");
	    view.textField_Address.setText(program.getUniversity().getAddress() + "");
	    view.textField_TuitionFee.setText(program.getTuitionFee() + "");
	}

	public void deleteTableRow() {
	    int indexRow = view.table.getSelectedRow();
	    int select = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn xóa không?");
	    if (select == JOptionPane.YES_OPTION) {
	        StudyProgram program = getSelectedProgram();
	        model.delete(program);
	        modelTable.removeRow(indexRow);
	        if (!model.checkExistsUniversity(program)) {
	            view.comboBox_SelectedUniversitySearch.removeItem(program.getUniversity().getUniversityName());
	        }
	    }
	}

	public void sortByScore() {
	    String[] options = {"Sắp xếp tăng dần", "Sắp xếp giảm dần"};
	    int result = JOptionPane.showOptionDialog(
	            null,
	            "Bạn có chắc chắn muốn sắp xếp",
	            "Xác nhận sắp xếp",
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE,
	            null,
	            options,
	            options[0]
	    );
	    if (result == JOptionPane.YES_OPTION) {
	        ArrayList<StudyProgram> sortedList = sortByScore.sortAscending(modelTable);
	        modelTable.setRowCount(0);
	        addDataToTable(sortedList);
	    } else if (result == JOptionPane.NO_OPTION) {
	        ArrayList<StudyProgram> sortedList = sortByScore.sortDescending(modelTable);
	        modelTable.setRowCount(0);
	        addDataToTable(sortedList);
	    }
	}

	public void sortByTuition() {
		String[] options = {"Sắp xếp tăng dần", "Sắp xếp giảm dần"};
		int result = JOptionPane.showOptionDialog(
                null,
                "Bạn có chắc chắn muốn sắp xếp?",
                "Xác nhận sắp xếp",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
		if (result == JOptionPane.YES_OPTION) {
	        ArrayList<StudyProgram> sortedList = sortByTuition.sortAscending(modelTable);
	        modelTable.setRowCount(0);
	        addDataToTable(sortedList);
	    } else if (result == JOptionPane.NO_OPTION) {
	        ArrayList<StudyProgram> sortedList = sortByTuition.sortDescending(modelTable);
	        modelTable.setRowCount(0);
	        addDataToTable(sortedList);
	    }
	}
	public void clearFields() {
		view.textField_ProgramCode.setText("");
	    view.textField_ProgramName.setText("");
	    view.textField_AdmissionScore.setText("");
	    view.textField_UniversityCode.setText("");
	    view.textField_UniversityName.setText("");
	    view.textField_Address.setText("");
	    view.textField_TuitionFee.setText("");
	}

	public void addToTable() {
		String programCode = view.textField_ProgramCode.getText() + "";
		String programName = view.textField_ProgramName.getText();
		String universityCode = view.textField_UniversityCode.getText();
		String universityName = view.textField_UniversityName.getText();
		String address = view.textField_Address.getText();
		University university = new University(universityCode, universityName, address);
		long tuitionFee = Long.valueOf(view.textField_TuitionFee.getText());
		double admissionScore = Double.valueOf(view.textField_AdmissionScore.getText());
		StudyProgram studyProgram = new StudyProgram(programCode, programName, university, tuitionFee, admissionScore);

		if (!model.getUniversityNames().containsKey(universityName)) {
		    view.comboBox_SelectedUniversitySearch.addItem(universityName);
		}
		addOrUpdateToTable(studyProgram, this.model.getExistingSubject(studyProgram));
		for(int i = 0; i < this.model.getProgramList().size(); i++) {
			System.out.println(this.model.getProgramList().get(i).getProgramCode());
		}
	}
	public void addOrUpdateToTable(StudyProgram program, StudyProgram existingProgram) {
	    if (!this.model.checkExists(program)) {
	        this.model.insert(program);
	        this.model.insertUniversityMap(program);
	        modelTable.addRow(new Object[]{
	        		program.getProgramCode(),
	        		program.getProgramName(),
	        		program.getAdmissionScore() + "",
	        		program.getUniversity().getUniversityCode() + "",
	        		program.getUniversity().getUniversityName() + "",
	                program.getUniversity().getAddress() + "",
	                program.getTuitionFee() + ""
	        });
	    } else {
	        this.model.delete(existingProgram);
	        this.model.insert(program);
	        int rowCount = modelTable.getRowCount();
	        for (int i = 0; i < rowCount; i++) {
	            String programCode = modelTable.getValueAt(i, 0) + "";
	            if (programCode.equals(program.getProgramCode() + "")) {
	                modelTable.setValueAt(program.getProgramCode() + "", i, 0);
	                modelTable.setValueAt(program.getProgramName() + "", i, 1);
	                modelTable.setValueAt(program.getAdmissionScore() + "", i, 2);
	                modelTable.setValueAt(program.getUniversity().getUniversityCode() + "", i, 3);
	                modelTable.setValueAt(program.getUniversity().getUniversityName() + "", i, 4);
	                modelTable.setValueAt(program.getUniversity().getAddress() + "", i, 5);
	                modelTable.setValueAt(program.getTuitionFee() + "", i, 6);
	            }
	        }
	    }
	}

	public void search() {
	    HashMap<String, StudyProgram> mapStudyProgram = search.searchInList();
	    if (mapStudyProgram.size() == 0) {
	        JOptionPane.showMessageDialog(view, "Không tìm thấy ngành học", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	    } else if (mapStudyProgram.size() > 0) {
	        modelTable.setRowCount(0);
	        addDataToTable(mapStudyProgram);
	    }
	}

	public void cancel() {
	    modelTable.setRowCount(0);
	    addDataToTable(this.model.getProgramList());
	}


	public void disPlayAbout() {
		JOptionPane.showMessageDialog(view, "Phần mềm quản lý trường đại học");
	}

	public void exit() {
		int result = JOptionPane.showConfirmDialog(
                view,
                "Bạn có muốn thoát khỏi chương trình",
                "Exit",
                JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	public void readFile() {
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Choose Excel File");
	    fileChooser.setFileFilter(new FileNameExtensionFilter("Excel File", "xlsx"));

	    int result = fileChooser.showOpenDialog(null);
	    if (result == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = fileChooser.getSelectedFile();
	        String filePath = selectedFile.getAbsolutePath();

	        ArrayList<StudyProgram> studyPrograms = readFile.readFile(filePath);
	        this.model.setProgramList(studyPrograms);
	        this.model.setUniversityNames(studyPrograms);
	    }
	}

	public void setDataToTable() {
		modelTable.setRowCount(0);
	    for (int i = 0; i < this.model.getProgramList().size(); i++) {
	        StudyProgram program = this.model.getProgramList().get(i);
	        modelTable.addRow(new Object[]{
	                program.getProgramCode(),
	                program.getProgramName(),
	                program.getAdmissionScore() + "",
	                program.getUniversity().getUniversityCode() + "",
	                program.getUniversity().getUniversityName() + "",
	                program.getUniversity().getAddress() + "",
	                program.getTuitionFee() + ""});
	    }
	}

	public void setBoxSearch() {
		for (String nameUniver : this.model.getUniversityNames().keySet()) {
            view.comboBox_SelectedUniversitySearch.addItem(nameUniver);
        }
	}

	public void saveFile() {
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save As");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xlsx")) {
                filePath += ".xlsx";
            }
            saveFile.saveListToExcel(this.model.getProgramList(), filePath);
        }
	}
}