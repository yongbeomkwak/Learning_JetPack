package com.yongbeom.sstviewmodel.DB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName="테이블 명")
@Entity(tableName = "UserTable")
data class User(
    val name: String,
    val age: String,
    val phone: String
)
{

    //유니크 키 설정 (autoGenerate 옵션 사용)
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

