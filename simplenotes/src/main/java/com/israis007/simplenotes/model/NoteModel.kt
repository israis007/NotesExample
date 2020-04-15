package com.israis007.simplenotes.model

import android.graphics.drawable.Drawable
import android.view.View
import java.util.*

data class NoteModel (
    val id: Int,
    val profile: Drawable?,
    val urlImage: String?,
    val name: String,
    val date: Calendar,
    val detail: String,
    val objects: Any?
) {

    constructor(id: Int, profile: Drawable?, name: String, date: Calendar, detail: String, objects: Any?): this(
        id,
        profile,
        null,
        name,
        date,
        detail,
        objects
    )

    constructor(id: Int, urlImage: String?, name: String, date: Calendar, detail: String, objects: Any?): this (
        id,
        null,
        urlImage,
        name,
        date,
        detail,
        objects
    )

    constructor(profile: Drawable?, name: String, date: Calendar, detail: String, objects: Any?): this(
        View.generateViewId(),
        profile,
        null,
        name,
        date,
        detail,
        objects
    )

    constructor(urlImage: String?, name: String, date: Calendar, detail: String, objects: Any?): this (
        View.generateViewId(),
        null,
        urlImage,
        name,
        date,
        detail,
        objects
    )
}