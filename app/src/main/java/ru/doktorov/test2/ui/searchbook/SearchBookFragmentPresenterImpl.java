package ru.doktorov.test2.ui.searchbook;

import javax.inject.Inject;

import ru.doktorov.test2.model.Book;

public class SearchBookFragmentPresenterImpl implements SearchBookFragmentPresenter {
    private SearchBookFragmentView mSearchBookFragmentView;

    @Inject
    public SearchBookFragmentPresenterImpl(SearchBookFragmentView searchBookFragmentView) {
        this.mSearchBookFragmentView = searchBookFragmentView;

        this.mSearchBookFragmentView.onSearchBookFragmentLoaded();
    }

    @Override
    public void setView(SearchBookFragmentView view) {

    }

    @Override
    public void searchBooks(String query) {

    }

    @Override
    public void searchBooksNext(String query, int next) {

    }

    @Override
    public void addToFavorites(Book book) {

    }
}
