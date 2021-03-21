package com.vdcodeassociate.simple_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vdcodeassociate.simple_notes.model.Notes;

public class NoteActivity extends AppCompatActivity implements View.OnTouchListener,GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {

    private static final String TAG = "NoteActivity";

    private LinedEditText linedEditText;
    private EditText editText;
    private TextView textView;

    private Notes note;
    private GestureDetector gestureDetector;

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

        setListeners();
    }

    private void setListeners(){
        linedEditText.setOnTouchListener(this::onTouch);
        gestureDetector = new GestureDetector(this,this);
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d("TAG","onDoubeTapped : Double Tap!");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
}