package ru.geekbrains.notes;

import android.content.SharedPreferences;
import android.content.res.TypedArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PreferencesNoteSource implements NoteSource{
    public static final String NOTE_DATA = "NOTE_DATA";
    private final SharedPreferences sharedPreferences;

    private List<Note> noteList;

    public PreferencesNoteSource(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        fetch();
    }

    @Override
    public Note getNote(int position) {
        return noteList.get(position);
    }

    @Override
    public int size() {
        return noteList.size();
    }

    @Override
    public void deleteNote(int position) {
        noteList.remove(position);
        update();
    }

    @Override
    public void updateNote(int position, Note note) {
        noteList.set(position, note);
        update();
    }

    @Override
    public void addNote(Note note) {
        noteList.add(note);
        update();
    }

    @Override
    public void clearNotes() {
        noteList.clear();
        update();
    }

    private void fetch() {
        String json = sharedPreferences.getString(NOTE_DATA, null);
        if (json == null) {
            noteList = new ArrayList<>();
        } else {
            Type type = new TypeToken<ArrayList<Note>>() {}.getType();
            noteList = new GsonBuilder().create().fromJson(json, type);
        }
    }

    private void update() {
        String json = new GsonBuilder().create().toJson(noteList);
        sharedPreferences.edit()
                .putString(NOTE_DATA, json)
                .apply();
    }
}
