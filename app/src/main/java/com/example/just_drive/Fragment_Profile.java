package com.example.just_drive;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Fragment_Profile extends Fragment {
    EditText name_profile;
    EditText email_profile;
    EditText password;
    AppCompatButton sign_out;
    ImageView btn_save;
    private FirebaseAuth firebaseAuth;
    public Fragment_Profile(){
        super(R.layout.fragment_profile);
    }
    public final static Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("[a-z-Z0-9._-]+@[a-z]+\\.+[a-z]+");
    public static Fragment_Profile newInstance(){
        return new Fragment_Profile();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        List<User> users= new ArrayList<>();
        AppCompatButton btn_statistic = view.findViewById(R.id.btn_statistic);
        name_profile = view.findViewById(R.id.name_profile);
        email_profile = view.findViewById(R.id.email_profile);
        password = view.findViewById(R.id.password_profile);
        btn_save = view.findViewById(R.id.btn_save);
        sign_out = view.findViewById(R.id.sign_out);

        //переход на экран статистики
        btn_statistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(),Statistic.class);
                startActivity(i);
            }
        });

        //получение данных о пользователе
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference databaseReference = db.child("Users").child(currentUser);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //вывод данных
                    User Name = snapshot.getValue(User.class);
                    name_profile.setText(Name.getName());
                    User Email = snapshot.getValue(User.class);
                    email_profile.setText(Email.getEmail());
                    User Password = snapshot.getValue(User.class);
                    password.setText(Password.getPassword());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        //редактирование данных
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //проверка наличия авторизированного пользователя
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    //проверка полей ввода
                    if(email_profile.getText().toString().equals("")&&password.getText().toString().equals("")&&name_profile.getText().toString().equals("")){
                        Toast.makeText(getContext(),"Введите данные",Toast.LENGTH_SHORT).show();
                    }
                    else if(name_profile.getText().toString().equals("")){
                        Toast.makeText(getContext(),"Введите имя",Toast.LENGTH_SHORT).show();
                    }
                    else if(email_profile.getText().toString().equals("")){
                        Toast.makeText(getContext(),"Введите электронную почту",Toast.LENGTH_SHORT).show();}
                    else if (checkEmail(email_profile.getText().toString())==false){
                        Toast.makeText(getContext(),"Некорректная электронная почта",Toast.LENGTH_SHORT).show();
                    }
                    else if (password.getText().toString().equals("")){
                        Toast.makeText(getContext(),"Введите пароль",Toast.LENGTH_SHORT).show();
                    }
                    else if(password.length()<6){
                        Toast.makeText(getContext(),"Минимальная длина пароля 6 символов",Toast.LENGTH_SHORT).show();
                    }

                    else{
                        //сохранение в Realtime Database
                        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                        mDatabase.child("Users").child(currentUser).child("email").setValue(email_profile.getText().toString());
                        mDatabase.child("Users").child(currentUser).child("password").setValue(password.getText().toString());
                        mDatabase.child("Users").child(currentUser).child("name").setValue(name_profile.getText().toString());

                        //сохранение в Firebase Authentication
                        user.updateEmail(email_profile.getText().toString().trim())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            user.updatePassword(password.getText().toString().trim())
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()){
                                                                //авторизация пользователя
                                                                firebaseAuth.signInWithEmailAndPassword(email_profile.getText().toString(),password.getText().toString())
                                                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                                            @Override
                                                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                                                if(task.isSuccessful()){
                                                                                    Toast.makeText(getContext(),"Данные сохранены",Toast.LENGTH_SHORT).show();
                                                                                }

                                                                            }
                                                                        });
                                                            }
                                                        }
                                                    });
                                        }
                                        else{
                                            Toast.makeText(getContext(),"Вы превысили количество попыток изменения данных. Перезагрузите приложение",Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });

                    }
                }


            }
        });

        //выход из аккаунта
        firebaseAuth = FirebaseAuth.getInstance();
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent intent = new Intent(getContext(),Authorization.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        return view;
    }
    public static boolean checkEmail(String email){
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }
}
