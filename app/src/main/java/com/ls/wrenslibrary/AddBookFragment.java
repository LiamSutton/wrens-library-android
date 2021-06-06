package com.ls.wrenslibrary;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddBookFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
//    private static final String[] genres =
//            {       "Action", "Adult Fantasy", "Adventure", "Autobiography", "Biography", "Childrens",
//                    "Classics", "Comedy", "Cookbook", "Dark Fantasy", "Detective", "Dystopian",
//                    "Educational", "Fantasy", "Fiction", "Graphic Novel", "Historical Fiction",
//                    "History", "Horror", "Manga", "Poetry", "Romance", "Science Fiction",
//                    "Self-Help", "Short Stories"
//            };
    EditText isbnET;
    TextView bookTitleTV;
    TextView bookAuthorTV;
    TextView bookGenreTV;
    TextView bookDatePublishedTV;
    ImageView bookCoverIV;
    Button isbnSearchBtn;
    Button addBookBtn;
    RequestQueue queue;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        queue = Volley.newRequestQueue(getContext());
        isbnET = (EditText)view.findViewById(R.id.isbn_input_et);
        bookTitleTV = (TextView)view.findViewById(R.id.tv_book_name);
        bookAuthorTV = (TextView)view.findViewById(R.id.tv_book_author);
        bookGenreTV = (TextView)view.findViewById(R.id.tv_book_genre);
        bookDatePublishedTV = (TextView)view.findViewById(R.id.tv_book_published_date);
        bookCoverIV = (ImageView)view.findViewById(R.id.iv_cover_image);
        isbnSearchBtn = (Button)view.findViewById(R.id.isbn_search_btn);
        addBookBtn = (Button)view.findViewById(R.id.btn_add_book);


        isbnSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performBookLookup();
            }
        });

        addBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Genre g = new Genre("test");
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        LibraryDatabase.getInstance(getContext()).genreDao().insertGenre(g);
                    }
                });
            }
        });
    }
    private void performBookLookup() {
        String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbnET.getText();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
//                        System.out.println(response.toString());
                        try {
                            JSONArray resultArray = response.getJSONArray("items");
                            JSONObject resultObject = resultArray.getJSONObject(0);

                            JSONObject volumeInfo = resultObject.getJSONObject("volumeInfo");
                            JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");

                            String authorName =  volumeInfo.getString("authors");
                            String bookTitle = volumeInfo.getString(("title"));
                            String bookCategory = volumeInfo.getString("categories");
                            String datePublished = volumeInfo.getString("publishedDate");
                            String coverImage = imageLinks.getString("thumbnail");
                            String altered = coverImage.replace("http", "https");
                            String test = "https://www.google.com";
                            System.out.println(test);
                            authorName = authorName.substring(2,authorName.length()-2);
                            bookCategory = bookCategory.substring(2, bookCategory.length()-2);

                            bookTitleTV.setText(bookTitle);
                            bookAuthorTV.setText(authorName);
                            bookGenreTV.setText(bookCategory);
                            bookDatePublishedTV.setText(datePublished);
                            System.out.println(altered);
                            Picasso.get().load(altered).into(bookCoverIV);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        queue.add(jsonObjectRequest);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_book, container, false);
    }
}