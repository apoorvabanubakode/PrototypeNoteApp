package com.example.shreya.prototypenoteapp;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Shreya on 19-12-2016.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private List<NoteRow> notesList; // create class and this is the list of all note titles

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, timestamp;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            timestamp = (TextView) view.findViewById(R.id.timestamp);
        }
    }


    public NotesAdapter(List<NoteRow> notesList) {
        this.notesList = notesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NoteRow movie = notesList.get(position);
        holder.title.setText(movie.getTitle());  // create getters and setters
        holder.timestamp.setText(movie.getTimeStamp());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }
}
