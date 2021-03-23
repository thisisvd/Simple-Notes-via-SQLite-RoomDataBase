package com.vdcodeassociate.simple_notes.roompersistance;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.vdcodeassociate.simple_notes.model.Notes;

import java.util.List;

public class NoteRepository {

    private NoteDatabase mNoteDatabase;

    public NoteRepository(Context context){
        mNoteDatabase = NoteDatabase.getInstance(context);
    }

    public void insertNoteTask(Notes note){}

    public void updateNote(Notes note){}

    private LiveData<List<Notes>> retrieveNotesTask(){
        return null;
    }

    public void deleteNote(Notes note){}
}

