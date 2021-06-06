package com.ls.wrenslibrary;

import android.content.Context;
import android.os.strictmode.InstanceCountViolation;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

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
                }
            }
        }
        return INSTANCE;
    }
}
