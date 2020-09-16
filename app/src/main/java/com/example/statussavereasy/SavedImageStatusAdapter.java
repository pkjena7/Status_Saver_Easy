package com.example.statussavereasy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SavedImageStatusAdapter extends RecyclerView.Adapter<SavedImageStatusAdapter.ViewHolder> {

    ArrayList<String> saved_images;
    Context context;

    public SavedImageStatusAdapter(ArrayList<String> saved_images, Context context) {
        this.saved_images = saved_images;
        this.context = context;
    }

    @NonNull
    @Override
    public SavedImageStatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_image_item, parent, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SavedImageStatusAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(saved_images.get(position))
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                context.startActivity(intent);

                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/external/images/media/16")));
            }
        });
    }

    @Override
    public int getItemCount() {
        return saved_images.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.saved_image_status);
        }
    }
}
