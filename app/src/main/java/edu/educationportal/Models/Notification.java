package edu.educationportal.Models;

/**
 * Created by Ishaq Hassan on 2/28/2017.
 */

public class Notification {
    String title;
    String descp;

    public Notification() {
    }

    public Notification(String title, String descp) {
        this.title = title;
        this.descp = descp;
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
}
