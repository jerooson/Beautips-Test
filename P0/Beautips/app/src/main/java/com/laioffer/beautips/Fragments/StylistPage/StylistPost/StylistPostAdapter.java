package com.laioffer.beautips.Fragments.StylistPage.StylistPost;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.laioffer.beautips.Models.Post;

import java.util.ArrayList;
import java.util.List;

import com.laioffer.beautips.R;
import com.laioffer.beautips.Utils.GlideApp;
import com.laioffer.beautips.databinding.StylistPostBinding;
import com.squareup.picasso.Picasso;

/*

 */
public class StylistPostAdapter extends RecyclerView.Adapter<StylistPostAdapter.StylistPostViewHolder> {

    private Context context;
    private List<Post> posts;



    public StylistPostAdapter(Context context, ArrayList<Post> postList) {

        this.context = context;
        this.posts = postList;
    }

    
    public void setPosts(List<Post> newsList) {
        posts.clear();
        posts.addAll(newsList);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public StylistPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stylist_post, parent, false);
        return new StylistPostViewHolder(view);
    }


    /*
    This method change the content of each of our ViewHolder to different values
     */
    @Override
    public void onBindViewHolder(@NonNull StylistPostViewHolder holder, int position) {

        Post postImage = posts.get(position);
        //Load Post Image
        GlideApp.with(holder.itemView)
                .load(postImage.getImageUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                .fitCenter()
                .into(holder.post);
        // modify all the text view
        holder.likeCount.setText(String.valueOf(postImage.getNumOfLikes()));
        holder.stylistName.setText(postImage.getOwnerId());

    }

    private int getImage(String imageName) {
        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        return drawableResourceId;
    }


    @Override
    public int getItemCount() {
        return posts.size();
    }



    /*
    This is the holder class for an individual post item;
     */
    public static class StylistPostViewHolder extends RecyclerView.ViewHolder {


        ImageView post;
        TextView stylistName;
        TextView likeCount;
        ImageView thumbsUp;
        ImageView stylistPic;

        public StylistPostViewHolder(@NonNull View itemView) {
            super(itemView);
            StylistPostBinding binding = StylistPostBinding.bind(itemView);
            post = binding.postPic;
            stylistName = binding.stylistNamePost;
            likeCount = binding.numThums;
            thumbsUp = binding.thumb;
            stylistPic = binding.stylistProfileImage2;
        }
    }

}
