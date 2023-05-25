package com.example.just_drive;

public class YoutubeVideo {
    String videoUrl;
    public YoutubeVideo(){
    }
    public YoutubeVideo(String videoUrl){
        this.videoUrl = videoUrl;
    }
    public String getVideoUrl(){
        return videoUrl;
    }
    public void setVideoUrl(String videoUrl){
        this.videoUrl = videoUrl;
    }
}
