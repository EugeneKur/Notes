package ru.geekbrains.notes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.UUID;


public class ListNotesFragment extends Fragment {
    int currentPosition = -1;

    private NoteSource source;
    private NotesAdapter adapter;

    public ListNotesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_list_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);

    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.note_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_delete_note){
            source.deleteNote(adapter.getMenuPosition());
            adapter.notifyItemRemoved(adapter.getMenuPosition());
            return true;
        } else if (item.getItemId() == R.id.action_update){
            source.updateNote(adapter.getMenuPosition(), new Note("Обновленная заметка", "Текст обновленной заметки. Текст. Текст. Текст. Текст. Текст. Текст.", "22.12.2048"));
            adapter.notifyItemChanged(adapter.getMenuPosition());
            return true;
        }

        return super.onContextItemSelected(item);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.list_notes_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add){
            Note note = new Note("Новая заметка", "Текст новой заметки. Текст. Текст. Текст. Текст. Текст. Текст.", "22.12.2048");

            note.setId(UUID.randomUUID().toString());
            source.addNote(note);
            adapter.notifyItemInserted(source.size()-1);
            return true;
        } else if (item.getItemId() == R.id.action_clear){
            int size = source.size();
            source.clearNotes();
            adapter.notifyItemRangeRemoved(0, size);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initView(View view) {

        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);

        //source = new NoteSourceImpl(getContext());
        //source = new PreferencesNoteSource(getActivity().getPreferences(Context.MODE_PRIVATE));

        source = new FirebaseNoteSource(() -> {
            adapter.notifyDataSetChanged();
        });

        adapter = new NotesAdapter(this, source);
        adapter.setClickListener((view1, position) -> {
            currentPosition = position;
            Note note = source.getNote(currentPosition);
            showText(note.getNameNote(), note.getTextNote(), note.getDateNote());
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    void showText(String name, String text, String date) {
        if (isLand()) {
            showTextLand(name, text, date);
        } else {
            showTextPort(name, text, date);
        }

    }

    void  showTextPort (String name, String text, String date) {
        TextNotesFragment textNotesFragment = TextNotesFragment.newInstance(new Note(name, text, date));
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container1, textNotesFragment)
                .addToBackStack(null)
                .commit();

    }

    void  showTextLand (String name, String text, String date) {
        TextNotesFragment textNotesFragment = TextNotesFragment.newInstance(new Note(name, text, date));
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container2, textNotesFragment)
                .addToBackStack(null)
                .commit();

    }

    private boolean isLand () {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

    };
}