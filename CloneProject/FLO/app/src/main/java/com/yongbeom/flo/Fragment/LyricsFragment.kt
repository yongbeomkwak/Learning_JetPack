package com.yongbeom.flo.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yongbeom.flo.Activity.MainActivity
import com.yongbeom.flo.R
import com.yongbeom.flo.databinding.FragmentLyricsBinding
import com.yongbeom.flo.databinding.FragmentMainBinding

class LyricsFragment:Fragment() {
    private var mBinding: FragmentLyricsBinding?=null
    private val binding get() = mBinding
    private lateinit var act:MainActivity
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding= FragmentLyricsBinding.inflate(layoutInflater,container,false)
        return mBinding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        act=activity as MainActivity

        binding!!.imgExit.setOnClickListener {
            act.statusViewModel._isMain.value=true
            act.navController.navigate(R.id.action_lyricsFragment_to_mainFragment)
        }

    }

}