package com.ls.wrenslibrary;

import androidx.room.Dao;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GenreDao {
    @Query("SELECT * FROM genre")
    List<Genre> getAll();

    @Query("SELECT g_id FROM Genre WHERE g_name = (:g_name)")
    Genre getGenreIdByName(String g_name);

    @Query("SELECT g_name from genre")
    List<String> getAllGenreNames();

    @Query("SELECT COUNT(*) FROM genre")
    int Count();

    @Insert
    void populateGenres(Genre... genres);

    @Insert
    void insertGenre(Genre genre);

}
