package com.israis007.simplenotes.ui

import com.israis007.simplenotes.model.NoteModel

interface EventNewNote {

    fun onNewNote(noteModel: NoteModel)

    fun noteAdded(noteModel: NoteModel)

}