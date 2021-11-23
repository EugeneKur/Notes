package ru.geekbrains.notes;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoteSourceImpl implements NoteSource{

    private final List<Note> notes;

    public NoteSourceImpl(@NonNull Context context) {
        this.notes = new ArrayList<>(Arrays.asList(
                new Note("1 заметка", "Текст первой заметки. Текст. Текст. Текст. Текст. Текст. Текст.", "22.12.2048"),
                new Note("2 заметка", "Текст второй заметки. Текст. Текст. Текст. Текст. Текст. Текст.", "23.12.2048"),
                new Note("3 заметка", "Текст третьей заметки. Текст. Текст. Текст. Текст. Текст. Текст.", "25.12.2048"),
                new Note("4 заметка", "Текст четвертой заметки. Текст. Текст. Текст. Текст. Текст. Текст.", "26.12.2048"),
                new Note("5 заметка", "Текст пятой заметки. Текст. Текст. Текст. Текст. Текст. Текст.", "26.12.2048"),
                new Note("6 заметка", "Текст шестой заметки. Текст. Текст. Текст. Текст. Текст. Текст.", "26.12.2048"),
                new Note("7 заметка", "Текст седьмой заметки. Текст. Текст. Текст. Текст. Текст. Текст.", "26.12.2048"),
                new Note("8 заметка", "Текст восьмой заметки. Текст. Текст. Текст. Текст. Текст. Текст.", "26.12.2048")
        ));
    }

    @Override
    public Note getNote(int position) {
        return notes.get(position);
    }

    @Override
    public int size() {
        return notes.size();
    }

    @Override
    public void deleteNote(int position) {
        notes.remove(position);
    }

    @Override
    public void updateNote(int position, Note note) {
        notes.set(position, note);
    }

    @Override
    public void addNote(Note note) {
        notes.add(note);
    }

    @Override
    public void clearNotes() {
        notes.clear();
    }
}
