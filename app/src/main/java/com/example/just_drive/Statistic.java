package com.example.just_drive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Statistic extends AppCompatActivity {
   // StateStatisticAdapter adapter;
    //String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private final List<StateStatistic> recyclerList = new ArrayList<StateStatistic>();
   // private final DatabaseReference database = FirebaseDatabase.getInstance().getReference().child( "Users").child(currentUser);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        RecyclerView recyclerView = findViewById(R.id.recycle_settings);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Statistic.this));
        TextView correct = findViewById(R.id.correct);
        TextView date = findViewById(R.id.date);
        TextView mistakes = findViewById(R.id.uncorrect);
        TextView result = findViewById(R.id.type);
        //adapter = new StateStatisticAdapter(this,recyclerList);
        //recyclerView.setAdapter(adapter);
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference database = db.child("Users").child(currentUser).child("Results");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                recyclerList.clear();
                for (DataSnapshot dataSnapshot : snapshot.child("Билет № 1").getChildren()) {
                    // StateStatistic state = dataSnapshot.getValue(StateStatistic.class);
                    //recyclerList.add(state);
                    final String getCorrect = dataSnapshot.child("correct").getValue(String.class);
                    final String getDate = dataSnapshot.child("date").getValue(String.class);
                    final String getMistake = dataSnapshot.child("mistakes").getValue(String.class);
                    final String getResult = dataSnapshot.child("result").getValue(String.class);
                    StateStatistic state = new StateStatistic(getCorrect, getDate, getMistake, getResult);
                    recyclerList.add(state);
                }
                recyclerView.setAdapter(new StateStatisticAdapter(Statistic.this, recyclerList));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}