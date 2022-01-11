package com.programmers.kmooc.activities.list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.programmers.kmooc.KmoocApplication
import com.programmers.kmooc.activities.detail.KmoocDetailActivity
import com.programmers.kmooc.databinding.ActivityKmookListBinding
import com.programmers.kmooc.models.Lecture
import com.programmers.kmooc.utils.toVisibility
import com.programmers.kmooc.viewmodels.KmoocListViewModel
import com.programmers.kmooc.viewmodels.KmoocListViewModelFactory

class KmoocListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKmookListBinding
    private lateinit var viewModel: KmoocListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val kmoocRepository = (application as KmoocApplication).kmoocRepository
        viewModel = ViewModelProvider(this, KmoocListViewModelFactory(kmoocRepository)).get(
            KmoocListViewModel::class.java
        )

        binding = ActivityKmookListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = LecturesAdapter()
            .apply { onClick = this@KmoocListActivity::startDetailActivity }

        viewModel.list()
        binding.lectureList.adapter = adapter

        viewModel.lectureList.observe(this, Observer {lectureList ->
            //RefreshListener 다음 동작됨 Listener안에 .list가 해당 liveData를 변경시킴
            Log.e("ListObserve","List Changed")
            adapter.updateLectures(lectureList.lectures)
            binding.pullToRefresh.isRefreshing=false //Refresh 끄기
        })


        binding.lectureList.addOnScrollListener(object :RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager=binding.lectureList.layoutManager

                if (viewModel.progressVisible.value!=true)
                {
                    //보이는 마지막 아이
                    val lastVisibleItem=(layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                    if(layoutManager.itemCount <=lastVisibleItem+5) //아이템 개수가 현재 기준 5개 보다 더 남았다면
                    {
                        viewModel.next() //다음 아이템 불러오기
                    }
                }
            }
        })


        binding.pullToRefresh.setOnRefreshListener{
            Log.e("Refresh","Re")
            viewModel.list() //변
        }




    }

    private fun startDetailActivity(lecture: Lecture) {
        startActivity(
            Intent(this, KmoocDetailActivity::class.java)
                .apply { putExtra(KmoocDetailActivity.INTENT_PARAM_COURSE_ID, lecture.id) }
        )
    }
}
