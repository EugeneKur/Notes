package ru.geekbrains.notes;

public class Note {
    private String nameNote;
    private String textNote;
    private String dateNote;



    public Note(String nameNote, String textNote, String dateNote) {
        this.nameNote = nameNote;
        this.textNote = textNote;
        this.dateNote = dateNote;
    }


    public String getNameNote() {
        return nameNote;
    }

    public void setNameNote(String nameNote) {
        this.nameNote = nameNote;
    }

    public String getTextNote() {
        return textNote;
    }

    public void setTextNote(String textNote) {
        this.textNote = textNote;
    }

    public String getDateNote() {
        return dateNote;
    }

    public void setDateNote(String dateNote) {
        this.dateNote = dateNote;
    }
}
