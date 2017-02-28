package edu.educationportal.Models;

/**
 * Created by Ishaq Hassan on 3/1/2017.
 */

public class Assignment {
    String title;
    String descp;
    String teacherName;
    Integer semester;
    String teacherId;

    public Assignment() {
    }

    public Assignment(String title, String descp, String teacherName, Integer semester, String teacherId) {
        this.title = title;
        this.descp = descp;
        this.teacherName = teacherName;
        this.semester = semester;
        this.teacherId = teacherId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
