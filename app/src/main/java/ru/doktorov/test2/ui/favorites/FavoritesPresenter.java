package ru.doktorov.test2.ui.favorites;

import ru.doktorov.test2.model.Book;

public interface FavoritesPresenter {
    void setView(FavoritesView view);

    void loadData();

    void removeFromFavorites(Book book);
}
