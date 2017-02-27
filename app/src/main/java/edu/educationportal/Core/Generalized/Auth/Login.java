package edu.educationportal.Core.Generalized.Auth;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import edu.educationportal.Core.BaseFragment;
import edu.educationportal.Core.CoreFeatures;
import edu.educationportal.R;

/**
 * Created by Ishaq Hassan on 2/27/2017.
 */

public class Login extends BaseFragment {

    private EditText et_email;
    private EditText et_password;
    private Button btn_sign_in;

    private ProgressDialog progressDialog;

    public Login(){
        setTitle("Login");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setFragmentView(inflater.inflate(R.layout.students_login_layout,container,false));
        setupComponents();
        setupListners();

        return getFragmentView();
    }

    private void setupComponents(){
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);

        progressDialog = CoreFeatures.buildLoadingDialog(getContext(),"Loading...Please wait");
    }

    private void setupListners(){
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                et_email.setError(null);
                et_password.setError(null);

                if(email.length() <= 0){
                    et_email.setError("Please enter Email");
                    return;
                }

                if(password.length() <= 0){
                    et_password.setError("Please enter Password");
                    return;
                }

                login(email,password);
            }
        });
    }

    private void login(String email,String password){
        progressDialog.show();
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){

                        }else {
                            Toast.makeText(getContext(),getResources().getString(R.string.invalid_sign_in_toast),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
