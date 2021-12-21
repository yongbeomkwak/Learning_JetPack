package com.yongbeom.roomwordssample.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yongbeom.roomwordssample.DB.DAO.WordDao
import com.yongbeom.roomwordssample.DB.Entity.Word

@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
// exportSchema는 빌드 경고를 피하기 위해 false로 설정했습니다.
abstract class WordRoomDatabase:RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object{
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}