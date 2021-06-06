package com.ls.wrenslibrary;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GenreDao {
    @Query("SELECT * FROM genre")
    List<Genre> getAll();

    @Query("SELECT g_id FROM Genre WHERE g_name = (:g_name)")
    Genre getGenreIdByName(String g_name);

    @Insert
    void populateGenres(Genre... genres);

    @Insert
    void insertGenre(Genre genre);
}
