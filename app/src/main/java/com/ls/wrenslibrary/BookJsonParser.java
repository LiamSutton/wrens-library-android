package com.ls.wrenslibrary;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BookJsonParser {
    private String bookName;
    private String bookAuthor;
    private String bookCoverImageUrl;

    public BookJsonParser(JSONObject data) throws JSONException {
        JSONArray dataArray = data.getJSONArray("items");
        JSONObject dataObject = dataArray.getJSONObject(0);

        JSONObject volumeInfo = dataObject.getJSONObject("volumeInfo");
        JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");

        String bookCover = imageLinks.getString("thumbnail");

        this.bookName = volumeInfo.getString("title");
        this.bookAuthor = volumeInfo.getString("authors");
        this.bookAuthor = bookAuthor.substring(2, this.bookAuthor.length() - 2);
        this.bookCoverImageUrl = bookCover.replace("http", "https");
    }

    public String getBookName() {
        return this.bookName;
    }

    public String getBookAuthor() {
        return this.bookAuthor;
    }

    public String getBookCoverImageUrl() {
        return this.bookCoverImageUrl;
    }
}
