package ru.doktorov.test2.ui.search;

import ru.doktorov.test2.model.Books;

interface SearchListView {
    void showLoading();

    void hideLoading();

    void showError();

    void showResult(Books books);

    void showNextResult(Books books);
}
