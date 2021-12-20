package com.yongbeom.sstviewmodel.DB.model

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.yongbeom.sstviewmodel.DB.AppDataBase
import com.yongbeom.sstviewmodel.DB.Entities.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoListViewModel(application: Application) : AndroidViewModel(application) {
 val db:AppDataBase= AppDataBase.getInstance(application.applicationContext)!!


 fun getAll():LiveData<List<Todo>>
 {
  return db.toDoDao().getAll()
 }


 fun insert(todo:Todo)
 {
  CoroutineScope(Dispatchers.IO).launch {
   db.toDoDao().insert(todo)
  }

 }
 fun update(todo:Todo)
 {
  CoroutineScope(Dispatchers.IO).launch {
   db.toDoDao().update(todo)
  }
 }
 fun delete(todo:Todo)
 {
  CoroutineScope(Dispatchers.IO).launch {
   db.toDoDao().delete(todo)
  }
 }
}