package edu.educationportal.Models;

/**
 * Created by Ishaq Hassan on 2/28/2017.
 */

public class Student {
    String name;
    String email;

    public Student(){

    }

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
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
}
