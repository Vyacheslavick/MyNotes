package com.example.slavick.zametkiwyacheslawa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    volatile List<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notes = new ArrayList<>();
        notes = summonDao().getAll();

        final RecyclerView list = findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        adapter = new NoteRecyclerAdapter(notes, this, this);
        list.setAdapter(adapter);

        addNote = findViewById(R.id.add_note);

        if (getIntent().getParcelableExtra("key") != null) {
            note = getIntent().getParcelableExtra("key");
            summonDao().insert(note);
            adapter.addItem(note);
        } else if (getIntent().getParcelableExtra("watch") != null) {
            SharedPreferences sp = getSharedPreferences("PositionName", Context.MODE_PRIVATE);
            int count = sp.getInt("position", 0);
            note = getIntent().getParcelableExtra("watch");
            adapter.changeItem(note, count);
            summonDao().update(note);
        }

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
        SharedPreferences sp = getSharedPreferences("PositionName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("position", position);
        editor.apply();
        Intent intent = new Intent(MainActivity.this, CreateActivity.class);
        intent.putExtra("watch", notes.get(position));
        startActivity(intent);
    }

    @Override
    public void onLongClick(final int position) {
        summonDao().delete(notes.get(position));
        notes.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public NoteDao summonDao(){
        NoteDatabase database = App.getInstance().getDatabase();
        return database.noteDao();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        return true;
    }

}
