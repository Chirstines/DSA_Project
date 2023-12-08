package model;

import java.util.Objects;

public class StudyProgram {
    private String programCode;
    private String programName;
    private University university;
    private long tuitionFee;
    private double admissionScore;

    public StudyProgram() {
    }

    public StudyProgram(String programCode, String programName, University university, long tuitionFee, double admissionScore) {
        this.programCode = programCode;
        this.programName = programName;
        this.university = university;
        this.tuitionFee = tuitionFee;
        this.admissionScore = admissionScore;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public long getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(long tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public double getAdmissionScore() {
        return admissionScore;
    }

    public void setAdmissionScore(double admissionScore) {
        this.admissionScore = admissionScore;
    }

    @Override
    public String toString() {
        return "StudyProgram [programCode=" + programCode + ", programName=" + programName + ", university=" + university +
                ", tuitionFee=" + tuitionFee + ", admissionScore=" + admissionScore + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(admissionScore, programCode, programName, tuitionFee, university);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        StudyProgram other = (StudyProgram) obj;
        return Double.doubleToLongBits(admissionScore) == Double.doubleToLongBits(other.admissionScore) &&
                tuitionFee == other.tuitionFee && Objects.equals(programCode, other.programCode) &&
                Objects.equals(programName, other.programName) && Objects.equals(university, other.university);
    }
}
