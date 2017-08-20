package ru.doktorov.test2.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import ru.doktorov.test2.app.AndroidTestApplication;
import ru.doktorov.test2.model.Book;

public class DBService {
    static final String TABLE_BOOKS = "books";

    static final String KEY_ID = "id";
    static final String KEY_ID_GOOGLE = "id_google";
    static final String KEY_THUMBNAIL = "thumbnail";
    static final String KEY_TITLE = "title";
    static final String KEY_AUTHORS = "authors";
    static final String KEY_PREVIEW_LINK = "preview_link";

    private static final String[] COLUMNS = {KEY_ID};

    private static final String WHERE_ID_GOOGLE = KEY_ID_GOOGLE + "=?";

    public DBService() {

    }

    public void addBook(Book book) {
        SQLiteDatabase db = AndroidTestApplication.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_GOOGLE, book.getIdGoogle());
        values.put(KEY_THUMBNAIL, book.getThumbnail());
        values.put(KEY_TITLE, book.getTitle());
        values.put(KEY_AUTHORS, book.getAuthors());
        values.put(KEY_PREVIEW_LINK, book.getPreviewLink());

        db.insert(TABLE_BOOKS, null, values);
        db.close();
    }

    public Book getBook(String google_id) {
        SQLiteDatabase db = AndroidTestApplication.getReadableDatabase();

        Cursor cursor =
                db.query(TABLE_BOOKS,
                        COLUMNS,
                        WHERE_ID_GOOGLE,
                        new String[]{String.valueOf(google_id)},
                        null,
                        null,
                        null,
                        null);

        final int ST_ID_INDEX = cursor.getColumnIndex(KEY_ID);

        Book book = new Book();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            book.setId(cursor.getInt(ST_ID_INDEX));

            cursor.moveToNext();
        }

        cursor.close();
        db.close();

        return book;
    }

    public List<Book> getBooks() {
        List<Book> books = new LinkedList<>();

        String query = "SELECT  * FROM " + TABLE_BOOKS;

        SQLiteDatabase db = AndroidTestApplication.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        final int ST_ID_INDEX = cursor.getColumnIndex(KEY_ID);
        final int ST_ID_GOOGLE_INDEX = cursor.getColumnIndex(KEY_ID_GOOGLE);
        final int ST_THUMBNAIL_INDEX = cursor.getColumnIndex(KEY_THUMBNAIL);
        final int ST_TITLE_INDEX = cursor.getColumnIndex(KEY_TITLE);
        final int ST_AUTHORS_INDEX = cursor.getColumnIndex(KEY_AUTHORS);
        final int ST_PREVIEW_LINK_INDEX = cursor.getColumnIndex(KEY_PREVIEW_LINK);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Book book = new Book();
            book.setId(cursor.getInt(ST_ID_INDEX));
            book.setIdGoogle(cursor.getString(ST_ID_GOOGLE_INDEX));
            book.setThumbnail(cursor.getString(ST_THUMBNAIL_INDEX));
            book.setTitle(cursor.getString(ST_TITLE_INDEX));
            book.setAuthors(cursor.getString(ST_AUTHORS_INDEX));
            book.setPreviewLink(cursor.getString(ST_PREVIEW_LINK_INDEX));
            books.add(book);

            cursor.moveToNext();
        }

        cursor.close();
        db.close();

        return books;
    }

    public void deleteBook(Book book) {
        SQLiteDatabase db = AndroidTestApplication.getWritableDatabase();

        db.execSQL("delete from " + TABLE_BOOKS + " where id=" + book.getId());

        db.close();
    }
}
