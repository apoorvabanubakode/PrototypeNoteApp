package com.example.shreya.prototypenoteapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


public class MainViewAllActivity extends AppCompatActivity {


    private List<NoteRow> noteList = new ArrayList<>();
    private RecyclerView recyclerView;
    private NotesAdapter nAdapter;

    private TextView Title;
    private TextView Timestamp;

    private static final String viewstring = "SELECT * FROM NotesTable";

    private SQLiteDatabase db;

    private Cursor c;

    private List <NoteRow> allnotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_veiw_all);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        nAdapter = new NotesAdapter(noteList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // set the adapter
        recyclerView.setAdapter(nAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                NoteRow movie = noteList.get(position);
                Toast.makeText(getApplicationContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show(); */

            }
        });


        openDb();

        getAllTitles();

        nAdapter.notifyDataSetChanged();
    }

    private void getAllTitles(){
        NoteRow nr=new NoteRow();
        try {
            if (db != null) {
                c = db.rawQuery(viewstring, null);
                c.moveToFirst();
                while (c.isAfterLast() == false) {
                    String t = c.getString(1);
                    String ts = c.getString(2);

                    //NoteRow nr=new NoteRow();
                    nr.setTitle(t);
                    nr.setTimeStamp(ts);

                    allnotes.add(nr);

                    Title = (TextView) findViewById(R.id.title);
                    Timestamp = (TextView) findViewById(R.id.timestamp);

                    Title.setText(t);
                    Timestamp.setText(ts);

                    c.moveToNext();

                }

            }
            db.close();
            c.close();
        } catch(Exception e){
            e.getMessage();
        }



    }


    private void openDb(){
        db=openOrCreateDatabase("NotesDb", Context.MODE_PRIVATE,null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_veiw_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
