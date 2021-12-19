package com.yongbeom.sstviewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.yongbeom.sstviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.btnLiveData.setOnClickListener{
            val intent:Intent=Intent(this,LiveDataActivity::class.java)
            startActivity(intent)
        }

        binding.btnViewModel.setOnClickListener {
            val intent:Intent=Intent(this,ViewModelTest::class.java)
            startActivity(intent)
        }
        binding.btnRoom.setOnClickListener {
            val intent:Intent=Intent(this,RoomTest::class.java)
            startActivity(intent)
        }
        binding.btnFinal.setOnClickListener {
            val intent:Intent=Intent(this,Final::class.java)
            startActivity(intent)
        }

    }
}