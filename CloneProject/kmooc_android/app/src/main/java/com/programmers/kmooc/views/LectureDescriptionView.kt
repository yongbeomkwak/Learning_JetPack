package com.programmers.kmooc.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.programmers.kmooc.R
import com.programmers.kmooc.databinding.ViewLectureDescriptionBinding

class LectureDescriptionView(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {

    constructor(context: Context) : this(context, null)

    private lateinit var binding: ViewLectureDescriptionBinding

    init {
        val view =
            LayoutInflater.from(context).inflate(R.layout.view_lecture_description, this, true)
        binding = ViewLectureDescriptionBinding.bind(view)
    }

    fun setDescription(title: String, description: String) {
        binding.descriptionTitle.text = title
        binding.description.text = description
    }
}