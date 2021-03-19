package com.vdcodeassociate.simple_notes;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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
        recycleViewAdapter = new RecycleViewAdapter(mNotes,this::onNoteClick);
        recyclerView.setAdapter(recycleViewAdapter);
    }

    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "onNoteClick: "+mNotes.get(position));
    }
}