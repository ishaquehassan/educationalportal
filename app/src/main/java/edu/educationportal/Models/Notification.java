package edu.educationportal.Models;

/**
 * Created by Ishaq Hassan on 2/28/2017.
 */

public class Notification {
    String title;
    String descp;
    String teacherName;

    public Notification() {
    }

    public Notification(String title, String descp,String teacherName) {
        this.title = title;
        this.descp = descp;
        this.teacherName = teacherName;
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
}
