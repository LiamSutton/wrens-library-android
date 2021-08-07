package com.ls.wrenslibrary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder> {

    List<BookDisplay> bookDisplayList;

    public LibraryAdapter(List<BookDisplay> bookDisplayList) {
        this.bookDisplayList = bookDisplayList;
    }

    @NonNull
    @Override
    public LibraryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryAdapter.ViewHolder holder, int position) {
        BookDisplay bookDisplay = bookDisplayList.get(position);
        holder.book_title_tv.setText(bookDisplay.getBd_name());
        holder.book_author_tv.setText(bookDisplay.getBd_author());
        holder.book_genre_tv.setText(bookDisplay.getBd_genre());
    }

    @Override
    public int getItemCount() {
        return bookDisplayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView book_cover_iv;
        TextView book_title_tv;
        TextView book_author_tv;
        TextView book_genre_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            book_cover_iv = itemView.findViewById(R.id.book_cover_iv);
            book_title_tv = itemView.findViewById(R.id.book_title_tv);
            book_author_tv = itemView.findViewById(R.id.book_author_tv);
            book_genre_tv = itemView.findViewById(R.id.book_genre_tv);
        }
    }
}
