package ru.doktorov.test2.ui.favorites;

import java.util.List;

import javax.inject.Inject;

import ru.doktorov.test2.app.AndroidTestApplication;
import ru.doktorov.test2.db.DBService;
import ru.doktorov.test2.model.Book;
import ru.doktorov.test2.model.Books;

public class FavoritesPresenterImpl implements FavoritesPresenter {
    private FavoritesView mView;

    @Inject
    DBService mDBService;

    public FavoritesPresenterImpl() {
        AndroidTestApplication.getAppComponent().inject(this);
    }

    @Override
    public void setView(FavoritesView view) {
        this.mView = view;
    }

    @Override
    public void loadData() {
        mView.showLoading();

        List<Book> bookList = mDBService.getBooks();

        Books books = new Books();
        books.setBooks(bookList);

        mView.hideLoading();
        mView.showResult(books);
    }

    @Override
    public void removeFromFavorites(Book book) {
        mView.showLoading();

        mDBService.deleteBook(book);

        loadData();
    }
}
