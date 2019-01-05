package com.example.slavick.zametkiwyacheslawa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class CreateActivity extends AppCompatActivity {

    EditText noteTheme;
    EditText noteText;
    EditText importance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
    }
}
