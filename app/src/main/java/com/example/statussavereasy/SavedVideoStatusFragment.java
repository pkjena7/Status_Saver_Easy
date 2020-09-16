package com.example.statussavereasy;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;


public class SavedVideoStatusFragment extends Fragment {

    Context context;
    Fragment fragment;
    RecyclerView recyclerView;
    SavedVideoStatusAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_video_status, container, false);

        context = getActivity();
        fragment = this;

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Saved Status");


        recyclerView = view.findViewById(R.id.saved_video_status_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        readVideoFromDevice();
        return view;
    }

    public void readVideoFromDevice(){


        String ExternalStorageDirectoryPath = Environment
                .getExternalStorageDirectory()
                .getAbsolutePath();
        String targetPath = ExternalStorageDirectoryPath + "/WhatsApp/Media/video77";
        ArrayList<String> saved_videos = new ArrayList();
        File targetDirector = new File(targetPath);

        File[] files = targetDirector.listFiles();
        for (File file : files) {
            String filePath = file.getPath();
            if(filePath.endsWith(".mp4"))
                saved_videos.add(file.getAbsolutePath());
        }
        adapter = new SavedVideoStatusAdapter(saved_videos, getActivity());
        recyclerView.setAdapter(adapter);

    }
}