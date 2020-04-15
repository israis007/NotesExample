package com.israis007.simplenotes.ui

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.israis007.simplenotes.R
import com.israis007.simplenotes.model.NoteModel
import com.israis007.simplenotes.model.PropertiesNote
import com.israis007.simplenotes.tools.DateFormatter

class NoteAdapter(
    private val context: Context,
    private val propertiesNote: PropertiesNote,
    private val listNotes: ArrayList<NoteModel>,
    private val eventNewNote: EventNewNote?
) : RecyclerView.Adapter<NoteAdapter.NoteItem>() {

    inner class NoteItem(private val noteView: View) : RecyclerView.ViewHolder(noteView) {
        fun bindItems(noteModel: NoteModel) {
            val icon = noteView.findViewById<ImageView>(R.id.card_note_iv_profile)
            val tv_name = noteView.findViewById<AppCompatTextView>(R.id.card_note_actv_name)
            val tv_date = noteView.findViewById<AppCompatTextView>(R.id.card_note_actv_date)
            val tv_detail = noteView.findViewById<AppCompatTextView>(R.id.card_note_actv_detail)

            /* Setting properties */
            val lp = tv_detail.layoutParams as ConstraintLayout.LayoutParams

            lp.marginStart = propertiesNote.note_detail_marginStart.toInt()
            lp.marginEnd = propertiesNote.note_detail_marginEnd.toInt()
            lp.bottomMargin = propertiesNote.note_detail_marginBottom.toInt()
            lp.topMargin = propertiesNote.note_detail_marginTop.toInt()

            tv_detail.layoutParams = lp

            val lpicon = icon.layoutParams as ConstraintLayout.LayoutParams
            lpicon.width = propertiesNote.note_profile_side_size.toInt()
            lpicon.height = propertiesNote.note_profile_side_size.toInt()

            icon.layoutParams = lpicon

            if (propertiesNote.note_profile_tint != -1)
                icon.setColorFilter(propertiesNote.note_profile_tint!!)

            tv_name.setTextSize(TypedValue.COMPLEX_UNIT_PX, propertiesNote.note_name_text_size)
            tv_date.setTextSize(TypedValue.COMPLEX_UNIT_PX, propertiesNote.note_date_text_size)
            tv_detail.setTextSize(TypedValue.COMPLEX_UNIT_PX, propertiesNote.note_detail_text_size)

            tv_name.setTextColor(propertiesNote.note_name_text_color)
            tv_date.setTextColor(propertiesNote.note_date_text_color)
            tv_detail.setTextColor(propertiesNote.note_detail_text_color)

            when (propertiesNote.note_profile_type) {
                PropertiesNote.ProfileType.CIRCLE -> if (noteModel.profile == null && noteModel.urlImage.isNullOrEmpty())
                    Glide.with(context).load(R.drawable.ic_perfil).centerInside().circleCrop()
                        .into(icon)
                else
                    Glide.with(context)
                        .setDefaultRequestOptions(
                            RequestOptions().centerInside().circleCrop()
                        )
                        .load(noteModel.urlImage ?: noteModel.profile)
                        .placeholder(propertiesNote.note_profile_default_icon)
                        .error(propertiesNote.note_profile_default_icon)
                        .into(icon)
                PropertiesNote.ProfileType.SQUARE -> if (noteModel.profile == null && noteModel.urlImage.isNullOrEmpty())
                    Glide.with(context).load(R.drawable.ic_perfil).centerInside().circleCrop()
                        .into(icon)
                else
                    Glide.with(context)
                        .setDefaultRequestOptions(
                            RequestOptions().centerInside().centerCrop()
                        )
                        .load(noteModel.urlImage ?: noteModel.profile)
                        .placeholder(propertiesNote.note_profile_default_icon)
                        .error(propertiesNote.note_profile_default_icon)
                        .into(icon)
            }

            tv_name.text = noteModel.name
            tv_date.text = DateFormatter.getCalendarFormatted(
                noteModel.date,
                propertiesNote.note_date_text_mask
            )
            tv_detail.text = noteModel.detail
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItem =
        NoteItem(LayoutInflater.from(context).inflate(R.layout.card_note, parent, false))

    override fun getItemCount(): Int = listNotes.size

    override fun onBindViewHolder(holder: NoteItem, position: Int) =
        holder.bindItems(listNotes[position])

    fun addNewNote(noteModel: NoteModel){
        listNotes.add(noteModel)
        notifyItemInserted(itemCount - 1)
        eventNewNote?.noteAdded(noteModel)
    }

    fun addNotes(listNotes: ArrayList<NoteModel>){
        listNotes.forEach {
            val ic = itemCount
            listNotes.add(it)
            notifyItemInserted(ic + 1)
            eventNewNote?.noteAdded(it)
        }
    }

    fun getListNotes() = listNotes

    fun removeNote(index: Int){
        listNotes.removeAt(index)
        notifyDataSetChanged()
    }

    fun removeNote(noteModel: NoteModel){
        listNotes.remove(noteModel)
        notifyDataSetChanged()
    }

    fun cleanNotes(){
        listNotes.clear()
        notifyDataSetChanged()
    }

    fun getNote(index: Int) = listNotes[index]

}