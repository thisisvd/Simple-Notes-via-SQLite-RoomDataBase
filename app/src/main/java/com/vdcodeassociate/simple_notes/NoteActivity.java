package com.vdcodeassociate.simple_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.vdcodeassociate.simple_notes.model.Notes;

public class NoteActivity extends AppCompatActivity {

    private static final String TAG = "NoteActivity";

    private LinedEditText linedEditText;
    private EditText editText;
    private TextView textView;

    private Notes note;

    private boolean mIsNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        linedEditText = findViewById(R.id.note_text);
        editText = findViewById(R.id.note_edit_title);
        textView = findViewById(R.id.note_text_title);

        if(getIncomingIntent()){
            // this is a new note, (Edit Mode)
            setNewNoteProperties();
        }else{
            // this is not a new note (View Mode)
            setNoteProperties();
        }
    }

    private boolean getIncomingIntent(){
        if(getIntent().hasExtra("selected_note")){
            note = getIntent().getParcelableExtra("selected_note");
            Log.d(TAG, "onCreate: "+note.toString());

            mIsNewNote = false;
            return false;
        }

        mIsNewNote = true;
        return true;
    }

    private void setNoteProperties() {
        editText.setText(note.getTitle());
        textView.setText(note.getTitle());
        linedEditText.setText(note.getContent());
    }

    private void setNewNoteProperties() {
        editText.setText("Note Title");
        textView.setText("Note Title");
    }
}