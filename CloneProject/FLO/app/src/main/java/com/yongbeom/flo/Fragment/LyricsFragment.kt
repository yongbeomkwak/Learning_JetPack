package com.yongbeom.flo.Fragment

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.*
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.forEachIndexed
import androidx.core.view.marginBottom
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
import java.lang.Math.abs

class LyricsFragment:Fragment() {
    private var mBinding: FragmentLyricsBinding?=null
    private val binding get() = mBinding
    private lateinit var act:MainActivity
    private val Ids:MutableList<Int> = mutableListOf() //동적으로 만든 아이템들의 아이디를 담고 있음
    private var prevPosition:Int=-1
    private var whiteColor:Int? =null
    private  var grayColor:Int? =null
    private  var seekColor:Int? =null
    private  var hilightColor:Int? =null
    //private var _adpater:LyricsAdapter? =null

    private val seek:MutableLiveData<Boolean> = MutableLiveData(false) // Seek 모드 확인 라이브 데이터
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
        //_adpater=LyricsAdapter(context = requireContext(),act.lyricsInfo.lyricses)


        /***
         *
         * 사용할 색깔 초기화
         * /
         */
        whiteColor = ContextCompat.getColor(requireContext(),R.color.white)
        grayColor = ContextCompat.getColor(requireContext(),R.color.gray)
        seekColor = ContextCompat.getColor(requireContext(),R.color.seek)
        hilightColor=whiteColor

        GenerateLyricsTable() //가사 테이블 동적 초기화

        if(act.lyricsScollYList.size==0) //만약 이동할 위치 리스트가 초기화가 안되있으
        {
            //초기화 진행
            binding!!.table.viewTreeObserver.addOnGlobalLayoutListener(mGlobalListener)
        }





        seek.observe(this, Observer {
            if(it) //seek 모드가 켜졌으면
            {
                hilightColor=seekColor //하이라이트 색깔 파란색
            }
            else
            {
                hilightColor=whiteColor //하이라이트 색깔 하얀색
            }
        })

        act.statusViewModel.pos.observe(this, Observer {
            val pos=act.lyricsInfo.binarySearch(it)
            trackingLyrics(pos)

            if(seek.value==false) //Seek 이 꺼져 있으면 스크롤 하고
            {


                if(abs(prevPosition-pos)>=7 && act.lyricsScollYList.size!=0)  // 7줄 이상 차이나면 Scroll 포지션 배열이 초기화 대었다
                {
                    ScrollLyrics(pos) //스크롤
                    prevPosition=pos //이전 위치 변경
                }
            }






        })


        binding!!.switchSeek.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                Log.e("Check",isChecked.toString())
                seek.value=isChecked
            }
        })

        binding!!.imgExit.setOnClickListener {
            //나가기 이동
            act.statusViewModel._isMain.value=true
            act.navController.navigate(R.id.action_lyricsFragment_to_mainFragment)
        }
        /*with(binding!!.recLyrics)
        {
            adapter=_adpater
            layoutManager=LinearLayoutManager(requireContext())
            addItemDecoration(RecyclerDecoration(30)) //아이템간 간격 추가
        }
         */

        binding!!.table.setOnClickListener {
            if (seek.value==false) //seek 값이 꺼져 있을 때 가사창 누를 시 메인 페이지로 이동
            {
                act.statusViewModel._isMain.value=true
                act.navController.navigate(R.id.action_lyricsFragment_to_mainFragment)
            }
        }








    }

    fun GenerateLyricsTable()
    {
        val param:TableRow.LayoutParams=TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT) //파라미터 설정
        for ( (i,value) in act.lyricsInfo.lyricses.withIndex())
        {
            val tableRow:TableRow= TableRow(requireContext()) //테이블 Row layout 생성
            tableRow.setPadding(0,20,0,20) //패딩 바텀 10
            tableRow.gravity=Gravity.CENTER // 센터 정렬
            val textView:TextView= TextView(requireContext()) //텍스트뷰 생성
            textView.id=ViewCompat.generateViewId() //아이디 동적으로 만들기
            Ids.add(textView.id)  //아이디 담기
            textView.setOnClickListener(object :View.OnClickListener{
                override fun onClick(v: View?) {
                    val index=Ids.indexOf(v!!.id)

                    if(seek.value==true) //Seek 모드 켜져있으면
                    {
                        prevPosition=-1 // tracking 하는 거 초기
                        act.moveSeekBarFromLyricsFragment(index)
                    }


                }
            })
            textView.text=value.second // 해당 가사 넣기
            textView.gravity=Gravity.CENTER //센터 정렬
            textView.setTextColor(grayColor!!) //색 설정
            textView.textSize=15.0f // 글씨 크기
            tableRow.addView(textView,param) //테이블 row 안에 텍스트뷰 넣기
            binding!!.table.addView(tableRow) //테이블 레이아웃에 테이블 row 넣기
        }

    }

    fun trackingLyrics(pos:Int)
    {
        //가사 추적하여 하이라이트 작업 및 색 변경
        for (id in 0..Ids.size-1)
        {
            if(id==pos) //현재 가사면 여기로
            {
                binding!!.table.getChildAt(id).findViewById<TextView>(Ids[id]).setTextColor(hilightColor!!)
                binding!!.table.getChildAt(id).findViewById<TextView>(Ids[id]).setTypeface(null,Typeface.BOLD_ITALIC)

            }
            else
            {
                binding!!.table.getChildAt(id).findViewById<TextView>(Ids[id]).setTextColor(grayColor!!)
                binding!!.table.getChildAt(id).findViewById<TextView>(Ids[id]).setTypeface(null,Typeface.NORMAL)
            }
        }
    }

    fun ScrollLyrics(pos:Int)
    {
        if(pos!=0)
        {

            binding!!.scroll.post( //post 와 Runnable이 핵심
                object :Runnable {
                    override fun run() {
                        Log.e("POS","Scroll")
                        binding!!.scroll.smoothScrollTo(0,act.lyricsScollYList[pos])
                    }
                }
            )
        }


    }


    val mGlobalListener=object:ViewTreeObserver.OnGlobalLayoutListener
    { //해당 뷰의 높이와 너비를 얻기 위해서... 꼭 여기를 이용해야 정확한 값 나옴
        override fun onGlobalLayout() {
            val w:Int =binding!!.table.width //나는 테이블 레이아웃을 얻고 싶음
            val h:Int =binding!!.table.height

            val divH:Int=h/Ids.size //해당 테이블 레이아웃을 해당 테이블 row의 개수로 나움

            for(i in 0.. Ids.size-1)
            {
                act.lyricsScollYList.add(divH*i) // 0~ 일정한 배수 형식으로 스크롤 위치 저장
            }

            Log.e("WH","${w} ${h} ${ act.lyricsScollYList.size}")

            removeOnGlobalLayoutListener(binding!!.table.viewTreeObserver,this) //한번 초기화 후 사용하면 안되므로 삭제
        }
    }

    companion object{
        //삭제 이벤트 리스너
        fun removeOnGlobalLayoutListener(observer:ViewTreeObserver,listener:ViewTreeObserver.OnGlobalLayoutListener)
        {
            if (observer == null) {

                return ;

            }
            observer.removeOnGlobalLayoutListener(listener); //삭제


        }
    }




}