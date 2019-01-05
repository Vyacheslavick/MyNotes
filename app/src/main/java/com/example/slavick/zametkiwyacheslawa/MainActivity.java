package com.example.slavick.zametkiwyacheslawa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteRecyclerAdapter.OnNoteItemClick {

    NoteRecyclerAdapter adapter;
    Button createNote;
    List<Note> notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView list = findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        adapter = new NoteRecyclerAdapter(notes, this, this );
        list.setAdapter(adapter);
        if (getIntent().getParcelableExtra("key" ) != null ){
            Note note = getIntent().getParcelableExtra("key");
            adapter.addItem(note);
        }
        createNote = findViewById(R.id.create_note);
        createNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    public void onClick(int position) {

    }
}
