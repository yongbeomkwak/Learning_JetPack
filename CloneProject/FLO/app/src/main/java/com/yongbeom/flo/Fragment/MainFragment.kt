package com.yongbeom.flo.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.yongbeom.flo.Activity.MainActivity
import com.yongbeom.flo.R
import com.yongbeom.flo.databinding.ActivitySplashBinding
import com.yongbeom.flo.databinding.FragmentMainBinding
import com.yongbeom.flo.util.FloApplication

class MainFragment:Fragment() {
    private var mBinding: FragmentMainBinding?=null
    private val binding get() = mBinding
    private lateinit var act:MainActivity //해당 Activity 접근을 위한 변수
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding= FragmentMainBinding.inflate(layoutInflater,container,false)

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        act=activity as MainActivity //엑티비티 얻기

        binding!!.tvSongTitle.text=FloApplication._data.title?: "Null"
        binding!!.tvAlbum.text=FloApplication._data.album?:"Null"
        binding!!.tvSinger.text=FloApplication._data.singer?:"Null"
        Glide.with(this).load(FloApplication._data.image).into(binding?.imgSong!!)

        act.statusViewModel.pos.observe(this, Observer {
            if(it==0) //만약 재생 전이면
            {
                //prev와 next만 보여주기
                binding!!.lyricsBox.tvPrevlyrics.text=act.lyricsInfo._hash[act.lyricsInfo._time[0]]
                binding!!.lyricsBox.tvNextlyrics.text=act.lyricsInfo._hash[act.lyricsInfo._time[1]]
            }
            else //재생중일 때
            {
                var prev:Int=0
                var next:Int=0
                val now:Int=act.lyricsInfo.binarySearch(it) //이분 탐색 후
                if(now>0) //만약 현재가 0보다 크면 prev는 0보다 작을 수 없으므로 초기화
                {
                    prev=now-1
                }
                if(now<act.lyricsInfo._end)//만약 now가 배열에 끝보다 작으면 next는 배열을 안넘을 수 있음
                {
                    next=now+1
                }
                if(next==0) //만약 next가 0이면 끝난 것
                {
                    binding!!.lyricsBox.tvNextlyrics.text="" //next가사 없음
                }
                else
                {
                    //아닐 경우 next가사 접근
                    binding!!.lyricsBox.tvNextlyrics.text=act.lyricsInfo._hash[act.lyricsInfo._time[next]]?:""
                }
                //해당 인덱스에 해당하는 시간대에 가사로 접근하여 보여줌
                binding!!.lyricsBox.tvPrevlyrics.text=act.lyricsInfo._hash[act.lyricsInfo._time[prev]]?:""
                binding!!.lyricsBox.tvNowlyrics.text=act.lyricsInfo._hash[act.lyricsInfo._time[now]]?:""


            }
        })

        binding!!.lyricsBox.root.setOnClickListener { // 가사 창 클릭 시 전체 창으로 이동
            act.statusViewModel._isMain.value=false
            act.navController.navigate(R.id.action_mainFragment_to_lyricsFragment)
        }

    }
}