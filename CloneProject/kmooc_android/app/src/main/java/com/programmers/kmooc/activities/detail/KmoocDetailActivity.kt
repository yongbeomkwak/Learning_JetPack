package com.programmers.kmooc.activities.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.programmers.kmooc.KmoocApplication
import com.programmers.kmooc.databinding.ActivityKmookDetailBinding
import com.programmers.kmooc.models.Lecture
import com.programmers.kmooc.network.ImageLoader
import com.programmers.kmooc.utils.DateUtil
import com.programmers.kmooc.utils.toVisibility
import com.programmers.kmooc.viewmodels.KmoocDetailViewModel
import com.programmers.kmooc.viewmodels.KmoocDetailViewModelFactory

class KmoocDetailActivity : AppCompatActivity() {

    companion object {
        const val INTENT_PARAM_COURSE_ID = "param_course_id"
    }

    private lateinit var binding: ActivityKmookDetailBinding
    private lateinit var viewModel: KmoocDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val courseId=intent.getStringExtra(INTENT_PARAM_COURSE_ID)
        Log.e("courseId",courseId)
        if(courseId==null||courseId.isEmpty())
        {
            finish()
            return
        }
        val kmoocRepository = (application as KmoocApplication).kmoocRepository
        viewModel = ViewModelProvider(this, KmoocDetailViewModelFactory(kmoocRepository)).get(
            KmoocDetailViewModel::class.java
        )

        binding = ActivityKmookDetailBinding.inflate(layoutInflater)
        binding.toolbar.setNavigationOnClickListener{finish()} //뒤로 가기 설정

        setContentView(binding.root)

        /*viewModel.lecture.observe(this, Observer {
            setDetailInfo(viewModel.lecture!!.value!!)
        })*/

        /**
         * viewModel.lecture.observe를 하기 때문에 매개변수가 lecture.value로 자동 Delegate
        *  바로 위 구문과 같음 setDetailInfo의 매개변수 lecture는 viewModel의 lecture.value
        * */
        viewModel.lecture.observe(this,this::setDetailInfo)

        viewModel.progressVisible.observe(this) { visible ->
            binding.progressBar.visibility = visible.toVisibility()
        }
        viewModel.detail(courseId)


    }

    private fun setDetailInfo(lecture: Lecture)
    {
        binding.toolbar.title = lecture.name
        ImageLoader.loadImage(lecture.courseImageLarge) { binding.lectureImage.setImageBitmap(it) }
        binding.lectureNumber.setDescription("• 강좌번호 :", lecture.number)
        binding.lectureType.setDescription(
            "• 강좌분류 :",
            "${lecture.classfyName} (${lecture.middleClassfyName})"
        )
        binding.lectureOrg.setDescription("• 운영기관 :", lecture.orgName)

        binding.lectureTeachers.setDescription("• 교수정보 :", lecture.teachers ?: "")
        binding.lectureTeachers.visibility = (lecture.teachers?.isEmpty() == false).toVisibility()

        binding.lectureDue.setDescription(
            "• 운영기간 :",
            DateUtil.dueString(lecture.start, lecture.end)
        )

        binding.webView.loadData(lecture.overview?:"","text/html","UTF-8") //html 태그,mimeType,encoding
        binding.webView.visibility=(lecture.overview?.isEmpty()==false).toVisibility() //비어있지 않으면 보여줘라
    }
}