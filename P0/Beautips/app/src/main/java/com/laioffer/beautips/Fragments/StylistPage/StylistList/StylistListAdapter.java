package com.laioffer.beautips.Fragments.StylistPage.StylistList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.beautips.Models.Stylist;
import com.laioffer.beautips.R;
import com.laioffer.beautips.databinding.SingleStylistListBinding;

import java.util.ArrayList;
import java.util.List;

public class StylistListAdapter extends RecyclerView.Adapter<StylistListAdapter.StylistListViewHolder>{

    // 1. supporting data
    private List<Stylist> stylistList = new ArrayList<>();
    private ItemCallback itemCallback;

    public StylistListAdapter(ArrayList<Stylist> stylistList) {
        this.stylistList = stylistList;
        notifyDataSetChanged();
    }

    public StylistListAdapter() {

    }


    interface ItemCallback {
        void onOpenDetails(Stylist stylistList);
    }

    public void setItemCallback(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
    }


    // 2. adapter overrides
    @NonNull
    @Override
    public StylistListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // follow the pattern
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_stylist_list, parent, false);
        return new StylistListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StylistListViewHolder holder, int position) {
        Stylist list = stylistList.get(position);
        holder.stylistName.setText(list.getName());
        holder.numsReview.setText(list.getNumOfReviews());
        holder.stylustTitle.setText(list.getTitle());
        //holder.itemView.setOnClickListener(v -> ItemCallback.onOpenDetails(list));

    }

    @Override
    public int getItemCount() {
        return stylistList.size();
    }


    // 3. view holder
    public static class StylistListViewHolder extends RecyclerView.ViewHolder {

        TextView stylistName;
        ImageView stylustImage;
        TextView stylustTitle;
        TextView numsReview;
        RatingBar ratingBar;

        public StylistListViewHolder(@NonNull View itemView) {
            super(itemView);
            SingleStylistListBinding binding = SingleStylistListBinding.bind(itemView);
            stylistName = binding.name;
            stylustImage = binding.stylistImage;
            stylustTitle = binding.stylistTitle;
            numsReview = binding.reviews;
            ratingBar = binding.ratingBar;


        }
    }

}
