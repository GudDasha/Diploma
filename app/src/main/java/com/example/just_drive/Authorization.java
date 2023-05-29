package com.example.just_drive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class Authorization extends AppCompatActivity {
    private EditText email;
    private EditText password;
    Button btn_auth;
    public final static Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("[a-z-Z0-9._-]+@[a-z]+\\.+[a-z]+");

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        Button btn_reg = (Button) findViewById(R.id.btn_registration);
        //переход на экран Регистрация
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Authorization.this, Registration.class);
                startActivity(i);
                finish();
            }
        });


        email = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        btn_auth = (Button) findViewById(R.id.btn_auth);
        auth = FirebaseAuth.getInstance();
        AppCompatButton btn_reset = findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Authorization.this,Reset_Password.class);
                startActivity(i);
                finish();
            }
        });
        btn_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //проверка полей ввода
                if(email.getText().toString().equals("")&&password.getText().toString().equals("")){
                    Toast.makeText(Authorization.this,"Введите данные",Toast.LENGTH_SHORT).show();
                }
                if(email.getText().toString().equals("")){
                    Toast.makeText(Authorization.this,"Введите электронную почту",Toast.LENGTH_SHORT).show();}
                else if (password.getText().toString().equals("")){
                    Toast.makeText(Authorization.this,"Введите пароль",Toast.LENGTH_SHORT).show();
                }
                else if (checkEmail(email.getText().toString())==false){
                    Toast.makeText(Authorization.this,"Некорректная электронная почта",Toast.LENGTH_SHORT).show();
                }
                else {
                    //авторизация пользователя
                    auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Intent i = new Intent(Authorization.this,MainFragments.class);
                                        startActivity(i);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(Authorization.this,"Пользователь не авторизован",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });
    }
    public static boolean checkEmail(String email){
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }
}