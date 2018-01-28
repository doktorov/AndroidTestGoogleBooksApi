package ru.doktorov.test2.ui.searchbook;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import ru.doktorov.test2.R;
import ru.doktorov.test2.model.Book;
import ru.doktorov.test2.model.Books;
import ru.doktorov.test2.ui.BaseDaggerFragment;
import ru.doktorov.test2.ui.adapter.OnItemClickListener;
import ru.doktorov.test2.ui.adapter.OnLoadMoreListener;
import ru.doktorov.test2.ui.search.SearchListAdapter;

public class SearchBookFragment extends BaseDaggerFragment implements SearchBookFragmentView, OnLoadMoreListener, OnItemClickListener {

    @Inject
    SearchBookFragmentPresenterImpl mSearchBookFragmentPresenterImpl;

    @BindView(R.id.search_view)
    SearchView mSearchView;

    @BindView(R.id.progress)
    View mProgressView;

    @BindView(R.id.books_list)
    RecyclerView mBooksList;

    private SearchListAdapter mSearchListAdapter;

    private String mQuery;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mUnBinder = ButterKnife.bind(this, view);

        mSearchBookFragmentPresenterImpl.setView(this);

        final Activity activity = this.getActivity();

        Disposable sProfileEditPersonal = RxSearchView.queryTextChanges(mSearchView).subscribe(aVoid -> {
            mQuery = aVoid.toString();

            if (!TextUtils.isEmpty(mQuery)) {
                mSearchBookFragmentPresenterImpl.searchBooks(mQuery);
            } else {
                mBooksList.setVisibility(View.GONE);
            }
        });

        mCompositeDisposable.add(sProfileEditPersonal);

        LinearLayoutManager mLayout = new LinearLayoutManager(activity);
        mBooksList.setLayoutManager(mLayout);
        mLayout.setOrientation(LinearLayoutManager.VERTICAL);
        mSearchListAdapter = new SearchListAdapter(mBooksList, this, this);
        mBooksList.setAdapter(mSearchListAdapter);
    }

    @Override
    public void onSearchBookFragmentLoaded() {
        mQuery = "";
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showResult(Books books) {

    }

    @Override
    public void showNextResult(Books books) {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onAddFavorites(Book book) {

    }
}
