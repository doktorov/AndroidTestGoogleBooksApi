package ru.doktorov.test2.ui.searchbook;

import ru.doktorov.test2.model.Book;

public interface SearchBookFragmentPresenter {
    void setView(SearchBookFragmentView view);

    void searchBooks(String query);

    void searchBooksNext(String query, int next);

    void addToFavorites(Book book);
}
