package com.yongbeom.sstviewmodel.DB.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yongbeom.sstviewmodel.DB.Entities.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM TodoTable")
    fun getAll():LiveData<List<Todo>>

    @Insert
    fun insert(todo:Todo)

    @Update
    fun update(todo:Todo)

    @Delete
    fun delete(todo:Todo)

}