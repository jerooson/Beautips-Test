package com.laioffer.beautips.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.laioffer.beautips.Fragments.StylistPage.StylistList.StylistListViewModel;
import com.laioffer.beautips.Fragments.StylistPage.StylistPost.StylistPostViewModel;

public class BeautipsViewModelFactory implements ViewModelProvider.Factory {

//    private final StylistPostRepository repository;
//
//    public BeautipsViewModelFactory(StylistPostRepository repository) {
//        this.repository = repository;
//    }
//
//    @NonNull
//    @Override
//    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//        if (modelClass.isAssignableFrom(StylistPostViewModel.class)) {
//            return (T) new StylistPostViewModel(repository);
//        }else {
//            throw new IllegalStateException("Unknown ViewModel");
//        }
//    }
    private final StylistPostRepository repository;
    private final StylistListRepository listRepository;

    public BeautipsViewModelFactory(StylistPostRepository repository, StylistListRepository listRepository) {
        this.repository = repository;
        this.listRepository = listRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(StylistPostViewModel.class)) {
            return (T) new StylistPostViewModel(repository);
        } else if (modelClass.isAssignableFrom(StylistListViewModel.class)) {
            return (T) new StylistListViewModel(listRepository);
        } else {
            throw new IllegalStateException("Unknown ViewModel");
        }
    }

}
