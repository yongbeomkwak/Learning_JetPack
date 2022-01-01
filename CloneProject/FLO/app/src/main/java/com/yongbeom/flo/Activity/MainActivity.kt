package com.yongbeom.flo.Activity

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import com.yongbeom.flo.R
import com.yongbeom.flo.data.Respon
import com.yongbeom.flo.databinding.ActivityMainBinding
import com.yongbeom.flo.util.FloApplication
import com.yongbeom.flo.util.RetrofitService
import com.yongbeom.flo.util.RetrofitSet
import com.yongbeom.flo.viewModels.LyricsViewModel
import com.yongbeom.flo.viewModels.StatusViewModel
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.Exception
import java.lang.Runnable

class MainActivity : AppCompatActivity() {
    private var mBinding:ActivityMainBinding? =null
    private val binding get() = mBinding
    private var mediaPlayer:MediaPlayer? =null
    public lateinit var navController: NavController
    public val statusViewModel:StatusViewModel by viewModels() //Fragment에서 접근할 수 있도록 public
    public val lyricsInfo:LyricsViewModel=LyricsViewModel()
    public var lyricsScollYList:MutableList<Int> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityMainBinding.inflate(layoutInflater)
        val navHostFragment:NavHostFragment =supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController=navHostFragment.navController

        //Fragment를 이동하기 위한 navController

        setContentView(binding?.root)
        initMedia() //MediaPlayer 초기화

        mediaPlayer!!.setOnPreparedListener(object :MediaPlayer.OnPreparedListener{
            //곡을 불러오는 준비 작업 리스
            override fun onPrepared(mp: MediaPlayer?) {
                Log.e("Prepare","준비 완료")
                binding!!.seekBar.max=FloApplication._data.duration// seekBar max값 설정
            }
        })



        /**
         ********************** Observer ******************
         */

        statusViewModel.isPlay.observe(this, Observer {
            if(it) //재생 중이면  pause 아이콘 보여주기
            {

                binding!!.playBox.imgPlay.setImageResource(R.drawable.icon_pause)
            }
            else //아니면 play 아이콘 보여주기
            {
                binding!!.playBox.imgPlay.setImageResource(R.drawable.icon_play)

            }
        })

        statusViewModel.isMain.observe(this, Observer {
            if(it) //Main 회면이면 가사리스트 아이콘 보이게
            {
                binding!!.playBox.imgPlayList.visibility= View.VISIBLE //보이게
            }
            else
            {
                binding!!.playBox.imgPlayList.visibility= View.INVISIBLE //보이게
            }

        })



        /**
         * ****************** Event ********************
         */



        binding!!.playBox.imgPlay.setOnClickListener{
            if (mediaPlayer!!.isPlaying) //재생중인 상태에서 재생 버튼을 눌렀을 때
            {
                mediaPlayer!!.pause() //멈춤
            }
            else //재생중이 아닐 때 눌럿을 경우
            {
                mediaPlayer!!.start() //재생
                // Seekbar 트랙킹 하는 영역
                Thread(object:Runnable{ //이 때 스레드를 하나 만들어 SeekBar를 트래킹함
                    override fun run() {
                        while(mediaPlayer!!.isPlaying) //재생동안만 작동
                        {
                            try {
                                Thread.sleep(1000)//1초에 한번씩
                            }
                            catch (e:Exception){}




                            binding!!.seekBar.progress=mediaSeekToProgress(mediaPlayer!!.currentPosition) //SeekBar 이동
                        }

                    }
                }).start()
            }

            statusViewModel._isPlay.value=!statusViewModel.isPlay.value!! //재생중인지 아닌지 상태 변경 (!반대값으로)
        }

        binding!!.playBox.imgPlayList.setOnClickListener{
            //가사창으로 Fragment이동, MainFragment인지 그 값 역시 변함
            statusViewModel._isMain.value=!statusViewModel._isMain.value!!
            navController.navigate(R.id.action_mainFragment_to_lyricsFragment)
        }

        binding!!.seekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{

            /**
             *    onStartTrackingTouch(SeekBar seekbar) : 최초 탭하여 드래그 시작시 발생
            onStopTrackingTouch(SeekBar seekbar) : 드래그 중 발생
            onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) : 드래그 멈추면 발생
            -> 시크바 View / 변경된 값 / 사용자에 의한 변경인지(True), 코드에 의한 변경인지(False)
             * */
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //여기서 fromUser를 거치면 외부에서 UI로 컨트롤 한 것이고
                //그냥 밖에있으면 progress가 지날 때마다 계속 실행됨
                //진짜 다행이 여기서 해당 값을 liveData하여 MainFragment에서 Observe가능
                statusViewModel._pos.value=progress //LiveData 변경
                if(fromUser) //것을 보면 사용자가 SeekBar를 움직였을때만 if가 실행되게 되었습니다
                {
                    mediaPlayer!!.seekTo(progressToMediaSeek(progress)) //유저가 움직인 만큼 mediaPlayer도 노래 재생 위치를 변경
                    //이때 mediaPlayer와 Progress값과 차이가 있으니 해당 함수를 통해 맞춰줌
                }



            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }
        })

        mediaPlayer!!.setOnCompletionListener {
            // 곡 종료 시 초기화
            Log.e("Finish","곡 종료")
            Toast.makeText(this,"곡 종료",Toast.LENGTH_SHORT).show()
            binding!!.seekBar.progress=0
            mediaPlayer!!.seekTo(0)
            statusViewModel._isPlay.value=false
        }

    }

    fun initMedia()
    {
        mediaPlayer= MediaPlayer.create(this, Uri.parse(FloApplication._data.file))
        //해당 Uri를 Uri.parse를 이용


    }

     //값을 Convert
    fun progressToMediaSeek(progress:Int):Int
    {
        return progress*1000
    }
    fun mediaSeekToProgress(progress:Int):Int
    {
        return progress/1000
    }


    override fun isDestroyed(): Boolean {
        //할당 해제
        mBinding=null
        if(mediaPlayer != null) {
            mediaPlayer!!.release();
            mediaPlayer = null;
        }
        return super.isDestroyed()
    }

    fun moveSeekBarFromLyricsFragment(index:Int) //LyricsFragment에서 가사 클릭시 해당 노래위치와 SeekBar progress를 변경
    {
        //mediaPlayer를 옮기면 위에 Thread 부분에서 다시 progress를 초기화 시킴
        mediaPlayer!!.seekTo(progressToMediaSeek(lyricsInfo._time[index])) // TextView에 해당 되는 id에 의한 index 이용해 time라인 이동
    }


}