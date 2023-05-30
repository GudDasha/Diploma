package com.example.just_drive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
    private final List<StateStatistic> recyclerList = new ArrayList<StateStatistic>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        RecyclerView recyclerView = findViewById(R.id.recycle_settings);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Statistic.this));
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference database = db.child("Users").child(currentUser).child("Results");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                recyclerList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    final Integer getCorrect = dataSnapshot.child("correct").getValue(Integer.class);
                    final String getDate = dataSnapshot.child("date").getValue(String.class);
                    final Integer getMistake = dataSnapshot.child("mistakes").getValue(Integer.class);
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

        ImageView img_back_main = findViewById(R.id.img_back_main);
        img_back_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Statistic.this,MainFragments.class);
                startActivity(i);
                finish();
            }
        });
    }
}