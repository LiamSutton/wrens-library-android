package com.ls.wrenslibrary;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainLibraryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainLibraryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainLibraryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainLibraryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainLibraryFragment newInstance(String param1, String param2) {
        MainLibraryFragment fragment = new MainLibraryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        return inflater.inflate(R.layout.fragment_main_library, container, false);
    }

    RecyclerView library_rv;
    LibraryAdapter libraryAdapter;
    BookDisplayDao bookDisplayDao;
    List<BookDisplay> bookDisplayList;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Populate database if required.
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                LibraryDatabase.getInstance(getContext());
            }
        });

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                library_rv = view.findViewById(R.id.library_rv);
                library_rv.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                bookDisplayDao = LibraryDatabase.getInstance(getContext()).bookDisplayDao();
                Log.d("BookDisplayDao", "onViewCreated: Retrieved BookDisplayDao");
                bookDisplayList = bookDisplayDao.getAllBooks();
                libraryAdapter = new LibraryAdapter(bookDisplayList);
                library_rv.setAdapter(libraryAdapter);
            }
        });
    }
}