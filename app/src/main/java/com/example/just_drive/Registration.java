package com.example.just_drive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {
    Button to_auth;
    private EditText email_regist;
    private EditText password_regist;
    public final static Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("[a-z-Z0-9._-]+@[a-z]+\\.+[a-z]+");
    private FirebaseAuth regist;
    String user = "";
    DatabaseReference mDataBase = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        EditText name_reg = findViewById(R.id.reg_name);
        email_regist = findViewById(R.id.email_regist);
        password_regist = findViewById(R.id.password_rigist);
        regist = FirebaseAuth.getInstance();
        AppCompatButton btn_auth = findViewById(R.id.btn_unauth);

        //проверка на наличие пользователя
        btn_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Toast.makeText(Registration.this,"Пользователь есть в системе",Toast.LENGTH_SHORT).show();
                    Toast.makeText(Registration.this,"Выйдите полностью из аккаунта",Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(Registration.this, Training.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        Button btn_go_in =  findViewById(R.id.go_in);
        btn_go_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //переменные для регистрации
                String id = mDataBase.getKey();
                String name = name_reg.getText().toString().trim();
                String email = email_regist.getText().toString().trim();
                String password = password_regist.getText().toString().trim();
                //проверка условий регистрации
                if(TextUtils.isEmpty(email)&&TextUtils.isEmpty(name)&&TextUtils.isEmpty(password)){
                    Toast.makeText(Registration.this,"Введите данные",Toast.LENGTH_SHORT).show();}
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Registration.this,"Введите электронную почту",Toast.LENGTH_SHORT).show();}
                else if ( TextUtils.isEmpty(password)){
                    Toast.makeText(Registration.this,"Введите пароль",Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(name)){
                    Toast.makeText(Registration.this,"Введите имя",Toast.LENGTH_SHORT).show();
                }

                else if (checkEmail(email_regist.getText().toString())==false){
                    Toast.makeText(Registration.this,"Некорректная электронная почта",Toast.LENGTH_SHORT).show();
                }
                //регистрация пользователя
                else {
                    regist.createUserWithEmailAndPassword(email_regist.getText().toString(),password_regist.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                        User newUser = new User(currentUser, id, name, email, password);
                                        writeUserToDB(newUser, currentUser);
                                        Intent i = new Intent(Registration.this, MainFragments.class);
                                        startActivity(i);
                                        finish();
                                    }

                                    else{
                                        Toast.makeText(Registration.this,"Пользователь не зарегистрирован",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });

        to_auth = findViewById(R.id.btn_to_auth);
        to_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registration.this,Authorization.class);
                startActivity(i);
                finish();
            }
        });
    }
    //проверка эл.почты
    public static boolean checkEmail(String email){
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }
    //сохранение данных о пользователе
    public void writeUserToDB(Object data, String currentUser){
        DatabaseReference myRef = mDataBase.child("Users/" + currentUser);
        myRef.setValue(data);
    }

}