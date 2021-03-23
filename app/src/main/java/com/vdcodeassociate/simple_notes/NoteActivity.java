package com.vdcodeassociate.simple_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vdcodeassociate.simple_notes.model.Notes;

public class NoteActivity extends AppCompatActivity implements View.OnTouchListener,GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {

    private static final String TAG = "NoteActivity";
    private static final int EDIT_MODE_ENABLED = 1;
    private static final int EDIT_MODE_DISABLED = 0;

    //Containers
    private LinedEditText linedEditText;
    private EditText editText;
    private TextView textView;
    private RelativeLayout mBackArrowContainer,mCheckContainer;
    private ImageButton backArrow,checkBox;

    //variables
    private Notes note;
    private GestureDetector gestureDetector;
    private int mMode;

    private boolean mIsNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        linedEditText = findViewById(R.id.note_text);
        editText = findViewById(R.id.note_edit_title);
        textView = findViewById(R.id.note_text_title);
        mBackArrowContainer = findViewById(R.id.back_arrow_container);
        mCheckContainer = findViewById(R.id.checkbox_container);
        backArrow = findViewById(R.id.toolbar_back_arrow);
        checkBox = findViewById(R.id.toolbar_checkbox);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disabledEditMode();
            }
        });

        if(getIncomingIntent()){
            // this is a new note, (Edit Mode)
            setNewNoteProperties();
            enabledEditMode();
        }else{
            // this is not a new note (View Mode)
            setNoteProperties();
        }

        setListeners();
    }

    public void enabledEditMode(){
        mBackArrowContainer.setVisibility(View.GONE);
        mCheckContainer.setVisibility(View.VISIBLE);
        linedEditText.setFocusableInTouchMode(true);

        textView.setVisibility(View.GONE);
        editText.setVisibility(View.VISIBLE);

        mMode = EDIT_MODE_ENABLED;
    }

    public void disabledEditMode(){
        mBackArrowContainer.setVisibility(View.VISIBLE);
        mCheckContainer.setVisibility(View.GONE);
        linedEditText.setFocusable(false);

        textView.setVisibility(View.VISIBLE);
        editText.setVisibility(View.GONE);

        mMode = EDIT_MODE_DISABLED;
    }

    private void setListeners(){
        linedEditText.setOnTouchListener(this::onTouch);
        gestureDetector = new GestureDetector(this,this);
    }

    private boolean getIncomingIntent(){
        if(getIntent().hasExtra("selected_note")){
            note = getIntent().getParcelableExtra("selected_note");
            Log.d(TAG, "onCreate: "+note.toString());

            mMode = EDIT_MODE_DISABLED;
            mIsNewNote = false;
            return false;
        }

        mMode = EDIT_MODE_ENABLED;
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
        enabledEditMode();
        Log.d("TAG","onDoubeTapped : Double Tap!");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
}