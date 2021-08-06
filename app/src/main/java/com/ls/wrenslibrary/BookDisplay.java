package com.ls.wrenslibrary;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity
public class BookDisplay {
    @PrimaryKey
    private int b_id;
    private String bd_name;
    private String bd_genre;
    private String b_cover;
    private boolean b_has_read;
    private Date b_date_added;
    private String b_isbn;

    public BookDisplay(int b_id, String bd_name, String bd_genre, String b_cover, boolean b_has_read, Date b_date_added, String b_isbn) {
        this.b_id = b_id;
        this.bd_name = bd_name;
        this.bd_genre = bd_genre;
        this.b_cover = b_cover;
        this.b_has_read= b_has_read;
        this.b_date_added = b_date_added;
        this.b_isbn = b_isbn;
    }

    public int getB_id() {
        return b_id;
    }

    public String getBd_name() {
        return bd_name;
    }

    public String getBd_genre() {
        return bd_genre;
    }

    public String getB_cover() {
        return b_cover;
    }

    public boolean getB_has_read() {
        return b_has_read;
    }

    public Date getB_date_added() {
        return b_date_added;
    }

    public String getB_isbn() { return this.b_isbn; }
}
