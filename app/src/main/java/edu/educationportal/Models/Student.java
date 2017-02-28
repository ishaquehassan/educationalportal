package edu.educationportal.Models;

/**
 * Created by Ishaq Hassan on 2/28/2017.
 */

public class Student {
    String name;
    String email;
    Integer semester;

    public Student(){

    }

    public Student(String name, String email, Integer semester) {
        this.name = name;
        this.email = email;
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}
