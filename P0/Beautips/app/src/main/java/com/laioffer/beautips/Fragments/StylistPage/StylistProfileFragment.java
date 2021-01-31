package com.laioffer.beautips.Fragments.StylistPage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.laioffer.beautips.Fragments.StylistPage.StylistPost.StylistPostViewModel;
import com.laioffer.beautips.R;
import com.laioffer.beautips.Repository.BeautipsViewModelFactory;
import com.laioffer.beautips.Repository.StylistPostRepository;
import com.laioffer.beautips.databinding.FragmentStylistProfileBinding;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;


public class StylistProfileFragment extends Fragment {


    public static ViewPager viewPager;
    private FragmentStylistProfileBinding binding;
    public static TabLayout tabLayout;
    private PostReviewTabAdapter adapter;
    StylistPostViewModel stylistViewModel;
    Context context;



    public StylistProfileFragment() {
        // Required empty public constructor
    }

    /*
    When create, bind with xml file
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StylistPostRepository repository = new StylistPostRepository(getContext());
        stylistViewModel = new ViewModelProvider(this, new BeautipsViewModelFactory(repository))
                .get(StylistPostViewModel.class);

        stylistViewModel
                .getStylistInfo("Abby")
                .observe(
                        getViewLifecycleOwner(),
                        response -> {
                            if (response != null) {
                                Log.d("TestResult", response.toString());
                                //Binding set text
                                binding.age.setText("#" + String.valueOf(response.getAge()) + " years");
                                binding.numCustomers.setText(String.valueOf(response.getNumOfCustomers()));
                                binding.bodyShape.setText("#" + response.getBodyShape() + " Shape");
                                binding.size.setText("#" + response.getSize());
                                binding.numLikes.setText(String.valueOf(response.getNumOfLikes()));
                                binding.stylistName.setText(response.getName());
                                binding.stylistTitle.setText(response.getTitle());
                                binding.numsFollows.setText(String.valueOf(response.getNumOfFollowers()));
                                binding.textView17numReview.setText(String.valueOf(response.getNumOfReviews()) + " reviews");

                                Picasso.get()
                                        .load(response.getProfileImageUrl())
                                        .noFade().into(binding.stylistImage);

                                Picasso.get()
                                        .load(response.getProfileImageUrl())
                                        .noFade().into((ImageView) getView().findViewById(R.id.stylist_profile_image2));
                            }
                        });


        viewPager = binding.viewPager;
        viewPager.setOffscreenPageLimit(2);
        tabLayout = binding.tab;
        createTabFragment();

    }

    private void createTabFragment(){
        adapter = new PostReviewTabAdapter(getActivity().getSupportFragmentManager(), tabLayout);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }



    }
