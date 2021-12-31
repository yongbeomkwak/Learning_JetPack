package com.yongbeom.flo.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yongbeom.flo.Activity.MainActivity
import com.yongbeom.flo.R
import com.yongbeom.flo.adapter.LyricsAdapter
import com.yongbeom.flo.adapter.RecyclerDecoration
import com.yongbeom.flo.databinding.FragmentLyricsBinding
import com.yongbeom.flo.databinding.FragmentMainBinding
import com.yongbeom.flo.util.FloApplication

class LyricsFragment:Fragment() {
    private var mBinding: FragmentLyricsBinding?=null
    private val binding get() = mBinding
    private lateinit var act:MainActivity
    private var _adpater:LyricsAdapter? =null

    private val seek:MutableLiveData<Boolean> = MutableLiveData(false)
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
        _adpater=LyricsAdapter(context = requireContext(),act.lyricsInfo.lyricses)



        seek.observe(this, Observer {
            if(it) //seek 모드가 켜졌으면
            {

            }
        })

        act.statusViewModel.pos.observe(this, Observer {

        })

        binding!!.switchSeek.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                Log.e("Check",isChecked.toString())
                seek.value=isChecked
            }
        })

        binding!!.imgExit.setOnClickListener {
            act.statusViewModel._isMain.value=true
            act.navController.navigate(R.id.action_lyricsFragment_to_mainFragment)
        }
        with(binding!!.recLyrics)
        {
            adapter=_adpater
            layoutManager=LinearLayoutManager(requireContext())
            addItemDecoration(RecyclerDecoration(30)) //아이템간 간격 추가
        }




    }


}