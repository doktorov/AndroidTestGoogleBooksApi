package ru.doktorov.test2.ui.search;

import ru.doktorov.test2.model.Book;

public interface SearchPresenter {
    void setView(SearchListView view);

    void searchBooks(String query);

    void searchBooksNext(String query, int next);

    void addToFavorites(Book book);
}
