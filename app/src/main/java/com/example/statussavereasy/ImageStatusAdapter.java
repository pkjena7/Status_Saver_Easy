package com.example.statussavereasy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

public class ImageStatusAdapter extends RecyclerView.Adapter<ImageStatusAdapter.ViewHolder> {

    ArrayList<String> images;
    Context context;

    public ImageStatusAdapter(ArrayList<String> images, Context context) {
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageStatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_status_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageStatusAdapter.ViewHolder holder, final int position) {
        Glide.with(context)
                .load(images.get(position))
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String image = images.get(position);
                File f = new File(image);
                String fname = f.getName();

                Intent intent = new Intent(context,ImageOpenActivity.class);
                intent.putExtra("image", fname);
                context.startActivity(intent);
            }
        });


        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String image = images.get(position);
                Bitmap bitmap = BitmapFactory.decodeFile(image);
                String ExternalStorageDirectoryPath = Environment
                        .getExternalStorageDirectory()
                        .getAbsolutePath();
                String targetPath = ExternalStorageDirectoryPath + "/WhatsApp/Media/";
                File dir = new File(targetPath + "/pupun/");
                dir.mkdirs();
              // Random generator = new Random();
              //  int n = 10000;
              //  n = generator.nextInt(n);
              //  String fname = "Image-" + n + ".jpg";
                File f = new File(image);
                String fname = f.getName();
                File file = new File(dir, fname);
                if (file.exists())
                    file.delete();
                    Toast.makeText(context, "Image Already Downloaded", Toast.LENGTH_SHORT).show();

                try {
                    FileOutputStream out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();
                    Toast.makeText(context, "Download Successful", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_status);
            button = itemView.findViewById(R.id.download);
        }
    }

}
