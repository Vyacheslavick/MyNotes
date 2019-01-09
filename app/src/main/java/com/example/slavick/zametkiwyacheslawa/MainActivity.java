package com.example.slavick.zametkiwyacheslawa;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteRecyclerAdapter.OnNoteItemClick {

    Note note;
    NoteRecyclerAdapter adapter;
    Button addNote;
    List<Note> notes;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        note = data.getParcelableExtra("key");
        adapter.addItem(note);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notes = new ArrayList<>();
        if (notes.size() == 0) {
            notes.add(new Note("Hello", "you in my pleasantly notes, fucking assholekfvrmfrkvmkefvkrfvklefkvnjnjdnvjnsvlkvndsvnsfndvskvmlksnvnjlnskdnndvj", "1488228"));
            notes.add(new Note("Hello", "you in my pleasantly notes, fucking assholekfvrmfrkvmkefvkrfvklefkvnjnjdnvjnsvlkvndsvnsfndvskvmlksnvnjlnskdnndvj", "1488229"));
            notes.add(new Note("Hello", "you in my pleasantly notes, fucking assholekfvrmfrkvmkefvkrfvklefkvnjnjdnvjnsvlkvndsvnsfndvskvmlksnvnjlnskdnndvj", "1488230"));
            notes.add(new Note("Hello", "you in my pleasantly notes, fucking assholekfvrmfrkvmkefvkrfvklefkvnjnjdnvjnsvlkvndsvnsfndvskvmlksnvnjlnskdnndvj", "1488231"));
        }
        final RecyclerView list = findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        adapter = new NoteRecyclerAdapter(notes, this, this);
        list.setAdapter(adapter);
        addNote = findViewById(R.id.add_note);
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(MainActivity.this, CreateActivity.class);
        intent.putExtra("watch", notes.get(position));
        startActivityForResult(intent, 0);
    }

    @Override
    public void onLongClick(int position) {
        notes.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        return true;
    }

}
