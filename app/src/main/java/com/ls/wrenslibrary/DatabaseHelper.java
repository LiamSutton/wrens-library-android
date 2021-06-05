package com.ls.wrenslibrary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";

    private static final String DATABASE_NAME = "wrensLibrary";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_BOOKS = "books";
    private static final String TABLE_AUTHORS = "authors";
    private static final String TABLE_GENRES = "genres";

    private static final String KEY_BOOK_ID = "b_id";
    private static final String KEY_BOOK_TITLE = "b_title";
    private static final String KEY_BOOK_ISBN = "b_isbn";
    private static final String KEY_BOOK_DATE_PUBLISHED = "b_datepublished";
    private static final String KEY_BOOK_COVER = "b_cover";
    private static final String KEY_BOOK_HAS_READ = "b_hasread";
    private static final String KEY_BOOK_AUTHOR_ID = "b_author_id";
    private static final String KEY_BOOK_GENRE_ID = "b_genre_id";

    private static final String KEY_AUTHOR_ID = "a_id";
    private static final String KEY_AUTHOR_NAME = "a_name";

    private static final String KEY_GENRE_ID = "g_id";
    private static final String KEY_GENRE_NAME = "g_name";

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private static final String CREATE_TABLE_GENRES =
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT)",
                    TABLE_GENRES, KEY_GENRE_ID, KEY_GENRE_NAME);

    private static final String CREATE_TABLE_AUTHORS =
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT)",
                    TABLE_AUTHORS, KEY_AUTHOR_ID, KEY_AUTHOR_NAME);

    private static final String CREATE_TABLE_BOOKS =
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER, %s BOOLEAN, %s TEXT, %s DATETIME)",
                    TABLE_BOOKS, KEY_BOOK_ID, KEY_BOOK_ISBN, KEY_BOOK_AUTHOR_ID, KEY_BOOK_GENRE_ID, KEY_BOOK_HAS_READ, KEY_BOOK_COVER, KEY_BOOK_DATE_PUBLISHED);

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_GENRES);
        sqLiteDatabase.execSQL(CREATE_TABLE_AUTHORS);
        sqLiteDatabase.execSQL(CREATE_TABLE_BOOKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_GENRES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_AUTHORS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
    }
}
