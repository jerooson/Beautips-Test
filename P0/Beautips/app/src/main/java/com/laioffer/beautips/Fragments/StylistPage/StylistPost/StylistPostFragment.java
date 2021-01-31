package com.laioffer.beautips.Fragments.StylistPage.StylistPost;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.beautips.Fragments.StylistPage.PostReviewTabAdapter;
import com.laioffer.beautips.Models.Post;
import com.laioffer.beautips.Models.Stylist;
import com.laioffer.beautips.R;

import com.laioffer.beautips.Repository.BeautipsViewModelFactory;
import com.laioffer.beautips.Repository.StylistPostRepository;
import com.laioffer.beautips.databinding.FragmentStylistProfileBinding;
import com.laioffer.beautips.databinding.ScrollStylistPostsBinding;
import com.laioffer.beautips.databinding.StylistPostBinding;

import java.util.ArrayList;
import java.util.List;

public class StylistPostFragment extends Fragment {


    Context context;
    RecyclerView recyclerView;
    StylistPostAdapter StylistPostAdapter;
    StylistPostViewModel stylistViewModel;
    ScrollStylistPostsBinding binding;
    private List<Post> Posts;



    private ArrayList<com.laioffer.beautips.Models.Post> postList = new ArrayList<>();


    public StylistPostFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          //return inflater.inflate(R.layout.scroll_stylist_posts, container, false);
        binding = binding.inflate(inflater, container, false);
        return binding.getRoot();
    }



    @SuppressLint("LongLogTag")
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.context = getContext();

        //recycler view image show
        recyclerView = view.findViewById(R.id.swipe_post_recycler_view);
        Log.i("size of list",String.valueOf(postList.size()));
        //use grid layout
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));

        StylistPostAdapter = new StylistPostAdapter(context, postList);
        recyclerView.setAdapter(StylistPostAdapter);

        StylistPostRepository repository = new StylistPostRepository(getContext());
        stylistViewModel = new ViewModelProvider(this, new BeautipsViewModelFactory(repository))
                .get(StylistPostViewModel.class);

        stylistViewModel
                .getStylistPosts("Abby")
                .observe(
                        getViewLifecycleOwner(),
                        response -> {
                            if (response != null) {
                                Log.d("TestResult for images for post frag", response.toString());
                                //Binding set text
                                Posts = response;
                                StylistPostAdapter.setPosts(Posts);
                            }
                        });

    }


}