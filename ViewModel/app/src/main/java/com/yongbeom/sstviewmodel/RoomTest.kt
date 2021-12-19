package com.yongbeom.sstviewmodel

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.yongbeom.sstviewmodel.DB.Entities.User
import com.yongbeom.sstviewmodel.DB.UserDatabase
import com.yongbeom.sstviewmodel.databinding.ActivityRoomTestBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomTest : AppCompatActivity() {


private lateinit var binding: ActivityRoomTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var newUser:User=User("H","21","010-1234-1234")
        val db=UserDatabase.getInstance(applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
            Log.e("Size: ",db!!.userDao().getAll().size.toString())
            if(db!!.userDao().getAll().size==0)
            {

                db!!.userDao().insert(newUser)
            }
            else
            {
                db!!.userDao().insert(User("H","21","010-45678-91234"))
            }
            Log.e("List: ",db!!.userDao().getAll().toString())
        }


    }
}