package com.vdcodeassociate.simple_notes;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vdcodeassociate.simple_notes.adapters.RecycleViewAdapter;
import com.vdcodeassociate.simple_notes.model.Notes;
import com.vdcodeassociate.simple_notes.util.VerticalSpaceItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class NotesListActivity extends AppCompatActivity implements RecycleViewAdapter.OnNoteListener {

    private static final String TAG = "NotesListActivity";

    //UI Components -------
    private RecyclerView recyclerView;

    //Variables -------
    private List<Notes> mNotes = new ArrayList<>();
    private RecycleViewAdapter recycleViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        recyclerView = findViewById(R.id.recycleView);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NoteActivity.class);
                startActivity(intent);
            }
        });

        Toolbar toolbar = findViewById(R.id.notes_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notes APP");

        initRecycleView();
        dummyNotes();

    }

    private void dummyNotes(){
        for(int i=0; i<500;i++){
            Notes notes = new Notes();
            notes.setTitle("Title # "+i);
            notes.setContent("Content # "+i);
            notes.setDate("March 17, 2021");
            mNotes.add(notes);
        }
        recycleViewAdapter.notifyDataSetChanged();
    }

    private void initRecycleView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        VerticalSpaceItemDecorator verticalSpaceItemDecorator = new VerticalSpaceItemDecorator(10);
        recyclerView.addItemDecoration(verticalSpaceItemDecorator);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        recycleViewAdapter = new RecycleViewAdapter(mNotes,this::onNoteClick);
        recyclerView.setAdapter(recycleViewAdapter);
    }

    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "onNoteClick: "+mNotes.get(position));

        Intent intent = new Intent(NotesListActivity.this,NoteActivity.class);
        intent.putExtra("selected_note",mNotes.get(position));
        startActivity(intent);
    }

    private ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            deleteNote(mNotes.get(viewHolder.getAdapterPosition()));
        }
    };

    private void deleteNote(Notes note){
        mNotes.remove(note);
        recycleViewAdapter.notifyDataSetChanged();
    }
}