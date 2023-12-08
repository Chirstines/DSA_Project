package model;

import java.util.Objects;

public class University {
    private String universityCode;
    private String universityName;
    private String address;
    SMModel model;

    public University(String universityCode, String universityName, String address) {
        this.universityCode = universityCode;
        this.universityName = universityName;
        this.address = address;
    }

    public String getUniversityCode() {
        return universityCode;
    }

    public void setUniversityCode(String universityCode) {
        this.universityCode = universityCode;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "University [universityCode=" + universityCode + ", universityName=" + universityName +
                ", address=" + address + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, universityCode, universityName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        University other = (University) obj;
        return Objects.equals(address, other.address) &&
                Objects.equals(universityCode, other.universityCode) &&
                Objects.equals(universityName, other.universityName);
    }

    public static String getUniversityByName(String universityName) {
        return universityName;
    }

    public static String getUniversityByCode(String universityCode) {
        return universityCode;
    }

    public static String getUniversityByAddress(String address) {
        return address;
    }
}

