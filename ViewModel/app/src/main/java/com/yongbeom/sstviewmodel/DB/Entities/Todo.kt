package com.yongbeom.sstviewmodel.DB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TodoTable")
data class Todo(
    val title:String,
    val date:Long,
)
{
    @PrimaryKey(autoGenerate = true)
    var   id:Long=0
}
