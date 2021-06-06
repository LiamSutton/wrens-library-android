package com.ls.wrenslibrary;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "genre")
public class Genre {

    @PrimaryKey(autoGenerate = true)
    private int g_id;

    @ColumnInfo(name = "g_name")
    private String g_name;

    public Genre(int g_id, String g_name) {
        this.g_id = g_id;
        this.g_name = g_name;
    }

    @Ignore
    public Genre(String g_name) {
        this.g_name = g_name;
    }


    public int getG_id() {
        return g_id;
    }

    public String getG_name() {
        return g_name;
    }
}
