package com.israis007.notesexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.israis007.simplenotes.model.NoteModel
import com.israis007.simplenotes.tools.DateFormatter
import com.israis007.simplenotes.ui.EventNewNote
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        notess.setEventListener(object: EventNewNote{
            override fun onNewNote(noteModel: NoteModel) {
                Log.d(TAG, "note added: ${noteModel.detail} at ${DateFormatter.getCalendarFormatted(noteModel.date, getString(R.string.default_date_mask))}")
                Log.d(TAG, notess.getNotes().toString())
            }

            override fun noteAdded(noteModel: NoteModel) {
                Log.d(TAG, "note added: ${noteModel.detail} at ${DateFormatter.getCalendarFormatted(noteModel.date, getString(R.string.default_date_mask))}")
                Log.d(TAG, notess.getNotes().toString())
            }
        })


    }
}
