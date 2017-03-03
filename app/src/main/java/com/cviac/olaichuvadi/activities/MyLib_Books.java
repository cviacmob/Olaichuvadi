package com.cviac.olaichuvadi.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.GridView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.BooksAdapter;
import com.cviac.olaichuvadi.datamodels.BooksInfo;

import java.util.ArrayList;
import java.util.List;

public class MyLib_Books extends AppCompatActivity {

    GridView gv;
    List<BooksInfo> lib_books;
    BooksAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lib__books);

        loadbooks();

        setTitle(R.string.tab_books);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gv = (GridView) findViewById(R.id.booksgrd);
        gv.setFastScrollEnabled(true);
        adapter = new BooksAdapter(MyLib_Books.this, lib_books);
        gv.setAdapter(adapter);
    }

    private void loadbooks() {

        lib_books = new ArrayList<>();

        BooksInfo b1 = new BooksInfo("Book 1");
        lib_books.add(b1);
        BooksInfo b2 = new BooksInfo("Book 2");
        lib_books.add(b2);
        BooksInfo b3 = new BooksInfo("Book 3");
        lib_books.add(b3);
        BooksInfo b4 = new BooksInfo("Book 4");
        lib_books.add(b4);
        BooksInfo b5 = new BooksInfo("Book 5");
        lib_books.add(b5);
        BooksInfo b6 = new BooksInfo("Book 6");
        lib_books.add(b6);
        BooksInfo b7 = new BooksInfo("Book 7");
        lib_books.add(b7);
        BooksInfo b8 = new BooksInfo("Book 8");
        lib_books.add(b8);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}