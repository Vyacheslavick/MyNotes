package com.example.slavick.zametkiwyacheslawa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder> {

    private List<Note> notes;
    private Context context;
    private OnNoteItemClick onNoteItemClick;

    public NoteRecyclerAdapter(List<Note> notes, Context context, OnNoteItemClick onNoteItemClick) {
        this.notes = notes;
        this.context = context;
        this.onNoteItemClick = onNoteItemClick;
    }


    @NonNull
    @Override
    public NoteRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteRecyclerAdapter.ViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.noteText.setText(note.noteText);
        holder.noteTheme.setText(note.noteTheme);
        holder.importance.setText(note.importance);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void addItem(Note note) {
        notes.add(0, note);
        notifyItemInserted(0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView noteText;
        TextView noteTheme;
        TextView importance;

        public ViewHolder(View itemView) {
            super(itemView);
            noteText = itemView.findViewById(R.id.note_text);
            noteTheme = itemView.findViewById(R.id.note_theme);
            importance = itemView.findViewById(R.id.note_importance);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNoteItemClick.onClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onNoteItemClick.onLongClick(getAdapterPosition());
                    return true;
                }
            });
        }
    }



    public interface OnNoteItemClick {
        void onClick(int position);
        void onLongClick(int position);
    }
}
