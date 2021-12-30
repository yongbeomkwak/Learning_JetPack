package com.yongbeom.flo.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.yongbeom.flo.databinding.ActivitySplashBinding
import com.yongbeom.flo.databinding.FragmentMainBinding
import com.yongbeom.flo.util.FloApplication

class MainFragment:Fragment() {
    private var mBinding: FragmentMainBinding?=null
    private val binding get() = mBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding= FragmentMainBinding.inflate(layoutInflater,container,false)
        Log.e("onCreateViewMain","Lebal")
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.tvSongTitle.text=FloApplication._data.title?: "Null"
        binding!!.tvAlbum.text=FloApplication._data.album?:"Null"
        binding!!.tvSinger.text=FloApplication._data.singer?:"Null"
        Glide.with(this).load(FloApplication._data.image).into(binding?.imgSong!!)
        //binding!!.recyLyrics.adapter
        Log.e("OnViewCreated","Lebal")
    }
}