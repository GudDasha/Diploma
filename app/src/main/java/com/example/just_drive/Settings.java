package com.example.just_drive;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Settings extends AppCompatActivity {
    String quest;
    long time;
    String topic;
    String help;
    String attempts;
    String[] time_mas = { "5", "10", "20"};
    String[] attempts_mas  = { "1", "2", "3"};
    String[] topics_mas  = { "1", "2"};
    String[] quest_mas  = { "1", "5", "7"};
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        CheckBox check_help = (CheckBox) findViewById(R.id.check_help);
        AppCompatButton btn_auth = (AppCompatButton) findViewById(R.id.btn_auth);

        Spinner spinner_time = findViewById(R.id.spiner_time);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, time_mas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_time.setAdapter(adapter);

        Spinner spinner_attempts = findViewById(R.id.spinner_attempts);
        ArrayAdapter<String> adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, attempts_mas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_attempts.setAdapter(adapter1);

        Spinner spinner_topics = findViewById(R.id.spinner_topics);
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, topics_mas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_topics.setAdapter(adapter2);


        Spinner spinner_quest = findViewById(R.id.spiner_quest);
        ArrayAdapter<String> adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, quest_mas);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_quest.setAdapter(adapter3);

        //получение элемента списка количества вопросов
        AdapterView.OnItemSelectedListener itemSelectedListener_quest = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);
                quest = item;
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
                time = Long.parseLong(item);
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

        //получение элемента списка количества попыток
        AdapterView.OnItemSelectedListener itemSelectedListener_attempts = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);
                attempts = item;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner_attempts.setOnItemSelectedListener(itemSelectedListener_attempts);

        btn_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //получение подсказок
                if(check_help.isChecked()){
                    help = "да";
                    i = new Intent(Settings.this,Bulet_Settings.class);
                    i.putExtra("quest",quest);
                    i.putExtra("time",time);
                    i.putExtra("topic",topic);
                    i.putExtra("attempts",attempts);
                    startActivity(i);
                    finish();
                }
                else{
                    help = "нет";
                    i = new Intent(Settings.this,Bulet_Settings.class);
                    i.putExtra("help",help);
                    i.putExtra("name",quest);
                    i.putExtra("time",time);
                    i.putExtra("topic",topic);
                    i.putExtra("attempts",attempts);
                    startActivity(i);
                    finish();
                }

            }
        });
    }

}