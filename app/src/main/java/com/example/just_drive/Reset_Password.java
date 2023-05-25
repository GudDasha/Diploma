package com.example.just_drive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Reset_Password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        EditText email = findViewById(R.id.reset_email);
        AppCompatButton btn_send = findViewById(R.id.btn_sendpassword);
        AppCompatButton btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Reset_Password.this,Authorization.class);
                startActivity(i);
                finish();
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().equals("")){
                    Toast.makeText(Reset_Password.this,"Введите электронную почту",Toast.LENGTH_SHORT).show();
                }
                else{
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    String emailAddress = email.getText().toString();
                    auth.sendPasswordResetEmail(emailAddress)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Reset_Password.this,"Письмо отправлено на почту",Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(Reset_Password.this,Authorization.class);
                                        startActivity(i);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(Reset_Password.this,"Введена неверная эл.почта или её не существует",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });
    }
}