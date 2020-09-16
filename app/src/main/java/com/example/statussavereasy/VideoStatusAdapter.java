package com.example.statussavereasy;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class VideoStatusAdapter extends RecyclerView.Adapter<VideoStatusAdapter.ViewHolder> {

    ArrayList<String> videos;
    Context context;

    public VideoStatusAdapter(ArrayList<String> videos, Context context) {
        this.videos = videos;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoStatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_status_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoStatusAdapter.ViewHolder holder, final int position) {
        final String data = videos.get(position);
        final Uri uri = Uri.parse(data);
        holder.videoView.setVideoURI(uri);
        //holder.videoView.start();
//        MediaController mediaController = new MediaController(context);
//        holder.videoView.setMediaController(mediaController);
//        mediaController.setAnchorView(holder.videoView);
//        holder.videoView.requestFocus();

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String data = videos.get(position);
                final Uri uri = Uri.parse(data);
                //  UUID uuid = UUID.randomUUID();

                try {

                    File currentFile = new File(data);
                    File loc = Environment.getExternalStorageDirectory();
                    File directory = new File(loc.getAbsolutePath() + "/WhatsApp/Media/video77");
                    directory.mkdir();
                    String fname = currentFile.getName();
                    //   String fileName = String.format(uuid + ".mp4");
                    File newfile = new File(directory, fname);
                  //  newfile.delete();


                    if (currentFile.exists()) {


                        InputStream inputStream = new FileInputStream(currentFile);
                        OutputStream outputStream = new FileOutputStream(newfile);

                        byte[] buf = new byte[1024];
                        int len;

                        while ((len = inputStream.read(buf)) > 0) {
                            outputStream.write(buf, 0, len);
                        }

                        outputStream.flush();
                        inputStream.close();
                        outputStream.close();

                        Toast.makeText(context, "Video Downloaded!!", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(context, "Video has failed for saving!!", Toast.LENGTH_LONG).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.video_status);
            button = itemView.findViewById(R.id.download);
        }
    }


}

