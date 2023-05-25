package com.example.just_drive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Vector;

public class Lesson extends AppCompatActivity {
RecyclerView recyclerView;
Vector<YoutubeVideo>youtubeVideos = new Vector<YoutubeVideo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        recyclerView = (RecyclerView) findViewById(R.id.recyclervideos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        youtubeVideos.add(new YoutubeVideo("<iframe width=\"100%\" gravity=\"center\" height=\"100%\" src=\"https://firebasestorage.googleapis.com/v0/b/justdrive-72c73.appspot.com/o/%D0%9E%D0%B1%D1%89%D0%B8%D0%B5_%D0%BF%D0%BE%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D1%8F_1.mp4?alt=media&token=d3a360c7-5777-4c8c-b1f4-c1056cb60d17\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideo("<iframe width=\"100%\" gravity=\"center\" height=\"100%\"src=\"https://firebasestorage.googleapis.com/v0/b/justdrive-72c73.appspot.com/o/%D0%9E%D0%B1%D1%89%D0%B8%D0%B5_%D0%BF%D0%BE%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D1%8F_2.mp4?alt=media&token=95f75dd6-0dfb-43ce-8ef6-325a2cd6fbba\" framebotder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideo(""));
        youtubeVideos.add(new YoutubeVideo(""));
        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);
        recyclerView.setAdapter(videoAdapter);
    }
}