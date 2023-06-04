package com.example.just_drive;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Settings extends AppCompatActivity {
    String quest;
    String time;
    String topic;
    String help;
    String attempts;
    String[] time_mas = { "5", "10", "20"};
    String[] topics_mas  = { "Общие положения", "Дорожные знаки","Дорожная разметка", "Сигналы светофора и регулировщика","Начало движения, маневрирование" };
    String [] quest_mas  = { "5", "10","15"};
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        CheckBox check_help = (CheckBox) findViewById(R.id.check_help);
        AppCompatButton btn_start = (AppCompatButton) findViewById(R.id.btn_start);

        Spinner spinner_time = findViewById(R.id.spiner_time);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, time_mas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_time.setAdapter(adapter);

        Spinner spinner_topics = findViewById(R.id.spinner_topics);
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, topics_mas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_topics.setAdapter(adapter2);

        Spinner spinner_quest = findViewById(R.id.spiner_quest);
        ArrayAdapter<String> adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, quest_mas);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_quest.setAdapter(adapter3);

        //переход на экран Тренировка
        ImageView btn_back = findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Settings.this,Training.class);
                startActivity(i);
                finish();
            }
        });

        //получение элемента списка количества вопросов
        AdapterView.OnItemSelectedListener itemSelectedListener_quest = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                String item = (String) parent.getItemAtPosition(position);
                quest =  item;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner_quest.setOnItemSelectedListener(itemSelectedListener_quest);

        //получение элемента списка времени
        AdapterView.OnItemSelectedListener itemSelectedListener_time = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);
                time = item;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner_time.setOnItemSelectedListener(itemSelectedListener_time);

        //получение элемента списка тем
        AdapterView.OnItemSelectedListener itemSelectedListener_topic = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);
                topic = item;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner_topics.setOnItemSelectedListener(itemSelectedListener_topic);


        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //получение подсказок
                if(check_help.isChecked()){
                    help = "да";
                    i = new Intent(Settings.this,Bulet_Settings.class);
                    i.putExtra("help",help);
                    i.putExtra("quest",quest);
                    i.putExtra("time",time);
                    i.putExtra("topic",topic);
                    startActivity(i);
                    finish();
                }
                else{
                    help = "нет";
                    i = new Intent(Settings.this,Bulet_Settings.class);
                    i.putExtra("help",help);
                    i.putExtra("quest",quest);
                    i.putExtra("time",time);
                    i.putExtra("topic",topic);
                    startActivity(i);
                    finish();
                }

            }
        });
    }

}