package ru.geekbrains.notes;

import android.app.Activity;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    public static final String TAG = "NotesAdapter";
    private final NoteSource source;
    private final ListNotesFragment fragment;
    private OnNoteClickListener clickListener;
    private int menuPosition = -1;

    public int getMenuPosition() {
        return menuPosition;
    }

    public NotesAdapter(ListNotesFragment fragment, NoteSource noteList) {
        this.source = noteList;
        this.fragment = fragment;
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
        holder.bind(source.getNote(position));

        Log.d(TAG, "onBindViewHolder" + position);
    }

    @Override
    public int getItemCount() {
        return source.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView title = itemView.findViewById(R.id.title);
        TextView preText = itemView.findViewById(R.id.preText);
        TextView date = itemView.findViewById(R.id.date);
        CheckBox done = itemView.findViewById(R.id.done);


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            fragment.registerForContextMenu(itemView);
        }

        void bind(Note note) {
            title.setText(note.getNameNote());
            preText.setText(note.getTextNote());
            date.setText(note.getDateNote());
            done.setChecked(note.isDone());

            itemView.setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.onNoteClick(v, getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(v -> {
                menuPosition = getLayoutPosition();
                itemView.showContextMenu();
                return true;
            });
        }
    }

    interface OnNoteClickListener {
        void onNoteClick(View view, int position);
    }
}
