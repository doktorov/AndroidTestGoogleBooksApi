package ru.doktorov.test2.ui.favorites;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.doktorov.test2.R;
import ru.doktorov.test2.app.AndroidTestApplication;
import ru.doktorov.test2.model.Book;
import ru.doktorov.test2.model.Books;
import ru.doktorov.test2.ui.BaseItemFragment;
import ru.doktorov.test2.ui.adapter.OnItemClickListener;

public class FavoritesItemFragment extends BaseItemFragment implements FavoritesView, OnItemClickListener {
    @Inject
    FavoritesPresenter mFavoritesPresenter;

    @BindView(R.id.favorites_list)
    RecyclerView mFavoritesList;

    @BindView(R.id.progress)
    View mProgressView;

    FavoritesListAdapter mSearchListAdapter;

    public static FavoritesItemFragment newInstance() {
        return new FavoritesItemFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidTestApplication.getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites_item, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mUnBinder = ButterKnife.bind(this, view);

        mFavoritesPresenter.setView(this);

        final Activity activity = this.getActivity();

        LinearLayoutManager mLayout = new LinearLayoutManager(activity);
        mFavoritesList.setLayoutManager(mLayout);
        mLayout.setOrientation(LinearLayoutManager.VERTICAL);
        mSearchListAdapter = new FavoritesListAdapter(this);
        mFavoritesList.setAdapter(mSearchListAdapter);

        mFavoritesPresenter.loadData();
    }

    @Override
    public void showLoading() {
        mProgressView.setVisibility(View.VISIBLE);
        mFavoritesList.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        mProgressView.setVisibility(View.GONE);
        mFavoritesList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResult(Books books) {
        mSearchListAdapter.setBooks(books);
    }

    @Override
    public void onAddFavorites(Book book) {
        mFavoritesPresenter.removeFromFavorites(book);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mFavoritesList.setAdapter(null);
    }
}