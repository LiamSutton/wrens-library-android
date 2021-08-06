package com.ls.wrenslibrary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddBookFragment extends Fragment {

    public AddBookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddBookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddBookFragment newInstance(String param1, String param2) {
        AddBookFragment fragment = new AddBookFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    EditText isbnET;
    TextView bookTitleTV;
    TextView bookAuthorTV;
    TextView bookDatePublishedTV;
    ImageView bookCoverIV;
    Button isbnSearchBtn;
    Button addBookBtn;
    RequestQueue queue;
    Spinner genreSP;
    List<String> genreNames;
    ArrayAdapter<String> spinnderAdapter;
    BookJsonParser bookJsonParser;
    Context context;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        queue = Volley.newRequestQueue(getContext());
        isbnET = (EditText)view.findViewById(R.id.isbn_input_et);
        bookTitleTV = (TextView)view.findViewById(R.id.tv_book_name);
        bookAuthorTV = (TextView)view.findViewById(R.id.tv_book_author);
        genreSP = (Spinner)view.findViewById(R.id.sp_genres);
        bookDatePublishedTV = (TextView)view.findViewById(R.id.tv_book_published_date);
        bookCoverIV = (ImageView)view.findViewById(R.id.iv_cover_image);
        isbnSearchBtn = (Button)view.findViewById(R.id.isbn_search_btn);
        addBookBtn = (Button)view.findViewById(R.id.btn_add_book);
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                genreNames = LibraryDatabase.getInstance(getContext()).genreDao().getAllGenreNames();
                spinnderAdapter = new ArrayAdapter<>(getContext(), R.layout.custom_spinner_layout, genreNames);
                spinnderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                genreSP.setAdapter(spinnderAdapter);
                spinnderAdapter.notifyDataSetChanged();
             }
        });
        genreSP.setSelection(0);

        isbnSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performBookLookup();
            }
        });

        addBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "ADDING BOOK", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void performBookLookup() {
        String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbnET.getText();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {

            try {
                bookJsonParser = new BookJsonParser(response);
                bookTitleTV.setText(bookJsonParser.getBookName());
                bookAuthorTV.setText(bookJsonParser.getBookAuthor());
                Picasso.get().load(bookJsonParser.getBookCoverImageUrl()).into(bookCoverIV);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            }, error -> {
            // TODO: Handle error
         });

        queue.add(jsonObjectRequest);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_book, container, false);
    }
}