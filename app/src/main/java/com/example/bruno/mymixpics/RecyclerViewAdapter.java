package com.example.bruno.mymixpics;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bruno.mymixpics.model.Media;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bruno on 12/11/2015.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context context;
    List<Media> data = Collections.emptyList();
    private LayoutInflater inflater;

    public RecyclerViewAdapter(Context context, List<Media> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void setData(List<Media> data) {
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.custom_row, parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Media current = data.get(position);
        Picasso.with(context).load(current.getImages().getStandardResolution().getUrl())
                .into(holder.imageView);
        holder.captionTextView.setText(current.getCaption().getText());
        holder.likesTextView.setText(current.getLikes().getCount().toString() + " likes");
        holder.commentTextView.setText(current.getComments().getCount().toString() + " comments");
        holder.usernameTextView.setText(current.getUser().getUsername());
        Picasso.with(context).load(current.getUser().getProfilePicture()).into(holder.userProfilePic);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.userProfilePic)
        ImageView userProfilePic;
        @Bind(R.id.usernameTextView)
        TextView usernameTextView;
        @Bind(R.id.imageView)
        ImageView imageView;
        @Bind(R.id.captionTextView)
        TextView captionTextView;
        @Bind(R.id.likesTextView)
        TextView likesTextView;
        @Bind(R.id.commentTextView)
        TextView commentTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
