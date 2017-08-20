package ru.doktorov.test2.ui.favorites;

import ru.doktorov.test2.model.Books;

public interface FavoritesView {
    void showLoading();

    void hideLoading();

    void showResult(Books books);
}
