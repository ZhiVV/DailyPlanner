package com.zhivv.dailyplanner.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Note implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "dateStart")
    public long dateStart;

    @ColumnInfo(name = "dateFinish")
    public long dateFinish;

    @ColumnInfo(name = "noteName")
    public String noteName;

    @ColumnInfo(name = "noteDescription")
    public String noteDescription;

    public Note() {
    }
    protected Note(Parcel in) {
        uid = in.readInt();
        dateStart = in.readLong();
        dateFinish = in.readLong();
        noteName = in.readString();
        noteDescription = in.readString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return uid == note.uid &&
                dateStart == note.dateStart &&
                dateFinish == note.dateFinish &&
                Objects.equals(noteName, note.noteName) &&
                Objects.equals(noteDescription, note.noteDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, dateStart, dateFinish, noteName, noteDescription);
    }

    @Override
    public int describeContents() {
        return 0;
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

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(uid);
        parcel.writeLong(dateStart);
        parcel.writeLong(dateFinish);
        parcel.writeString(noteName);
        parcel.writeString(noteDescription);
    }
}
