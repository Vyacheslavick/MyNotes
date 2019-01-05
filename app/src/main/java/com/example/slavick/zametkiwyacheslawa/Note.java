package com.example.slavick.zametkiwyacheslawa;

import java.io.Serializable;

public class Note implements Serializable {
    String noteTheme;
    String noteText;
    String importance;

    public Note(String noteTheme, String noteText, String importance) {
        this.noteTheme = noteTheme;
        this.noteText = noteText;
        this.importance = importance;
    }
}
