package com.ls.wrenslibrary;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AuthorDao {
    @Query("SELECT * FROM author")
    List<Author> getAll();

    @Query("SELECT * FROM author WHERE a_name = (:a_name)")
    Author getAuthorByName(String a_name);

    @Query("SELECT * FROM author WHERE a_id = (:a_id)")
    Author getAuthorById(int a_id);

    @Insert
    void insertAuthor(Author author);
}
