package com.yongbeom.sstviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.yongbeom.sstviewmodel.databinding.ActivityFinalBinding

class Final : AppCompatActivity() {
    private var binding:ActivityFinalBinding? =null
    private lateinit var appBarConfiguration:AppBarConfiguration
    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

    val navHostFragment:NavHostFragment =supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController= navHostFragment.navController
        appBarConfiguration= AppBarConfiguration(navGraph = navController.graph)
        setupActionBarWithNavController(navController,appBarConfiguration)
        //네비게이션 컨트롤과 appBar를 연결하여 뒤로가기 기능 구현

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }

    override fun isDestroyed(): Boolean {
        binding=null
        return super.isDestroyed()
    }
}