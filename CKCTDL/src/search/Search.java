package search;

import java.util.HashMap;

import model.StudyProgram;
import model.SMModel;
import view.SMView;
import view.ControllerView;

public class Search {
    SMView view;
    ControllerView controller;
    SMModel model;

    public Search(ControllerView controller, SMModel model, SMView view) {
        this.controller = controller;
        this.view = view;
        this.model = model;
    }

    public HashMap<String, StudyProgram> searchInList() {
        boolean hasSelectedUniversity = view.comboBox_SelectedUniversitySearch.getSelectedIndex() - 1 >= 0;
        String selectedProgramName = view.textField_SelectedProgramNameSearch.getText();
        boolean hasSelectedProgramName = selectedProgramName.length() > 0;
        String selectedUniversityName = (String) view.comboBox_SelectedUniversitySearch.getSelectedItem();
        HashMap<String, StudyProgram> existingProgramsMap = new HashMap<>();

        if (hasSelectedUniversity && !hasSelectedProgramName) {
            for (int i = 0; i < model.getProgramList().size(); i++) {
                if (model.getProgramList().get(i).getUniversity().getUniversityName().toLowerCase().contains(selectedUniversityName.toLowerCase())) {
                    existingProgramsMap.put(model.getProgramList().get(i).getProgramCode(), model.getProgramList().get(i));
                }
            }
        }

        if (!hasSelectedUniversity && hasSelectedProgramName) {
            for (int i = 0; i < model.getProgramList().size(); i++) {
                if (model.getProgramList().get(i).getProgramName().toLowerCase().contains(selectedProgramName.toLowerCase())) {
                    existingProgramsMap.put(model.getProgramList().get(i).getProgramCode(), model.getProgramList().get(i));
                }
            }
        }

        if (hasSelectedUniversity && hasSelectedProgramName) {
            for (int i = 0; i < model.getProgramList().size(); i++) {
                if (model.getProgramList().get(i).getUniversity().getUniversityName().toLowerCase().contains(selectedUniversityName.toLowerCase()) &&
                        model.getProgramList().get(i).getProgramName().toLowerCase().contains(selectedProgramName.toLowerCase())) {
                    existingProgramsMap.put(model.getProgramList().get(i).getProgramCode(), model.getProgramList().get(i));
                }
            }
        }

        return existingProgramsMap;
    }
}

