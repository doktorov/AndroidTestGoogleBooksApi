package ru.doktorov.test2.ui.search;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import ru.doktorov.test2.R;
import ru.doktorov.test2.app.AndroidTestApplication;
import ru.doktorov.test2.model.Book;
import ru.doktorov.test2.model.Books;
import ru.doktorov.test2.ui.BaseItemFragment;
import ru.doktorov.test2.ui.adapter.OnItemClickListener;
import ru.doktorov.test2.ui.adapter.OnLoadMoreListener;

public class SearchItemFragment extends BaseItemFragment implements SearchListView, OnLoadMoreListener, OnItemClickListener {
    public static final String ROTATE_QUERY = "rotate_query";

    @Inject
    SearchPresenter mSearchPresenter;

    @BindView(R.id.search_view)
    SearchView mSearchView;

    @BindView(R.id.progress)
    View mProgressView;

    @BindView(R.id.books_list)
    RecyclerView mBooksList;

    private SearchListAdapter mSearchListAdapter;

    private String mQuery;

    public static SearchItemFragment newInstance() {
        return new SearchItemFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidTestApplication.getAppComponent().inject(this);

        mQuery = "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_item, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mUnBinder = ButterKnife.bind(this, view);

        mSearchPresenter.setView(this);

        final Activity activity = this.getActivity();

        Disposable sProfileEditPersonal = RxSearchView.queryTextChanges(mSearchView).subscribe(aVoid -> {
            mQuery = aVoid.toString();

            if (!TextUtils.isEmpty(mQuery)) {
                mSearchPresenter.searchBooks(mQuery);
            } else {
                mBooksList.setVisibility(View.GONE);
            }
        });

        mCompositeDis.add(sProfileEditPersonal);

        LinearLayoutManager mLayout = new LinearLayoutManager(activity);
        mBooksList.setLayoutManager(mLayout);
        mLayout.setOrientation(LinearLayoutManager.VERTICAL);
        mSearchListAdapter = new SearchListAdapter(mBooksList, this, this);
        mBooksList.setAdapter(mSearchListAdapter);

        if (savedInstanceState != null) {
            mQuery = savedInstanceState.getString(ROTATE_QUERY);

            mSearchView.setQuery(mQuery, false);
        }
    }

    @Override
    public void showLoading() {
        mProgressView.setVisibility(View.VISIBLE);
        mBooksList.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        mProgressView.setVisibility(View.GONE);
        mBooksList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        Toast.makeText(AndroidTestApplication.getContext(),
                AndroidTestApplication.getContext().getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(Books books) {
        mSearchListAdapter.setBooks(books);
    }

    @Override
    public void showNextResult(Books books) {
        mSearchListAdapter.setNextBooks(books);
    }

    @Override
    public void onAddFavorites(Book book) {
        mSearchPresenter.addToFavorites(book);
    }

    @Override
    public void onLoadMore() {
        mSearchPresenter.searchBooksNext(mQuery, mSearchListAdapter.getItemCount() + 1);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString(ROTATE_QUERY, mQuery);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mBooksList.setAdapter(null);
    }
}
