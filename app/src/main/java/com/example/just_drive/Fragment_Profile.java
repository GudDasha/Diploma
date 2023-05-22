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

public class Fragment_Profile extends Fragment {
    EditText name_profile;
    EditText email;
    EditText password;
    AppCompatButton sign_out;
    ImageView btn_save;
    private FirebaseAuth firebaseAuth;
    public Fragment_Profile(){
        super(R.layout.fragment_profile);
    }
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
        email = view.findViewById(R.id.email_profile);
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
                    email.setText(Email.getEmail());
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
                //сохранение в Realtime Database
                String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("Users").child(currentUser).child("email").setValue(email.getText().toString());
                mDatabase.child("Users").child(currentUser).child("password").setValue(password.getText().toString());
                mDatabase.child("Users").child(currentUser).child("name").setValue(name_profile.getText().toString());

                //получение текущего пользователя
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //сохранение в Firebase Authentication
                user.updateEmail(email.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(),"Данные сохранены",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                user.updatePassword(password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getContext(),"Данные сохранены",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        //выход из аккаунта
        firebaseAuth = FirebaseAuth.getInstance();
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent intent = new Intent(getContext(),Authorization.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
