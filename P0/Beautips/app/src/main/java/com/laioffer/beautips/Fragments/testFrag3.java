package com.laioffer.beautips.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.laioffer.beautips.databinding.FragmentTestfrag3Binding;

public class testFrag3 extends Fragment {

    FragmentTestfrag3Binding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = binding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
