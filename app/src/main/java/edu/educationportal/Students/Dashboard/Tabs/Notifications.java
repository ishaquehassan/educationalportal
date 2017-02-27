package edu.educationportal.Students.Dashboard.Tabs;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import edu.educationportal.Core.Adapters.NotificationsAdapter;
import edu.educationportal.Core.BaseFragment;
import edu.educationportal.Models.Notification;
import edu.educationportal.R;

/**
 * Created by Ishaq Hassan on 2/28/2017.
 */

public class Notifications extends BaseFragment {

    private ProgressDialog progressDialog;
    private ArrayList<Notification> notifications;

    public Notifications() {
        setTitle("Notifications");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setFragmentView(inflater.inflate(R.layout.students_notifications, container, false));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.notifications_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        notifications = new ArrayList<>();
        final RecyclerView.Adapter adapter = new NotificationsAdapter(notifications);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        FirebaseDatabase.getInstance().getReference().child("Notifications")
                .addChildEventListener(new ChildEventListener() {
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

        return getFragmentView();
    }
}