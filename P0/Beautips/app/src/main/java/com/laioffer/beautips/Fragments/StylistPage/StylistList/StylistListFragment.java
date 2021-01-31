package com.laioffer.beautips.Fragments.StylistPage.StylistList;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.laioffer.beautips.Fragments.StylistPage.StylistPost.StylistPostViewModel;
import com.laioffer.beautips.Models.Stylist;
import com.laioffer.beautips.Repository.BeautipsViewModelFactory;
import com.laioffer.beautips.Repository.StylistPostRepository;
import com.laioffer.beautips.databinding.FragmentStylistListBinding;

import java.util.ArrayList;

public class StylistListFragment extends Fragment {

    private FragmentStylistListBinding binding;


    private ArrayList<Stylist> StylistList = new ArrayList<>();

    StylistPostViewModel stylistViewModel;


    // construct a view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    // setup parameter
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        StylistListAdapter stylistListAdapter = new StylistListAdapter();
        binding.stylistListRecyclerView.setAdapter(stylistListAdapter);
        binding.stylistListRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        stylistListAdapter.setItemCallback(new StylistListAdapter.ItemCallback() {
            @Override
            public void onOpenDetails(Stylist stylistList) {
                Log.d("onOpenDetails", stylistList.toString());
//                StylistListFragmentDirections.ActionNavigationSaveToNavigationDetails direction;
//                direction = StylistListFragmentDirections.actionNavigationSaveToNavigationDetails(stylistList);
//                NavHostFragment.findNavController(StylistListFragment.this).navigate(direction);
            }
        });


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

                                // BUG ==> binding跟xml对应不上(single_stylist_list.xml)
                                binding.setText(response.getName());
                                binding.stylistTitle.setText(response.getTitle());
                                binding.reviews.setText(String.valueOf(response.getNumOfReviews()) + " reviews");
                            }
                        });


    }
}
