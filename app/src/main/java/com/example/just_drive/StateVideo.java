package com.example.just_drive;

public class StateVideo {
    String videoUrl;
    String topic;
    public StateVideo(){
    }


    public StateVideo(String topic,String videoUrl){
        this.videoUrl = videoUrl;
        this.topic = topic;
    }
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getVideoUrl(){
        return videoUrl;
    }
    public void setVideoUrl(String videoUrl){
        this.videoUrl = videoUrl;
    }
}
