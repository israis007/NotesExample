package com.israis007.simplenotes.ui

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.israis007.simplenotes.R
import com.israis007.simplenotes.model.NoteModel
import com.israis007.simplenotes.model.PropertiesNote
import com.israis007.simplenotes.tools.DateFormatter
import com.israis007.simplenotes.tools.ViewTools
import java.util.*
import kotlin.collections.ArrayList

class SimpleNoteLinear @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var propertiesNote: PropertiesNote
    private var listNotes = ArrayList<NoteModel>()
    private lateinit var recyclerView: RecyclerView
    private var iconD: Drawable? = null
    private var nameN: String? = null
    private var eventNewNote: EventNewNote? = null

    init {
        context.withStyledAttributes(
            attrs,
            R.styleable.SimpleNote,
            defStyleAttr,
            R.style.SimpleNoteTheme
        ) {
            propertiesNote = PropertiesNote()
            val reso = context.resources
            /* Get attrs from style */
            propertiesNote.note_profile_type = PropertiesNote.getProfileType(
                getInteger(
                    R.styleable.SimpleNote_note_profile_type,
                    reso.getInteger(R.integer.profile_type)
                )
            )
            val textmask = getString(R.styleable.SimpleNote_note_date_text_mask)
            propertiesNote.note_date_text_mask =
                if (textmask.isNullOrEmpty()) reso.getString(R.string.default_date_mask) else textmask
            propertiesNote.note_profile_tint =
                getColor(R.styleable.SimpleNote_note_profile_tint, -1)
            propertiesNote.note_profile_side_size = getDimension(
                R.styleable.SimpleNote_note_profile_side_size,
                reso.getDimension(R.dimen.profile_side)
            )
            propertiesNote.note_name_text_size = getDimension(
                R.styleable.SimpleNote_note_name_text_size,
                reso.getDimension(R.dimen.name_textsize)
            )
            propertiesNote.note_name_text_color = getColor(
                R.styleable.SimpleNote_note_name_text_color,
                ContextCompat.getColor(context, R.color.textColor)
            )
            propertiesNote.note_date_text_size = getDimension(
                R.styleable.SimpleNote_note_date_text_size,
                reso.getDimension(R.dimen.date_textsize)
            )
            propertiesNote.note_date_text_color = getColor(
                R.styleable.SimpleNote_note_date_text_color,
                ContextCompat.getColor(context, R.color.textColor)
            )
            propertiesNote.note_detail_text_size = getDimension(
                R.styleable.SimpleNote_note_detail_text_size,
                reso.getDimension(R.dimen.detail_textsize)
            )
            propertiesNote.note_detail_text_color = getColor(
                R.styleable.SimpleNote_note_detail_text_color,
                ContextCompat.getColor(context, R.color.textColor)
            )
            propertiesNote.note_new_text_size = getDimension(
                R.styleable.SimpleNote_note_new_text_size,
                reso.getDimension(R.dimen.detail_textsize)
            )
            propertiesNote.note_new_text_color = getColor(
                R.styleable.SimpleNote_note_new_text_color,
                ContextCompat.getColor(context, R.color.textColor)
            )
            propertiesNote.note_new_border_enable_color = getColor(
                R.styleable.SimpleNote_note_new_border_enable_color,
                ContextCompat.getColor(context, R.color.primary)
            )
            propertiesNote.note_new_border_disable_color = getColor(
                R.styleable.SimpleNote_note_new_border_disable_color,
                ContextCompat.getColor(context, R.color.textColor)
            )
            propertiesNote.note_button_text_color = getColor(
                R.styleable.SimpleNote_note_button_text_color,
                ContextCompat.getColor(context, R.color.primary)
            )
            propertiesNote.note_button_text_size = getDimension(
                R.styleable.SimpleNote_note_button_text_size,
                reso.getDimension(R.dimen.detail_textsize)
            )
            propertiesNote.note_button_text_allCaps =
                getBoolean(R.styleable.SimpleNote_note_button_text_allCaps, false)
            propertiesNote.note_button_background = getColor(
                R.styleable.SimpleNote_note_button_background,
                ContextCompat.getColor(context, android.R.color.white)
            )
            propertiesNote.note_scrollable =
                getBoolean(R.styleable.SimpleNote_note_scrollable, false)
            propertiesNote.note_detail_marginTop = getDimension(
                R.styleable.SimpleNote_note_detail_marginTop,
                reso.getDimension(R.dimen.note_margin_top)
            )
            propertiesNote.note_detail_marginStart = getDimension(
                R.styleable.SimpleNote_note_detail_marginStart,
                reso.getDimension(R.dimen.labels_margin)
            )
            propertiesNote.note_detail_marginEnd = getDimension(
                R.styleable.SimpleNote_note_detail_marginEnd,
                reso.getDimension(R.dimen.labels_margin)
            )
            propertiesNote.note_detail_marginBottom = getDimension(
                R.styleable.SimpleNote_note_detail_marginBottom,
                reso.getDimension(R.dimen.note_margin_bottom)
            )
            propertiesNote.note_height_notes_list = getDimension(
                R.styleable.SimpleNote_note_height_notes_list,
                reso.getDimension(R.dimen.notes_height)
            )
            propertiesNote.note_new_marginTop = getDimension(
                R.styleable.SimpleNote_note_new_marginTop,
                reso.getDimension(R.dimen.labels_margin)
            )
            propertiesNote.note_new_marginStart = getDimension(
                R.styleable.SimpleNote_note_new_marginStart,
                reso.getDimension(R.dimen.labels_margin)
            )
            propertiesNote.note_new_marginEnd = getDimension(
                R.styleable.SimpleNote_note_new_marginEnd,
                reso.getDimension(R.dimen.labels_margin)
            )
            propertiesNote.note_new_marginBottom = getDimension(
                R.styleable.SimpleNote_note_new_marginBottom,
                reso.getDimension(R.dimen.labels_margin)
            )

            orientation = VERTICAL
            listNotes.add(
                NoteModel(
                    profile = null,
                    name = reso.getString(R.string.profile_name),
                    date = Calendar.getInstance(Locale.getDefault()),
                    detail = reso.getString(R.string.profile_detail),
                    objects = null
                )
            )
            recyclerView = RecyclerView(context)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.isNestedScrollingEnabled = propertiesNote.note_scrollable
            drawNotes()
        }
    }

    private fun drawNotes() {
        this.removeAllViews()

        repeat(listNotes.size) {
            val noteModel = listNotes[it]

            val nc = LayoutInflater.from(context).inflate(R.layout.card_note, null, false)
            val ivIcon = nc.findViewById<ImageView>(R.id.card_note_iv_profile)
            val tvName = nc.findViewById<AppCompatTextView>(R.id.card_note_actv_name)
            val tvDate = nc.findViewById<AppCompatTextView>(R.id.card_note_actv_date)
            val tvDetail = nc.findViewById<AppCompatTextView>(R.id.card_note_actv_detail)

            /* Setting properties */
            val lp = tvDetail.layoutParams as ConstraintLayout.LayoutParams

            lp.marginStart = propertiesNote.note_detail_marginStart.toInt()
            lp.marginEnd = propertiesNote.note_detail_marginEnd.toInt()
            lp.bottomMargin = propertiesNote.note_detail_marginBottom.toInt()
            lp.topMargin = propertiesNote.note_detail_marginTop.toInt()

            tvDetail.layoutParams = lp

            val lpicon = ivIcon.layoutParams as ConstraintLayout.LayoutParams
            lpicon.width = propertiesNote.note_profile_side_size.toInt()
            lpicon.height = propertiesNote.note_profile_side_size.toInt()

            ivIcon.layoutParams = lpicon

            if (propertiesNote.note_profile_tint != -1)
                ivIcon.setColorFilter(propertiesNote.note_profile_tint!!)

            tvName.setTextSize(TypedValue.COMPLEX_UNIT_PX, propertiesNote.note_name_text_size)
            tvDate.setTextSize(TypedValue.COMPLEX_UNIT_PX, propertiesNote.note_date_text_size)
            tvDetail.setTextSize(TypedValue.COMPLEX_UNIT_PX, propertiesNote.note_detail_text_size)

            tvName.setTextColor(propertiesNote.note_name_text_color)
            tvDate.setTextColor(propertiesNote.note_date_text_color)
            tvDetail.setTextColor(propertiesNote.note_detail_text_color)

            when (propertiesNote.note_profile_type) {
                PropertiesNote.ProfileType.CIRCLE -> if (noteModel.profile == null && noteModel.urlImage.isNullOrEmpty())
                    Glide.with(context).load(R.drawable.ic_perfil).centerInside().circleCrop()
                        .into(ivIcon)
                else
                    Glide.with(context)
                        .setDefaultRequestOptions(
                            RequestOptions().centerInside().circleCrop()
                        )
                        .load(noteModel.urlImage ?: noteModel.profile)
                        .placeholder(propertiesNote.note_profile_default_icon)
                        .error(propertiesNote.note_profile_default_icon)
                        .into(ivIcon)
                PropertiesNote.ProfileType.SQUARE -> if (noteModel.profile == null && noteModel.urlImage.isNullOrEmpty())
                    Glide.with(context).load(R.drawable.ic_perfil).centerInside().circleCrop()
                        .into(ivIcon)
                else
                    Glide.with(context)
                        .setDefaultRequestOptions(
                            RequestOptions().centerInside().centerCrop()
                        )
                        .load(noteModel.urlImage ?: noteModel.profile)
                        .placeholder(propertiesNote.note_profile_default_icon)
                        .error(propertiesNote.note_profile_default_icon)
                        .into(ivIcon)
            }

            tvName.text = noteModel.name
            tvDate.text = DateFormatter.getCalendarFormatted(
                noteModel.date,
                propertiesNote.note_date_text_mask
            )
            tvDetail.text = noteModel.detail

            val lpe = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )

            nc.layoutParams = lpe
            this@SimpleNoteLinear.addView(ViewTools.getViewWithoutParent(nc))
        }


        loadLastField()

        this@SimpleNoteLinear.invalidate()
        this@SimpleNoteLinear.requestLayout()
    }

    private fun loadLastField() {
        val temp = LayoutInflater.from(context).inflate(R.layout.template_layout, null, false)
        val til_newNote = temp.findViewById<TextInputLayout>(R.id.tilNewNote)
        val et_newNote = temp.findViewById<TextInputEditText>(R.id.etNewNote)
        val btn_newNote = temp.findViewById<Button>(R.id.btnNewNote)

        /* Setting properties */
        til_newNote.boxStrokeColor = propertiesNote.note_new_border_enable_color

        val states = arrayOf(
            intArrayOf(android.R.attr.state_focused),
            intArrayOf(-android.R.attr.state_focused),
            intArrayOf(android.R.attr.state_empty),
            intArrayOf(android.R.attr.state_enabled),
            intArrayOf(android.R.attr.state_activated),
            intArrayOf(-android.R.attr.state_pressed),
            intArrayOf(-android.R.attr.state_window_focused),
            intArrayOf(-android.R.attr.state_active)
        )

        val colors = intArrayOf(
            propertiesNote.note_new_border_enable_color,
            propertiesNote.note_new_border_disable_color,
            propertiesNote.note_new_border_disable_color,
            propertiesNote.note_new_border_disable_color,
            propertiesNote.note_new_border_disable_color,
            propertiesNote.note_new_border_disable_color,
            propertiesNote.note_new_border_disable_color,
            propertiesNote.note_new_border_disable_color
        )

        til_newNote.hintTextColor = ColorStateList(states, colors)
        til_newNote.defaultHintTextColor = ColorStateList(states, colors)
        et_newNote.setTextColor(propertiesNote.note_new_text_color)
        et_newNote.setTextSize(TypedValue.COMPLEX_UNIT_PX, propertiesNote.note_new_text_size)
        btn_newNote.setTextSize(TypedValue.COMPLEX_UNIT_PX, propertiesNote.note_button_text_size)
        btn_newNote.setTextColor(propertiesNote.note_button_text_color)
        btn_newNote.isAllCaps = propertiesNote.note_button_text_allCaps

        val lpet = til_newNote.layoutParams as LayoutParams
        lpet.setMargins(
            propertiesNote.note_new_marginStart.toInt(),
            propertiesNote.note_new_marginTop.toInt(),
            propertiesNote.note_new_marginEnd.toInt(),
            propertiesNote.note_new_marginBottom.toInt()
        )
        til_newNote.layoutParams = lpet

        /* Adding views */
        this.addView(ViewTools.getViewWithoutParent(recyclerView))
        this.addView(ViewTools.getViewWithoutParent(temp))

        /* Adding Events */
        btn_newNote.setOnClickListener {
            val textNew = et_newNote.text.toString().trim()
            if (textNew.isEmpty())
                et_newNote.error = context.getString(R.string.new_note_error)
            else {
                val note = NoteModel(
                    listNotes.size + 1,
                    iconD,
                    nameN ?: context.getString(R.string.profile_name),
                    Calendar.getInstance(
                        Locale.getDefault()
                    ),
                    textNew,
                    null
                )
                listNotes.add(note)
                et_newNote.text = null
                eventNewNote?.noteAdded(note)
                drawNotes()
            }
        }

        this@SimpleNoteLinear.addView(ViewTools.getViewWithoutParent(temp))
    }

    fun setNewNoteProfile(drawable: Drawable) {
        iconD = drawable
    }

    fun setNewNoteName(name: String) {
        nameN = name
    }

    fun addNotes(listNotes: ArrayList<NoteModel>) {
        this.listNotes.addAll(listNotes)
        drawNotes()
    }

    fun cleanNotes() {
        this.listNotes.clear()
        drawNotes()
    }

    fun removeNote(index: Int) {
        this.listNotes.removeAt(index)
        drawNotes()
    }

    fun removeNote(noteModel: NoteModel) {
        this.listNotes.remove(noteModel)
        drawNotes()
    }

    fun getNotes(): ArrayList<NoteModel> = this.listNotes

    fun getNoteAt(index: Int) = this.listNotes[index]

    fun setEventListener(eventNewNote: EventNewNote) {
        this.eventNewNote = eventNewNote
        drawNotes()
    }

}