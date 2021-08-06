package com.ls.wrenslibrary;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDisplayDao {
    @Query("SELECT b_id, b_name, a_name, g_name, b_cover, b_has_read, b_date_added, b_isbn " +
            "FROM book " +
            "JOIN author on b_author_id = a_id " +
            "JOIN genre on b_genre_id = g_id")
    List<BookDisplay> getAllBooks();
}
