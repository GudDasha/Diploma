package com.example.just_drive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Training extends AppCompatActivity {
    ImageView back_main;
    String name = "";
    RecyclerView recyclerView;
    ArrayList<State> recyclerList = new ArrayList<State>();
    AppCompatButton btn_settings;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        recyclerView=(RecyclerView) findViewById(R.id.list_bulets);
        btn_settings = (AppCompatButton) findViewById(R.id.btn_settings);
        back_main = (ImageView) findViewById(R.id.back_main);

        //переход на экран настроек тестрования
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Training.this, Settings.class);
                startActivity(i);
                finish();

            }
        });
        //возвращение на главный экран
        back_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Training.this, MainFragments.class);
                startActivity(i);
                finish();

            }
        });

        setInitialData();

        StateAdapter.OnStateClickListener stateClickListener = new StateAdapter.OnStateClickListener() {
            @Override
            public void onState(State state, int position) {
                switch (state.getName()){
                    case "Билет № 1":
                        name = state.getName();
                        i = new Intent (Training.this, Bulet.class);
                        i.putExtra("name",name);
                        startActivity(i);
                        finish();
                    case "Билет № 2":
                        name = state.getName();
                        i = new Intent (Training.this, Bulet.class);
                        i.putExtra("name",name);
                        startActivity(i);
                        finish();
                }

            }
        };
        StateAdapter adapter = new StateAdapter(this,recyclerList,stateClickListener);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }
    private void setInitialData() {
        recyclerList.add(new State("Билет № 1"));
        recyclerList.add(new State("Билет № 2"));
        recyclerList.add(new State("№ 3"));
        recyclerList.add(new State("№ 4"));
        recyclerList.add(new State("№ 5"));
        recyclerList.add(new State("№ 6"));
        recyclerList.add(new State("№ 7"));
        recyclerList.add(new State("№ 8"));
        recyclerList.add(new State("№ 9"));
        recyclerList.add(new State("№ 10"));
        recyclerList.add(new State("№ 11"));
        recyclerList.add(new State("№ 12"));
        recyclerList.add(new State("№ 13"));
        recyclerList.add(new State("№ 14"));
        recyclerList.add(new State("№ 15"));
        recyclerList.add(new State("№ 16"));
        recyclerList.add(new State("№ 17"));
        recyclerList.add(new State("№ 18"));
        recyclerList.add(new State("№ 19"));
        recyclerList.add(new State("№ 20"));
        recyclerList.add(new State("№ 21"));
        recyclerList.add(new State("№ 22"));
        recyclerList.add(new State("№ 23"));
        recyclerList.add(new State("№ 24"));
        recyclerList.add(new State("№ 25"));
        recyclerList.add(new State("№ 26"));
        recyclerList.add(new State("№ 27"));
        recyclerList.add(new State("№ 28"));
        recyclerList.add(new State("№ 29"));
        recyclerList.add(new State("№ 30"));
        recyclerList.add(new State("№ 31"));
        recyclerList.add(new State("№ 32"));
        recyclerList.add(new State("№ 33"));
        recyclerList.add(new State("№ 34"));
        recyclerList.add(new State("№ 35"));
        recyclerList.add(new State("№ 36"));
        recyclerList.add(new State("№ 37"));
        recyclerList.add(new State("№ 38"));
        recyclerList.add(new State("№ 39"));
        recyclerList.add(new State("№ 40"));

    }
}