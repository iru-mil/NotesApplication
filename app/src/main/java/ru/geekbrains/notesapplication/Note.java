package ru.geekbrains.notesapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private String noteName;
    private String notePurport;
    private String noteCreationDate;
    private int noteImportanceDegree;
    private boolean noteToArchive;

    public Note(String noteName, String noteDescription, String noteCreationDate, int noteImportanceDegree, boolean noteToArchive) {
        this.noteName = noteName;
        this.notePurport = noteDescription;
        this.noteCreationDate = noteCreationDate;
        this.noteImportanceDegree = noteImportanceDegree;
        this.noteToArchive = noteToArchive;
    }

    protected Note(Parcel in) {
        noteName = in.readString();
        notePurport = in.readString();
        noteCreationDate = in.readString();
        noteImportanceDegree = in.readInt();
        noteToArchive = in.readByte() != 0;
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

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public void setNotePurport(String notePurport) {
        this.notePurport = notePurport;
    }

    public void setNoteCreationDate(String noteCreationDate) {
        this.noteCreationDate = noteCreationDate;
    }

    public void setNoteImportanceDegree(int noteImportanceDegree) {
        this.noteImportanceDegree = noteImportanceDegree;
    }

    public void setNoteToArchive(boolean noteToArchive) {
        this.noteToArchive = noteToArchive;
    }

    public String getNoteName() {
        return noteName;
    }

    public String getNotePurport() {
        return notePurport;
    }

    public String getNoteCreationDate() {
        return noteCreationDate;
    }

    public int getNoteImportanceDegree() {
        return noteImportanceDegree;
    }

    public boolean isNoteToArchive() {
        return noteToArchive;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(noteName);
        dest.writeString(notePurport);
        dest.writeString(noteCreationDate);
        dest.writeInt(noteImportanceDegree);
        dest.writeByte((byte) (noteToArchive ? 1 : 0));
    }
}
