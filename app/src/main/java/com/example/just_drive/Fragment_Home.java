package com.example.just_drive;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Home  extends Fragment {
    public Fragment_Home () {
        super(R.layout.fragment_home);
    }

    public static Fragment_Home newInstance() {
        return new Fragment_Home();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button btn_training = (Button) view.findViewById(R.id.training);
        Button btn_exam = (Button) view.findViewById(R.id.btn_exam);
        //переход на экран Тренировка
        btn_training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),Training.class);
                startActivity(i);
            }
        });
        //переход на экран Экзамен
        btn_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //рандомный выбор билета
                String[] myString = new String[]{"Билет № 1","Билет № 2"};
                int n = (int)Math.floor(Math.random() * myString.length);
                String name = myString[n].toString();
                Intent i = new Intent (getContext(), Bulet_Exam.class);
                i.putExtra("name",name);
                startActivity(i);
            }
        });
        return view;
    }
}
