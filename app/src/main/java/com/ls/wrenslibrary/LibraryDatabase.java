package com.ls.wrenslibrary;

import android.content.Context;
import android.os.strictmode.InstanceCountViolation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = {Author.class, Genre.class}, version = 1)
public abstract class LibraryDatabase extends RoomDatabase {
    public abstract AuthorDao authorDao();

    public abstract GenreDao genreDao();

    private static volatile  LibraryDatabase INSTANCE;

    static LibraryDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (LibraryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), LibraryDatabase.class, "wrens_library").build();
                    INSTANCE.PopulateInitialData();
                }
            }
        }
        return INSTANCE;
    }


    private void PopulateInitialData() {
        if (genreDao().Count() == 0) {
            runInTransaction(new Runnable() {
                @Override
                public void run() {
                    Genre genre = new Genre();
                    for (int i = 0; i < Genre.genres.length; i++) {
                        genre.setG_name(Genre.genres[i]);
                        genreDao().insertGenre(genre);
                    }
                }
            });
        }
    }
}
