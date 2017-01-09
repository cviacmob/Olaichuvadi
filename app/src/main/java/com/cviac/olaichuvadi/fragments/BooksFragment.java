package com.cviac.olaichuvadi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.BooksAdapter;
import com.cviac.olaichuvadi.datamodels.BooksInfo;

import java.util.ArrayList;
import java.util.List;

public class BooksFragment extends Fragment {
    GridView gv;
    List<BooksInfo> books;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_books, container, false);

        book_item();

        BooksAdapter adapter = new BooksAdapter(getActivity(), books);
        gv = (GridView) view.findViewById(R.id.booksgrid);
        gv.setAdapter(adapter);
        return view;
    }

    private void book_item() {

        books = new ArrayList<>();

        BooksInfo bo1 = new BooksInfo(R.mipmap.book, "", "", 0);
        books.add(bo1);

        BooksInfo bo2 = new BooksInfo(R.mipmap.ic, "Moments", "Author 1", R.mipmap.pencil);
        books.add(bo2);

        BooksInfo bo3 = new BooksInfo(R.mipmap.ic, "Wings of Fire", "Dr. A.P.J.Abdul Kalam", R.mipmap.pencil);
        books.add(bo3);

        BooksInfo bo4 = new BooksInfo(R.mipmap.ic, "Harry Potter", "J.K.Rowling", R.mipmap.pencil);
        books.add(bo4);

        BooksInfo bo5 = new BooksInfo(R.mipmap.ic, "Book 2", "Author 2", R.mipmap.pencil);
        books.add(bo5);

        BooksInfo bo6 = new BooksInfo(R.mipmap.ic, "Book 3", "Author 3", R.mipmap.pencil);
        books.add(bo6);
    }
}
