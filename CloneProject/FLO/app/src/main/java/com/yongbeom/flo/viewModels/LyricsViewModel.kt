package com.yongbeom.flo.viewModels

import android.util.Log
import com.yongbeom.flo.util.FloApplication

class LyricsViewModel {
    val raw:List<String> =FloApplication._data.lyrics.split("\n")
    val lyricses:MutableList<Pair<Int,String>> = mutableListOf() //가사와 타임라인을 담고 있는 배열
    val _time:MutableList<Int> = mutableListOf() //이분 탐색을 위한 타임라인 배열
    val _hash:HashMap<Int,String> = HashMap<Int,String>() //시간과 가사의 매칭
    var _end:Int=0

    init {
        val regex=Regex("[0-9][0-9]:[0-9][0-9]")// 정규 표현 식 사용
        for (_str in raw)
        {
            // _str이 [00:16:200]we wish you a merry christmas 구조


            val matchResult:MatchResult?=regex.find(_str)  //해당 [00:00]구조를 찾는다
            val _word=_str.split("]")[1]  // ]이후 가사 이므로
            val (str_min,str_sec)=matchResult!!.value.split(":") //[12:34]에서 :로 자르면 str_min=12,str_sec=34가 된다
            val min=str_min.toInt() //형 변환
            val sec=str_sec.toInt()
            val totalTime=min*60+sec //계산
            _time.add(totalTime) //시간 배열 삽입
            lyricses.add(Pair(totalTime,_word)) //가사 배열 삽입
            _hash.put(totalTime,_word) //해쉬 테이블 삽입 시간을 key로
        }
        _end=_time.size-1
    }

    fun binarySearch(time:Int):Int //이분 탐색
    {
        var start:Int =0
        var end:Int=this._time.size-1

        while(start<=end)
        {
            val mid:Int=(start+end)/2

            if(_time[mid]<time)
            {
                start=mid+1
            }
            else if(_time[mid]==time)
            {
                return mid
            }
            else
            {
                end=mid-1
            }
        }
        if(end<0) return 0
        return end
    }
}