package ru.doktorov.test2.ui.search;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.doktorov.test2.app.AndroidTestApplication;
import ru.doktorov.test2.db.DBService;
import ru.doktorov.test2.model.Book;
import ru.doktorov.test2.model.Books;
import ru.doktorov.test2.model.gson.GsonBooks;
import ru.doktorov.test2.network.GoogleApi;

public class SearchPresenterImpl implements SearchPresenter {
    private SearchListView mView;

    @Inject
    DBService mDBService;

    @Inject
    GoogleApi mGoogleApi;

    public SearchPresenterImpl() {
        AndroidTestApplication.getAppComponent().inject(this);
    }

    @Override
    public void setView(SearchListView view) {
        this.mView = view;
    }

    @Override
    public void searchBooks(String query) {
        mView.showLoading();

        mGoogleApi.getBooks(query).enqueue(new Callback<GsonBooks>() {
            @Override
            public void onResponse(@NonNull Call<GsonBooks> call, @NonNull Response<GsonBooks> response) {

                if (response.code() != 200) {
                    mView.showError();
                } else {
                    if (response.body() != null) {
                        onLoadingBooksSuccess(response.body());
                    }
                }

                mView.hideLoading();
            }

            @Override
            public void onFailure(@NonNull Call<GsonBooks> call, @NonNull Throwable t) {
                mView.showError();
                mView.hideLoading();
            }
        });
    }

    @Override
    public void searchBooksNext(String query, int next) {
        mGoogleApi.getBooksNext(query, next).enqueue(new Callback<GsonBooks>() {
            @Override
            public void onResponse(@NonNull Call<GsonBooks> call, @NonNull Response<GsonBooks> response) {

                if (response.code() != 200) {
                    mView.showError();
                } else {
                    if (response.body() != null) {
                        onLoadingBooksNextSuccess(response.body());
                    }
                }

                mView.hideLoading();
            }

            @Override
            public void onFailure(@NonNull Call<GsonBooks> call, @NonNull Throwable t) {
                mView.showError();
                mView.hideLoading();
            }
        });
    }

    private Books convertData(GsonBooks gsonBooks) {
        List<Book> books = new ArrayList<>();

        for (GsonBooks.Item item : gsonBooks.items) {
            Book book = new Book();
            book.setIdGoogle(item.id);
            book.setThumbnail(item.volumeInfo.imageLinks.thumbnail);
            book.setTitle(item.volumeInfo.title);
            if (item.volumeInfo.authors != null) {
                book.setAuthors(TextUtils.join(", ", item.volumeInfo.authors));
            }
            book.setPreviewLink(item.volumeInfo.previewLink);

            books.add(book);
        }

        Books booksResult = new Books();
        booksResult.setTotalItems(gsonBooks.totalItems);
        booksResult.setBooks(books);

        return booksResult;
    }

    private void onLoadingBooksSuccess(GsonBooks gsonBooks) {
        mView.hideLoading();
        mView.showResult(convertData(gsonBooks));
    }

    private void onLoadingBooksNextSuccess(GsonBooks gsonBooks) {
        mView.hideLoading();
        mView.showNextResult(convertData(gsonBooks));
    }

    @Override
    public void addToFavorites(Book book) {
        if (mDBService.getBook(book.getIdGoogle()).getId() == 0) {
            mDBService.addBook(book);
        }
    }
}
