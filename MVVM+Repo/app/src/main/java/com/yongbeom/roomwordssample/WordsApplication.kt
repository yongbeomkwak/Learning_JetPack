package com.yongbeom.roomwordssample

import android.app.Application
import com.yongbeom.roomwordssample.DB.WordRepository
import com.yongbeom.roomwordssample.DB.WordRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { WordRoomDatabase.getDatabase(this,applicationScope)}
    val repository by lazy { WordRepository(database.wordDao()) }
}