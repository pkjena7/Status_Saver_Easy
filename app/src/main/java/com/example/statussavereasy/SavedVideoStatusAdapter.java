package com.example.statussavereasy;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SavedVideoStatusAdapter extends RecyclerView.Adapter<SavedVideoStatusAdapter.ViewHolder> {

    ArrayList<String> saved_videos;
    Context context;

    public SavedVideoStatusAdapter(ArrayList<String> saved_videos, Context context) {
        this.saved_videos = saved_videos;
        this.context = context;
    }

    @NonNull
    @Override
    public SavedVideoStatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_video_item, parent, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SavedVideoStatusAdapter.ViewHolder holder, int position) {
        final String data = saved_videos.get(position);
        final Uri uri = Uri.parse(data);
        holder.videoView.setVideoURI(uri);
        //holder.videoView.start();
//        MediaController mediaController = new MediaController(context);
//        holder.videoView.setMediaController(mediaController);
//        mediaController.setAnchorView(holder.videoView);
//        holder.videoView.requestFocus();

    }

    @Override
    public int getItemCount() {
        return saved_videos.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView=itemView.findViewById(R.id.saved_video_status);
        }
    }
}
