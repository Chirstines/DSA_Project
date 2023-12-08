package model;

import java.util.ArrayList;
import java.util.HashMap;

public class SMModel {
    private ArrayList<StudyProgram> programList;
    private String choice;
    private String fileName;
    private HashMap<String, StudyProgram> universityMap;

    public SMModel() {
        this.universityMap = new HashMap<>();
        this.programList = new ArrayList<>();
        this.choice = "";
        this.fileName = "";
    }

    public SMModel(ArrayList<StudyProgram> programList, HashMap<String, StudyProgram> universityMap) {
        this.programList = programList;
        this.universityMap = universityMap;
    }

    public ArrayList<StudyProgram> getProgramList() {
        return programList;
    }

    public void setProgramList(ArrayList<StudyProgram> programList) {
        for(StudyProgram program : programList) {
        	if(!checkExists(program)) {
        		insert(program);
        	}
        }
    }

    public void insert(StudyProgram program) {
        this.programList.add(program);
    }

    public void delete(StudyProgram program) {
        this.programList.remove(program);
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setUniversityNames(ArrayList<StudyProgram> list) {
        for (int i = 0; i < list.size(); i++) {
            this.universityMap.put(list.get(i).getUniversity().getUniversityName(), list.get(i));
        }
    }

    public HashMap<String, StudyProgram> getUniversityNames() {
        return universityMap;
    }

    public void insertUniversityMap(StudyProgram subject) {
        this.universityMap.put(subject.getUniversity().getUniversityName(), subject);
    }

    public void deleteUniversityMap(StudyProgram subject) {
        this.universityMap.remove(subject.getUniversity().getUniversityName());
    }

    public boolean checkExists(StudyProgram subject) {
        for (StudyProgram existingSubject : programList) {
            if (existingSubject.getProgramCode().equals(subject.getProgramCode())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkExistsUniversity(StudyProgram subject) {
        for (StudyProgram existingSubject : getProgramList()) {
            if (existingSubject.getUniversity().getUniversityName().equals(subject.getUniversity().getUniversityName())) {
                return true;
            }
        }
        return false;
    }

    public StudyProgram getExistingSubject(StudyProgram subject) {
        StudyProgram existingSubject = new StudyProgram();
        for (StudyProgram subjectInList : programList) {
            if (subjectInList.getProgramCode().equals(subject.getProgramCode())) {
                existingSubject = subjectInList;
            }
        }
        return existingSubject;
    }
}
