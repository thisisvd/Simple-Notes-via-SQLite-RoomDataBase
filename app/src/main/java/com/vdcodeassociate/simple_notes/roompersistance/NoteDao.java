package com.vdcodeassociate.simple_notes.roompersistance;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.vdcodeassociate.simple_notes.model.Notes;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    long[] insertNotes(Notes[] notes);

    @Query("SELECT * FROM notes")
    LiveData<List<Notes>> getNotes();

    @Query("SELECT * FROM notes WHERE title LIKE :title")
    List<Notes> getNotesWithCustomQuery(String title);

    @Delete
    int delete(Notes[] notes);

    @Update
    int update(Notes[] notes);
}
