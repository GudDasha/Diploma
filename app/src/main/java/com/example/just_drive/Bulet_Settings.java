package com.example.just_drive;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Bulet_Settings extends AppCompatActivity {
    int quest;
    long time;
    String topic;
    String help;
    String attempts;
    private TextView question;
    private TextView questions;
    private ImageView img_quest;
     private long mTimeLeftInMillis;
    private CountDownTimer mCountDownTimer;
    TextView timer;
    AppCompatButton option1, option2, option3, option4, btn_next;
    private final List<QuestionList> questionLists = new ArrayList<>();
    private int currentQuestionPosition = 0;
    private String selectedOptionByUser = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulet_settings);
        TextView txt_name = (TextView) findViewById(R.id.name);
        txt_name.setText("Настроенная тренировка");
        timer = findViewById(R.id.timer);
        img_quest = (ImageView) findViewById(R.id.question_image);

        question = (TextView) findViewById(R.id.question);
        questions = (TextView) findViewById(R.id.questions);
        option1 = (AppCompatButton) findViewById(R.id.option1);
        option2 = (AppCompatButton) findViewById(R.id.option2);
        option3 = (AppCompatButton) findViewById(R.id.option3);
        option4 = (AppCompatButton) findViewById(R.id.option4);
        btn_next = (AppCompatButton) findViewById(R.id.btn_next);

        String t = getIntent().getStringExtra("time");
        time = Long.valueOf(t);
        mTimeLeftInMillis = time*60*1000;


        topic = getIntent().getStringExtra("topic");
        String q = getIntent().getStringExtra("quest");
        quest = Integer.valueOf( q);

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Bulet_Settings.this, Training.class);
                startActivity(i);
                finish();
            }});

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText (mTimeLeftInMillis);
            }

            @Override
            public void onFinish() {
                mCountDownTimer.cancel();
                Toast.makeText(Bulet_Settings.this,"Время вышло",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Bulet_Settings.this,Result.class);
                intent.putExtra("correct",getCorrectAnswers());
                intent.putExtra("incorrect",getIncorrectAnswers());
                startActivity(intent);
                finish();
            }
        }.start();

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option1.getText().toString();
                    option1.setBackgroundResource(R.drawable.test_border_red);
                    option1.setTextColor(Color.WHITE);

                    revealAnswer();
                    questionLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option2.getText().toString();
                    option2.setBackgroundResource(R.drawable.test_border_red);
                    option2.setTextColor(Color.WHITE);

                    revealAnswer();
                    questionLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option3.getText().toString();
                    option3.setBackgroundResource(R.drawable.test_border_red);
                    option3.setTextColor(Color.WHITE);

                    revealAnswer();
                    questionLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option4.getText().toString();
                    option4.setBackgroundResource(R.drawable.test_border_red);
                    option4.setTextColor(Color.WHITE);

                    revealAnswer();
                    questionLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        ProgressDialog progressDialog = new ProgressDialog(Bulet_Settings.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        FirebaseDatabase.getInstance().getReference().child("Settings").child(topic).limitToFirst(quest).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    final String getOption1 = dataSnapshot.child("option1").getValue(String.class);
                    final String getOption2  = dataSnapshot.child("option2").getValue(String.class);
                    final String getOption3  = dataSnapshot.child("option3").getValue(String.class);
                    final String getOption4  = dataSnapshot.child("option4").getValue(String.class);
                    final String getQuestion = dataSnapshot.child("question").getValue(String.class);
                    final String getAnswer = dataSnapshot.child("answer").getValue(String.class);
                    final String getImage = dataSnapshot.child("image").getValue(String.class);
                    QuestionList questionList1 = new QuestionList(getOption1,getOption2,getOption3,getOption4,getQuestion,getAnswer, getImage,"");
                    questionLists.add(questionList1);}
                progressDialog.hide();

                questions.setText((currentQuestionPosition+1)+"/"+questionLists.size());
                question.setText(questionLists.get(0).getQuestion());
                option1.setText(questionLists.get(0).getOption1());
                option2.setText(questionLists.get(0).getOption2());
                option3.setText(questionLists.get(0).getOption3());
                option4.setText(questionLists.get(0).getOption4());
                Picasso.get().load(questionLists.get(0).getImage()).into(img_quest);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedOptionByUser.isEmpty()){
                    Toast.makeText(Bulet_Settings.this,"Пожалуйста сделайте выбор",Toast.LENGTH_SHORT).show();
                }
                else{
                    ChangeQuestion();
                }

            }
        });
    }
    //обновление таймера
    private void updateCountDownText(long mTimeLeftInMillis){
        int minutes = (int) mTimeLeftInMillis/1000/60;
        int seconds = (int) mTimeLeftInMillis/1000 % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        timer.setText(timeLeftFormatted);
    }
    //метод для подсчёта корректных ответов
    private int getCorrectAnswers (){
        int correctAnswers = 0;

        for(int i = 0;i>questionLists.size();i++){
            final String getUserSelectedAnswer = questionLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionLists.get(i).getAnswer();

            if(getUserSelectedAnswer.equals(getAnswer)){
                correctAnswers++;
            }
        }

        return correctAnswers;
    }

    //метод для подсчёта некорректных ответов
    private int getIncorrectAnswers (){
        int correctAnswers = 0;

        for(int i = 0;i<questionLists.size();i++){
            final String getUserSelectedAnswer = questionLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionLists.get(i).getAnswer();

            if(!getUserSelectedAnswer.equals(getAnswer)){
                correctAnswers++;
            }
        }

        return correctAnswers;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Bulet_Settings.this, Training.class));
        finish();
    }

    private void revealAnswer(){
        final String getAnswer = questionLists.get(currentQuestionPosition).getAnswer();

        if(option1.getText().toString().equals(getAnswer)){
            option1.setBackgroundResource(R.drawable.test_border_green);
            option1.setTextColor(Color.WHITE);
        }
        else if(option2.getText().toString().equals(getAnswer)){
            option2.setBackgroundResource(R.drawable.test_border_green);
            option2.setTextColor(Color.WHITE);
        }
        else if(option3.getText().toString().equals(getAnswer)){
            option3.setBackgroundResource(R.drawable.test_border_green);
            option3.setTextColor(Color.WHITE);
        }
        else if(option4.getText().toString().equals(getAnswer)){
            option4.setBackgroundResource(R.drawable.test_border_green);
            option4.setTextColor(Color.WHITE);
        }
    }

    private void ChangeQuestion(){
        currentQuestionPosition++;

        if((currentQuestionPosition+1) == questionLists.size()){
            btn_next.setText("Готово");
        }
        if(currentQuestionPosition<questionLists.size()){
            selectedOptionByUser = "";
            option1.setBackgroundResource(R.drawable.bulet_border_button);
            option1.setTextColor(Color.parseColor("#000000"));

            option2.setBackgroundResource(R.drawable.bulet_border_button);
            option2.setTextColor(Color.parseColor("#000000"));

            option3.setBackgroundResource(R.drawable.bulet_border_button);
            option3.setTextColor(Color.parseColor("#000000"));

            option4.setBackgroundResource(R.drawable.bulet_border_button);
            option4.setTextColor(Color.parseColor("#000000"));

            questions.setText((currentQuestionPosition+1)+"/"+questionLists.size());
            question.setText(questionLists.get(currentQuestionPosition).getQuestion());
            option1.setText(questionLists.get(currentQuestionPosition).getOption1());
            option2.setText(questionLists.get(currentQuestionPosition).getOption2());
            option3.setText(questionLists.get(currentQuestionPosition).getOption3());
            option4.setText(questionLists.get(currentQuestionPosition).getOption4());
            Picasso.get().load(questionLists.get(currentQuestionPosition).getImage()).into(img_quest);
        }
        else {
            Intent i = new Intent(Bulet_Settings.this, Result.class);
            String pass = "";
            i.putExtra("pass",pass);
            i.putExtra("correct",getCorrectAnswers());
            i.putExtra("incorrect",getIncorrectAnswers());
            startActivity(i);
            finish();
        }
    }
}