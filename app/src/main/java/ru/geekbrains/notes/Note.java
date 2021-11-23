package ru.geekbrains.notes;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {

    private String nameNote;
    private String textNote;
    private String dateNote;
    private final boolean isDone;

    public Note(String nameNote, String textNote, String dateNote, boolean isDone) {
        this.nameNote = nameNote;
        this.textNote = textNote;
        this.dateNote = dateNote;
        this.isDone = isDone;
    }

    public Note(String nameNote, String textNote, String dateNote) {
        this(nameNote, textNote, dateNote, false);
    }

    protected Note(Parcel in) {
        nameNote = in.readString();
        textNote = in.readString();
        dateNote = in.readString();
        isDone = in.readByte() != 0;
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

    public String getTextNote() {
        return textNote;
    }

    public String getDateNote() {
        return dateNote;
    }

    public boolean isDone() {
        return isDone;
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
        dest.writeByte((byte) (isDone ? 1 : 0));
    }
}
