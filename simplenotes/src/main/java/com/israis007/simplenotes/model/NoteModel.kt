package com.israis007.simplenotes.model

import android.graphics.drawable.Drawable
import java.util.*

data class NoteModel (
    val id: Int,
    val profile: Drawable?,
    val name: String,
    val date: Calendar,
    val detail: String,
    val objects: Any?
)