package com.ls.wrenslibrary;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "author")
public class Author {
    @PrimaryKey(autoGenerate = true)
    private int a_id;

    @ColumnInfo(name = "a_name")
    private String a_name;

    public Author(int a_id, String a_name) {
        this.a_id = a_id;
        this.a_name = a_name;
    }

    @Ignore
    public Author(String a_name) {
        this.a_name = a_name;
    }

    public int getA_id() {
        return a_id;
    }

    public String getA_name() {
        return a_name;
    }
}
