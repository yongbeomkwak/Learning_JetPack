package com.yongbeom.flo.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yongbeom.flo.databinding.LayoutMainLyricsBinding

class LyricsAdapter(private val context: Context, private val list: List<Pair<Int, String>>) :
    RecyclerView.Adapter<LyricsAdapter.LyricsViewHolder>() {
    private var mBindnig: LayoutMainLyricsBinding? = null
    private val binding get() = mBindnig!!
    private var now_pos:Int=-1
    public val now:Int get() = now_pos

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LyricsViewHolder {
        mBindnig = LayoutMainLyricsBinding.inflate(LayoutInflater.from(context), parent, false)
        return LyricsViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        /**
         * 리사이클러뷰 아이템 뒤섞임 방지 를 위한 오버라이딩
         *
         */
        return position
    }

    override fun onBindViewHolder(holder: LyricsViewHolder, position: Int) {
        with(holder)
        {
            with(list[position])
            {
                if(position==now)
                {
                    binding.tvLyrics.text = second
                    binding.tvLyrics.setTextColor(Color.WHITE) //현재 값은 색깔 하얀색
                    binding.tvLyrics.setTypeface(null, Typeface.BOLD) //볼드 적용
                }
                else
                {
                    binding.tvLyrics.text = second
                }




            }
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }


    inner class LyricsViewHolder(binding: LayoutMainLyricsBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setNowPos(pos:Int)
    {
        now_pos=pos
    }


}