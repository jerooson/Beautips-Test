package com.laioffer.beautips.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.laioffer.beautips.databinding.FragmentTestfrag2Binding;
import com.laioffer.beautips.databinding.FragmentTestfragBinding;

public class testFrag2 extends Fragment {

    FragmentTestfrag2Binding binding;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = binding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
