package com.israis007.simplenotes.model

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt

class PropertiesNote {
    var note_date_text_mask: String = ""
    var note_profile_type: ProfileType = ProfileType.CIRCLE
    @ColorInt
    var note_profile_tint: Int? = null
    var note_profile_side_size: Float = 0f
    var note_name_text_size: Float = 0f
    @ColorInt
    var note_name_text_color: Int = 0
    var note_date_text_size: Float = 0f
    @ColorInt
    var note_date_text_color: Int = 0
    var note_detail_text_size: Float = 0f
    @ColorInt
    var note_detail_text_color: Int = 0
    var note_new_text_size: Float = 0f
    @ColorInt
    var note_new_text_color: Int = 0
    @ColorInt
    var note_new_border_enable_color: Int = 0
    @ColorInt
    var note_new_border_disable_color: Int = 0
    @ColorInt
    var note_button_text_color: Int = 0
    var note_button_text_size: Float = 0f
    var note_button_text_allCaps: Boolean = false
    @ColorInt
    var note_button_background: Int? = null
    var note_scrollable: Boolean = false
    var note_profile_default_icon: Drawable? = null
    var note_detail_marginTop: Float = 0f
    var note_detail_marginStart: Float = 0f
    var note_detail_marginEnd: Float = 0f
    var note_detail_marginBottom: Float = 0f
    var note_height_notes_list: Float = 0f
    var note_new_marginTop: Float = 0f
    var note_new_marginStart: Float = 0f
    var note_new_marginEnd: Float = 0f
    var note_new_marginBottom: Float = 0f

    companion object {

        fun getProfileType(value: Int): ProfileType =
            when (value) {
                0 -> ProfileType.CIRCLE
                else -> ProfileType.SQUARE
            }
    }

    enum class ProfileType{
        CIRCLE,
        SQUARE
    }
}