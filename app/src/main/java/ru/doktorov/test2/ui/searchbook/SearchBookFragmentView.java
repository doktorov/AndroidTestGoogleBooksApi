package ru.doktorov.test2.ui.searchbook;

import ru.doktorov.test2.model.Books;

public interface SearchBookFragmentView {

    void onSearchBookFragmentLoaded();

    void showLoading();

    void hideLoading();

    void showError();

    void showResult(Books books);

    void showNextResult(Books books);
}
