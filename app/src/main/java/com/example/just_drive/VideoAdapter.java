package com.example.just_drive;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {
     List<StateVideo> VideoList;
    public VideoAdapter(){}

    public VideoAdapter(List <StateVideo> youtubeVideoList){
        this.VideoList=youtubeVideoList;
    }

    public void setFilteredeList(List<StateVideo> filteredeList){
        this.VideoList = filteredeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VideoAdapter.VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_videos,parent,false);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.VideoHolder holder, int position) {
       StateVideo videolist = VideoList.get(position);
        holder.videoWeb.loadData(VideoList.get(position).getVideoUrl(),"text/html","utf-8");
        holder.topic.setText(videolist.getTopic());
    }

    @Override
    public int getItemCount() {
        return VideoList.size();
    }


    public class VideoHolder extends RecyclerView.ViewHolder {
        WebView videoWeb;
        TextView topic;
        public VideoHolder(View itemview){
            super(itemview);
            topic = itemview.findViewById(R.id.topic);
            videoWeb = (WebView) itemview.findViewById(R.id.videoview);
            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new WebChromeClient());
        }
    }
}
