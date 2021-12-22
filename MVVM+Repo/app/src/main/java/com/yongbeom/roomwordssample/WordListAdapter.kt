package com.yongbeom.roomwordssample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yongbeom.roomwordssample.DB.Entity.Word
import com.yongbeom.roomwordssample.WordListAdapter.*
import com.yongbeom.roomwordssample.databinding.RecyclerviewItemBinding
import androidx.recyclerview.widget.*

class WordListAdapter:ListAdapter<Word, WordViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current:Word = getItem(position)

        holder.bind(current.word) //텍스트 바인드
    }

    class WordViewHolder(binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val wordItemView: TextView = binding.textView

        fun bind(text: String?) {
            wordItemView.text = text
        }

        companion object {
            //바인딩
            fun create(parent: ViewGroup): WordViewHolder {
                val mBinding:RecyclerviewItemBinding= RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return(WordViewHolder(mBinding))

            }
        }
    }
    /**
     * WordsComparator는 두 단어가 동일한 경우 또는 콘텐츠가 동일한 경우 계산하는 방법을 정의합니다.
     * */
    class WordsComparator : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.word == newItem.word
        }
    }


}