package com.zhivv.dailyplanner.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.zhivv.dailyplanner.model.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{

    public abstract NoteDao noteDao();


}
