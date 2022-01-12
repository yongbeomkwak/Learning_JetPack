package com.programmers.kmooc.activities.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.programmers.kmooc.R
import com.programmers.kmooc.databinding.ViewKmookListItemBinding
import com.programmers.kmooc.models.Lecture
import com.programmers.kmooc.network.ImageLoader
import com.programmers.kmooc.utils.DateUtil
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LecturesAdapter : RecyclerView.Adapter<LectureViewHolder>() {

    private var mBindnig:ViewKmookListItemBinding?=null
    private val binding get() = mBindnig!!
    private val lectures = mutableListOf<Lecture>()
    var onClick: (Lecture) -> Unit = {}

    fun updateLectures(lectures: List<Lecture>) {
        this.lectures.clear()
        this.lectures.addAll(lectures)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return lectures.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LectureViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_kmook_list_item, parent, false)
        mBindnig = ViewKmookListItemBinding.bind(view)
        return LectureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LectureViewHolder, position: Int) {
        val lecture:Lecture = lectures[position]
        holder.itemView.setOnClickListener { onClick(lecture) }

        binding.lectureTitle.text="${lecture.name}"
        binding.lectureFrom.text="${lecture.orgName}"
        binding.lectureDuration.text=DateUtil.dueString(start = lecture.start,end = lecture.end)



        ImageLoader.loadImage(lecture.courseImage){ bitmap ->
            holder.itemView.findViewById<ImageView>(R.id.lectureImage).setImageBitmap(bitmap)
            //binding.lectureImage.setImageBitmap(bitmap) //비트맵으로 이미지 설정
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}

class LectureViewHolder(binding: ViewKmookListItemBinding) : RecyclerView.ViewHolder(binding.root) {


}