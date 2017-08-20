package ru.doktorov.test2.model;

import java.util.List;

public class Books {
    private int mTotalItems;
    private List<Book> mBooks;

    public int getTotalItems() {
        return mTotalItems;
    }

    public void setTotalItems(int totalItems) {
        this.mTotalItems = totalItems;
    }

    public List<Book> getBooks() {
        return mBooks;
    }

    public void setBooks(List<Book> books) {
        this.mBooks = books;
    }

    public void addBooks(List<Book> books) {
        this.mBooks.addAll(books);
    }
}
