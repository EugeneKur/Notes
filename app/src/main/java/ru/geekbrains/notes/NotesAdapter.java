package ru.geekbrains.notes;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    public static final String TAG = "NotesAdapter";
    private List<Note2_0> note2_0List;
    private OnNoteClickListener clickListener;

    public NotesAdapter(List<Note2_0> note2_0List) {
        this.note2_0List = note2_0List;
    }

    public void setClickListener(OnNoteClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_layout, parent, false);
        Log.d(TAG, "onCreateViewHolder");
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(note2_0List.get(position));

        Log.d(TAG, "onBindViewHolder" + position);
    }

    @Override
    public int getItemCount() {
        return note2_0List.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView title = itemView.findViewById(R.id.title);
        TextView preText = itemView.findViewById(R.id.preText);
        TextView date = itemView.findViewById(R.id.date);
        CheckBox done = itemView.findViewById(R.id.done);

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(Note2_0 note) {
            title.setText(note.getNameNote());
            preText.setText(note.getTextNote());
            date.setText(note.getDateNote());
            done.setChecked(note.isDone());

            itemView.setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.onNoteClick(v, getAdapterPosition());
                }
            });
        }
    }

    interface OnNoteClickListener {
        void onNoteClick(View view, int position);
    }
}
