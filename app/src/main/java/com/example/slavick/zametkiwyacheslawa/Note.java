package com.example.slavick.zametkiwyacheslawa;

import android.os.Parcel;
import android.os.Parcelable;


public class Note implements Parcelable {

    String noteTheme;
    String noteText;
    String importance;

    public Note(String noteTheme, String noteText, String importance) {
        this.noteTheme = noteTheme;
        this.noteText = noteText;
        this.importance = importance;
    }

    protected Note(Parcel in) {
        noteTheme = in.readString();
        noteText = in.readString();
        importance = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(noteTheme);
        dest.writeString(noteText);
        dest.writeString(importance);
    }
}
