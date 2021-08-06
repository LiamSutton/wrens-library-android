package com.ls.wrenslibrary;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.util.Date;

@Entity(tableName = "book")
public class Book {
    @PrimaryKey(autoGenerate = true)
    private int b_id;

    @ColumnInfo(name = "b_name")
    private String b_name;

    @ColumnInfo(name = "b_author_id")
    private int b_author_id;

    @ColumnInfo(name = "b_genre_id")
    private int b_genre_id;

    @ColumnInfo(name = "b_cover")
    private String b_cover;

    @ColumnInfo(name = "b_has_read")
    private boolean b_has_read;

    @ColumnInfo(name = "b_date_added")
    private Date b_date_added;

    public Book(int b_id, String b_name, int b_author_id, int b_genre_id, String b_cover, boolean b_has_read, Date b_date_added) {
        this.b_id = b_id;
        this.b_name = b_name;
        this.b_author_id = b_author_id;
        this.b_genre_id = b_genre_id;
        this.b_cover = b_cover;
        this.b_has_read = b_has_read;
        this.b_date_added = b_date_added;
    }

    @Ignore
    public Book(String b_name, int b_author_id, int b_genre_id, String b_cover, boolean b_has_read, Date b_date_added) {
        this.b_name = b_name;
        this.b_author_id = b_author_id;
        this.b_genre_id = b_genre_id;
        this.b_cover = b_cover;
        this.b_has_read = b_has_read;
        this.b_date_added = b_date_added;
    }

    public int getB_id() {
        return b_id;
    }

    public String getB_name() {
        return b_name;
    }

    public int getB_author_id() {
        return b_author_id;
    }

    public int getB_genre_id() {
        return b_genre_id;
    }

    public String getB_cover() {
        return b_cover;
    }

    public boolean isB_has_read() {
        return b_has_read;
    }

    public Date getB_date_added() {
        return b_date_added;
    }
}
