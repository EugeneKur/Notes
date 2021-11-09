package ru.geekbrains.notes;

import java.util.ArrayList;
import java.util.List;

public class AllNotes {
    private final List<Note> notesList;

    public AllNotes(int maxNotesCount) {
        this.notesList =  new ArrayList<>(maxNotesCount);
    }

    public void addNote(String nameNote, String textNote, String dateNote) {
        notesList.add(new Note(nameNote, textNote, dateNote));
    }

    public List<Note> getNotesList() {
        return notesList;
    }

    public int getSize() {
        return notesList.size();
    }
}
