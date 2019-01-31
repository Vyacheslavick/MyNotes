package com.example.slavick.zametkiwyacheslawa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateActivity extends AppCompatActivity {

    EditText noteTheme;
    EditText noteText;
    EditText importance;
    EditText hour;
    EditText min;
    Button createNote;
    NoteDatabase database = App.getInstance().getDatabase();
    NoteDao noteDao = database.noteDao();
    Note note;
    long num;
    List<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        noteTheme = findViewById(R.id.note_theme_wright);
        noteText = findViewById(R.id.note_text_wright);
        importance = findViewById(R.id.note_importance_wright);
        createNote = findViewById(R.id.create_note);
        hour = findViewById(R.id.hours);
        min = findViewById(R.id.minutes);
        notes = noteDao.getAll();
        if (getIntent().getParcelableExtra("watch") != null) {
            note = getIntent().getParcelableExtra("watch");
            noteTheme.setText(note.noteTheme);
            noteText.setText(note.noteText);
            importance.setText(note.importance);
        }
        createNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFrom(noteText) != null & getFrom(noteTheme) != null & getFrom(importance) != null & getFrom(hour) != null & getFrom(min) != null ) {
                    if (getFrom(noteText).length() > 3 & getFrom(noteTheme).length() > 3 & Integer.valueOf(getFrom(importance)) > 0 & getFrom(hour).length() == 2 & getFrom(min).length() == 2) {
                        if (getIntent().getParcelableExtra("watch") != null) {
                            num = note.id;
                            note = new Note(num, getFrom(noteTheme), getFrom(noteText), getFrom(importance));
                            Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                            intent.putExtra("watch", note);
                            startActivity(intent);
                        } else {
                            int count = notes.size();
                            num = notes.get(count - 1).id + 1;
                            note = new Note(num, getFrom(noteTheme), getFrom(noteText), getFrom(importance));
                            Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                            intent.putExtra("key", note);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(CreateActivity.this, "Проверь что ты ввёл", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public String getFrom(EditText editText) {
        return editText.getText().toString();
    }
}
