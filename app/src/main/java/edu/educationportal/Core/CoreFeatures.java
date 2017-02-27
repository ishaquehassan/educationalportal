package edu.educationportal.Core;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Ishaq Hassan on 2/27/2017.
 */

public class CoreFeatures {

    public static ProgressDialog buildLoadingDialog(Context context,String message){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        return progressDialog;
    }
}
