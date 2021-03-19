package com.vdcodeassociate.simple_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.vdcodeassociate.simple_notes.model.Notes;

public class NoteActivity extends AppCompatActivity {

    private static final String TAG = "NoteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        if(getIntent().hasExtra("selected_note")){
            Notes note = getIntent().getParcelableExtra("selected_note");
            Log.d(TAG, "onCreate: "+note.toString());
        }
    }
}