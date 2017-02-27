package edu.educationportal.Teachers.Dashboard.Tabs;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import edu.educationportal.Core.Adapters.NotificationsAdapter;
import edu.educationportal.Core.BaseFragment;
import edu.educationportal.Core.CoreFeatures;
import edu.educationportal.Models.Notification;
import edu.educationportal.R;

/**
 * Created by Ishaq Hassan on 2/28/2017.
 */

public class Notifications extends BaseFragment {

    private ProgressDialog progressDialog;
    private ArrayList<Notification> notifications;

    private EditText et_title;
    private EditText et_descp;
    private Button save_notification;
    private DatabaseReference notificationsRefrence;

    public Notifications() {
        setTitle("Notifications");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setFragmentView(inflater.inflate(R.layout.teachers_notifications, container, false));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.notifications_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        notifications = new ArrayList<>();
        final RecyclerView.Adapter adapter = new NotificationsAdapter(notifications);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        notificationsRefrence = FirebaseDatabase.getInstance().getReference().child("Notifications");

        notificationsRefrence.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Notification notification = dataSnapshot.getValue(Notification.class);
                        if (notification != null) {
                            notifications.add(notification);
                            adapter.notifyItemInserted(notifications.size() - 1);
                        }
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        setupComponents();
        setupListners();

        return getFragmentView();
    }

    private void setupComponents(){
        et_title = (EditText) findViewById(R.id.et_title);
        et_descp = (EditText) findViewById(R.id.et_descp);
        save_notification = (Button) findViewById(R.id.save_notification);
    }

    private void setupListners(){
        save_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et_title.getText().toString();
                String descp = et_descp.getText().toString();

                et_title.setError(null);
                et_descp.setError(null);

                if(title.length() <= 0){
                    et_title.setError("Please enter title");
                    return;
                }

                if(descp.length() <= 0){
                    et_descp.setError("Please enter Details");
                    return;
                }

                final ProgressDialog progressDialog = CoreFeatures.buildLoadingDialog(getContext(),"Saving...");
                progressDialog.show();

                Notification notification = new Notification(title,descp);
                notificationsRefrence.push().setValue(notification).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            et_title.setText("");
                            et_descp.setText("");
                            Toast.makeText(getContext(),"SAVED!",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}