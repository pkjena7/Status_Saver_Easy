package com.example.statussavereasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

public class ImageOpenActivity extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_open);

        imageView1 = findViewById(R.id.image);
        imageView2 = findViewById(R.id.share);
        imageView3 = findViewById(R.id.delete);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String resId = bundle.getString("image");
            Glide.with(this)
                    .load(resId)
                    .into(imageView1);

        }
    }
}