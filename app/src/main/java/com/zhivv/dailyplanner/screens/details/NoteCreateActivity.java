package com.zhivv.dailyplanner.screens.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.zhivv.dailyplanner.App;
import com.zhivv.dailyplanner.R;
import com.zhivv.dailyplanner.model.Note;

public class NoteCreateActivity extends AppCompatActivity {

    private static final String EXTRA_NOTE = "NoteCreateActivity.EXTRA_NOTE";

    private Note note;

    private EditText editNoteText;
    private EditText editNoteName;

    public static void start(Activity caller, Note note) {
        Intent intent = new Intent(caller, NoteCreateActivity.class);
        if (note != null) {
            intent.putExtra(EXTRA_NOTE, note);
        }
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note_create);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle(getString(R.string.note_details_title));

        editNoteText = findViewById(R.id.textNote);
        editNoteName = findViewById(R.id.nameNote);

        if (getIntent().hasExtra(EXTRA_NOTE)) {
            note = getIntent().getParcelableExtra(EXTRA_NOTE);
            editNoteText.setText(note.noteDescription);
            editNoteName.setText(note.noteName);
        } else {
            note = new Note();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_save:
                if (editNoteName.getText().length() > 0) {
                    note.noteName = editNoteName.getText().toString();
                    note.noteDescription = editNoteText.getText().toString();
                    note.dateStart = System.currentTimeMillis();
                    note.dateFinish = System.currentTimeMillis();

                    if (getIntent().hasExtra(EXTRA_NOTE)) {
                        App.getInstance().getNoteDao().update(note);
                    } else {
                        App.getInstance().getNoteDao().insert(note);
                    }
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
