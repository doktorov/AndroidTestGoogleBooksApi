package ru.doktorov.test2.ui.searchbook;

import javax.inject.Inject;

public class SearchBookFragmentPresenter {
    private SearchBookFragmentView searchBookFragmentView;

    @Inject
    public SearchBookFragmentPresenter(SearchBookFragmentView searchBookFragmentView) {
        this.searchBookFragmentView = searchBookFragmentView;

        searchBookFragmentView.onDetailFragmentLoaded();
    }
}
