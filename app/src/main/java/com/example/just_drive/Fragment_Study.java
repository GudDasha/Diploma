package com.example.just_drive;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

public class Fragment_Study  extends Fragment {
    public Fragment_Study(){
        super(R.layout.fragment_study);
    }
    public static Fragment_Study newInstance(){
        return new Fragment_Study();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_study, container, false);
        AppCompatButton btn_videos = view.findViewById(R.id.btn_videos);
        AppCompatButton btn_rules = view.findViewById(R.id.btn_rules);
        btn_rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new
                Intent(Intent.ACTION_VIEW, Uri.parse("https://nevatec.spb.ru/info/PDD.pdf"));
                startActivity(browserIntent);
            }
        });
        btn_videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(),Videos.class);
                startActivity(i);
            }
        });
        return view;
    }
}
