package ggoa.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PersonalDetails {
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Date dob;
    private Name nameOfFather;
    private String pan;

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Name getNameOfFather() {
        return nameOfFather;
    }

    public void setNameOfFather(Name nameOfFather) {
        this.nameOfFather = nameOfFather;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }
}
