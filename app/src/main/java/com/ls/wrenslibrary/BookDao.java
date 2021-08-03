package com.ls.wrenslibrary;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {
    @Query("SELECT * FROM book")
    List<Book> getAll();

    @Query("SELECT * FROM book WHERE b_id = (:b_id)")
    Book getBookById(int b_id);

    @Insert
    void insertBook(Book book);
}
