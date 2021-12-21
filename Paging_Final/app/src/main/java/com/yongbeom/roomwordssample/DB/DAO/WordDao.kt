package com.yongbeom.roomwordssample.DB.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yongbeom.roomwordssample.DB.Entity.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    /**
    * @Query("SELECT * FROM word_table ORDER BY word ASC"): 오름차순으로 정렬된 단어 목록을 반환하는 쿼리입니다
    * */
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    /**
     * onConflict = OnConflictStrategy.IGNORE: 선택된 onConflict 전략은 이미 목록에 있는 단어와 정확하게 같다면 새 단어를 무시합니다.
     * */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}