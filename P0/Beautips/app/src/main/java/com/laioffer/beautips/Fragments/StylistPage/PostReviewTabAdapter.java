package com.laioffer.beautips.Fragments.StylistPage;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;
import com.laioffer.beautips.Fragments.StylistPage.StylistPost.StylistPostFragment;
import com.laioffer.beautips.Fragments.StylistPage.StylistReview.StylistReviewFragment;
import com.laioffer.beautips.Models.Post;

import java.util.ArrayList;

public class PostReviewTabAdapter extends FragmentStatePagerAdapter {

    TabLayout tabLayout;

    public PostReviewTabAdapter(@NonNull FragmentManager fm, TabLayout _tabLayout) {
        super(fm);
        this.tabLayout = _tabLayout;

    }

    /*
    This method defines how to switch between different tabs
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        //initially the fragment display is null;
        Fragment fragment = null;
        if (position == 0 ){
            fragment = new StylistPostFragment();
        }else if (position == 1){
            fragment = new StylistReviewFragment();
        }

        return fragment;
    }


    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Posts";
        }
        else if (position == 1)
        {
            title = "Reviews";
        }
        return title;
    }
}
