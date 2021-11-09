package ru.geekbrains.notes;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private String nameNote;
    private String textNote;
    private String dateNote;



    public Note(String nameNote, String textNote, String dateNote) {
        this.nameNote = nameNote;
        this.textNote = textNote;
        this.dateNote = dateNote;
    }


    protected Note(Parcel in) {
        nameNote = in.readString();
        textNote = in.readString();
        dateNote = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameNote);
        dest.writeString(textNote);
        dest.writeString(dateNote);
    }
}
