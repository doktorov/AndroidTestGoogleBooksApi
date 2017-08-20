package ru.doktorov.test2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BookDB";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOK_TABLE = "CREATE TABLE " + DBService.TABLE_BOOKS + " ( " +
                DBService.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBService.KEY_ID_GOOGLE + " TEXT, "+
                DBService.KEY_THUMBNAIL + " TEXT, "+
                DBService.KEY_TITLE + " TEXT, "+
                DBService.KEY_AUTHORS + " TEXT, "+
                DBService.KEY_PREVIEW_LINK + " TEXT )";

        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBService.TABLE_BOOKS);

        this.onCreate(db);
    }
}
