package com.example.just_drive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Training extends AppCompatActivity {
    ImageView back_main;
    String name = "";
    String user;
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

        //проверка наличия авторизированного пользователя
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            //возвращение на главный экран
            back_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Training.this, MainFragments.class);
                    startActivity(i);
                    finish();

                }
            });
        } else {
            btn_settings.setVisibility(View.INVISIBLE);
            back_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Training.this, Registration.class);
                    startActivity(i);
                    finish();
                }
            });
        }

        //переход на экран настроек тестрования
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Training.this, Settings.class);
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
                    case "Билет № 3":
                        name = state.getName();
                        i = new Intent (Training.this, Bulet.class);
                        i.putExtra("name",name);
                        startActivity(i);
                        finish();
                    case "Билет № 4":
                        name = state.getName();
                        i = new Intent (Training.this, Bulet.class);
                        i.putExtra("name",name);
                        startActivity(i);
                        finish();
                    case "Билет № 5":
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
        recyclerList.add(new State("Билет № 3"));
        recyclerList.add(new State("Билет № 4"));
        recyclerList.add(new State("Билет № 5"));

    }
}