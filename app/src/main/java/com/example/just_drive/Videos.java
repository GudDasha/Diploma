package com.example.just_drive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Videos extends AppCompatActivity {
    RecyclerView recyclerView;
    private static Vector<StateVideo> Videos = new Vector<StateVideo>();
    VideoAdapter videoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        recyclerView = (RecyclerView) findViewById(R.id.recyclervideos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //получение данных о видеороликах
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference databaseReference = db.child("Videos");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Videos.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    final String getTitle = dataSnapshot.child("title").getValue(String.class);
                    final String getUrl = dataSnapshot.child("url").getValue(String.class);
                    StateVideo state = new StateVideo(getTitle,"<iframe width=\"100%\" gravity=\"center\" height=\"100%\" src=\""+getUrl+"\" allowfullscreen></iframe>");
                    Videos.add(state);
                }
                videoAdapter = new VideoAdapter(Videos);
                recyclerView.setAdapter(videoAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //поиск видероликов
        SearchView search = findViewById(R.id.edit_search);
        search.clearFocus();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                filerList(newText);
                return false;
            }
        });
    }
    private void filerList(String newText) {
        List<StateVideo> fileterdList = new ArrayList<>();
        for(StateVideo item : Videos){
            if(item.getTopic().toLowerCase().contains(newText.toLowerCase())){
                fileterdList.add(item);
            }
        }
        if(fileterdList.isEmpty()){
            Toast.makeText(Videos.this,"Ничего не найдено",Toast.LENGTH_SHORT).show();
        }
        else {
           videoAdapter.setFilteredeList(fileterdList);
        }
    }

}