package com.example.just_drive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class Result extends AppCompatActivity {

    DatabaseReference mDataBase = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        final AppCompatButton back_bulets = (AppCompatButton) findViewById(R.id.back_bulets);
        final AppCompatButton back_main = (AppCompatButton) findViewById(R.id.back_main);
        final TextView correctAnswer = (TextView) findViewById(R.id.correctAnswers);
        final TextView incorrectAnswer = (TextView) findViewById(R.id.incorrectAnswers);
        final TextView txt_result = (TextView) findViewById(R.id.txt_exam);
        DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        String date = formatter.format(new Date());
        //получение результатов
        final int getCorrectAnswer = getIntent().getIntExtra("correct",0);
        final int getIncorrectAnswer = getIntent().getIntExtra("incorrect",0);
        correctAnswer.setText(String.valueOf("Верные ответы: "+ getCorrectAnswer));
        incorrectAnswer.setText(String.valueOf("Неверные ответы: "+ getIncorrectAnswer));
        String pass =  getIntent().getStringExtra("pass");
        String name = getIntent().getStringExtra("name");

        FirebaseAuth currentUser = FirebaseAuth.getInstance();
        if (currentUser.getCurrentUser() != null){
            //загрузка результатов в базу данных
            if(pass.equals("Экзамен сдан!")){
                txt_result.setText(pass);
                Results newResult = new Results(date,"Экзамен", getCorrectAnswer, getIncorrectAnswer);
                writeResultToDB(newResult, "Экзамен");
            }
            else if(pass.equals("Экзамен не сдан!")){
                txt_result.setText(pass);
                Results newResult = new Results(date,"Экзамен", getCorrectAnswer, getIncorrectAnswer);
                writeResultToDB(newResult, "Экзамен");
            }
            else if (pass.equals("")){
                txt_result.setText("");
                Results newResult = new Results(date,name, getCorrectAnswer, getIncorrectAnswer);
                writeResultToDB(newResult, name);
            }
            else if(pass.equals("Настройка")){
                txt_result.setText(pass);
                Results newResult = new Results(date,"Настройка", getCorrectAnswer, getIncorrectAnswer);
                writeResultToDB(newResult, name);
            }

            //переход на экран Тренировки
            back_bulets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Result.this, Training.class);
                    startActivity(i);
                    finish();
                }
            });
            //переход на главный экран
            back_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Result.this, MainFragments.class);
                    startActivity(i);
                    finish();
                }
            });
        }
        else{
            back_main.setVisibility(View.INVISIBLE);
            back_bulets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Result.this, Training.class);
                    startActivity(i);
                    finish();
                }
            });
        }



    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Result.this, Training.class));
        finish();
    }

    //сохранение результатов в базу
    public void writeResultToDB(Object data, String currentResult){
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String name = "Results";
        DatabaseReference myRef = mDataBase.child("Users/" + currentUser + "/"+name+ "/"+ currentResult);
        myRef.setValue(data);
    }
}