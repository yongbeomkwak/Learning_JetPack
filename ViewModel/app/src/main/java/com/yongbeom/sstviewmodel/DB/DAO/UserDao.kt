package com.yongbeom.sstviewmodel.DB.DAO

import androidx.room.*
import com.yongbeom.sstviewmodel.DB.Entities.User
@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM UserTable") // 테이블의 모든 값을 가져와라
    fun getAll(): List<User>

}