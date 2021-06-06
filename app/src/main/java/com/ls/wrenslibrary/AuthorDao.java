package com.ls.wrenslibrary;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AuthorDao {
    @Query("SELECT * FROM author")
    List<Author> getAll();

    @Query("SELECT a_id FROM author WHERE a_name = (:a_name)")
    Author getAuthorIdByName(String a_name);

    @Insert
    void insertAuthor(Author author);
}
