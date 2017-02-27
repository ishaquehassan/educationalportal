package edu.educationportal.Core;

import android.app.ProgressDialog;
import android.content.Context;

import edu.educationportal.Models.Student;
import edu.educationportal.Models.Teacher;

/**
 * Created by Ishaq Hassan on 2/27/2017.
 */

public class CoreFeatures {

    public static boolean isTeacher = false;
    public static Student student;
    public static Teacher teacher;

    public static ProgressDialog buildLoadingDialog(Context context,String message){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        return progressDialog;
    }
}
