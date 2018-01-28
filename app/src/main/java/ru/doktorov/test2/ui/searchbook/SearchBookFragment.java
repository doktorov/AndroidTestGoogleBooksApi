package ru.doktorov.test2.ui.searchbook;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import ru.doktorov.test2.R;

public class SearchBookFragment extends DaggerFragment implements SearchBookFragmentView {
    @Inject
    SearchBookFragmentPresenter searchBookFragmentPresenter;

    public static SearchBookFragment newInstance() {
        Bundle args = new Bundle();
        SearchBookFragment fragment = new SearchBookFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_item, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetailFragmentLoaded() {
        String s = "";
    }
}
