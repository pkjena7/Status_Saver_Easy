package com.example.statussavereasy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;


public class ImageStatusFragment extends Fragment {

    Context context;
    Fragment fragment;
    RecyclerView recyclerView;
    ImageStatusAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_status, container, false);

        context = getActivity();
        fragment = this;

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Status");


        recyclerView = view.findViewById(R.id.image_status_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        readImageFromDevice();
        return view;

    }

    public void readImageFromDevice(){


        String ExternalStorageDirectoryPath = Environment
                .getExternalStorageDirectory()
                .getAbsolutePath();
        String targetPath = ExternalStorageDirectoryPath + "/WhatsApp/Media/.Statuses";
        ArrayList<String> images = new ArrayList();
        File targetDirector = new File(targetPath);

        File[] files = targetDirector.listFiles();
        for (File file : files) {
            String filePath = file.getPath();
            if(filePath.endsWith(".jpg"))
            images.add(file.getAbsolutePath());
        }
        adapter = new ImageStatusAdapter(images, getActivity());
        recyclerView.setAdapter(adapter);

    }


}