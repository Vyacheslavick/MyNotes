package com.example.slavick.zametkiwyacheslawa;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class CreateActivity extends AppCompatActivity {

    EditText noteTheme;
    EditText noteText;
    EditText importance;
    EditText hour;
    EditText min;
    Button createNote;
    Context context;


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
        if (getIntent().getParcelableExtra("watch") != null) {
            Note note = getIntent().getParcelableExtra("watch");
            noteTheme.setText(note.noteTheme);
            noteText.setText(note.noteText);
            importance.setText(note.importance);
        }
        createNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFrom(noteText).length() > 3 & getFrom(noteTheme).length() > 3 & Integer.valueOf(getFrom(importance)) > 0 & getFrom(hour).length() == 2 & getFrom(min).length() == 2) {
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTimeInMillis(System.currentTimeMillis());
//                    int hours = Integer.valueOf(getFrom(hour));
//                    int minutes = Integer.valueOf(getFrom(min));
//                    calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE, hours, minutes);
//                    calendar.set(Calendar.HOUR_OF_DAY,
//                            Integer.getInteger( getFrom(hour)),
//                            Integer.getInteger(getFrom(min)));

//                    Intent intent = new Intent(CreateActivity.this , AlarmManagerBroadcastReceiver.class);
//                    PendingIntent pendingIntent = PendingIntent.getBroadcast(CreateActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//                    AlarmManager alarmManager = (AlarmManager)context.getSystemService(ALARM_SERVICE);
//
//                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
//                            calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                    Intent intent1 = new Intent(CreateActivity.this, MainActivity.class);
                    intent1.putExtra("key", new Note(getFrom(noteTheme), getFrom(noteText), getFrom(importance)));
                    setResult(0, intent1);
                    finish();
                } else {
                    Toast.makeText(CreateActivity.this, "Проверь что ты ввёл", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public String getFrom(EditText editText) {
        return editText.getText().toString();
    }
}
