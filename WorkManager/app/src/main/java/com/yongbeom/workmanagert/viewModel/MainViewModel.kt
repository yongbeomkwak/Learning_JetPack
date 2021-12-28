package com.yongbeom.workmanagert.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.yongbeom.workmanagert.Worker.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainViewModel:ViewModel() {
    /**
     * OneTimeWorkRequestBuilder()
    enqueue 되는 순간, 단 한 번 work를 바로 수행한다.
    PeriodicWorkRequestBuilder(repeatInterval: Long, repeatIntervalTimeUnit: TimeUnit)
    enqueue 되는 순간 work를 수행하며, repeatInterval 마다 작업을 반복적으로 수행한다.
    간격을 아무리 짧게 설정해도, 15분이내에 작업을 수행하지 않는다.
     *
     * **/
    fun startWorkRequests(workManager: WorkManager) {
        workManager.cancelAllWork()

        viewModelScope.launch {
            OneTimeWorkRequest(workManager)
//            PeriodicWorkRequest(15, workManager)

            OneTimeCoroutineWorkRequest(workManager)
//            PeriodicCoroutineWorkRequest(15, workManager)
        }

        viewModelScope.launch {
            delay(1000L)
            Log.e("-", "-----------------------------------------------------------------------------")
            ArrayCreatingInputMerger(workManager)
            delay(1000L)
            OverwritingInputMerger(workManager)
        }
    }

    fun OneTimeWorkRequest(workManager: WorkManager) {
        /*단발 WorkRequest*/
        val workRequest = OneTimeWorkRequestBuilder<MyWorker>().build()
        workManager.enqueue(workRequest)
    }

    fun PeriodicWorkRequest(period: Long, workManager: WorkManager) {
        /*주기적으로 반복하는 WorkRequest*/
        val workRequest = PeriodicWorkRequestBuilder<MyWorker>(period, TimeUnit.MINUTES).build()
        workManager.enqueue(workRequest)
    }

    fun OneTimeCoroutineWorkRequest(workManager: WorkManager) {
        /*단발 CoroutineWorkRequest*/
        val workRequest = OneTimeWorkRequestBuilder<MyCoroutineWorker>().build()
        workManager.enqueue(workRequest)
    }

    fun PeriodicCoroutineWorkRequest(period: Long, workManager: WorkManager) {
        /*주기적으로 반복하는 CoroutineWorkRequest*/
        val workRequest = PeriodicWorkRequestBuilder<MyCoroutineWorker>(period, TimeUnit.MINUTES).build()
        workManager.enqueue(workRequest)
    }

    fun ArrayCreatingInputMerger(workManager: WorkManager) {
        val diceRollWorker1 = OneTimeWorkRequestBuilder<DiceRollWorker>().build()
        val diceRollWorker2 = OneTimeWorkRequestBuilder<DiceRollWorker>().build()
        val diceRollWorker3 = OneTimeWorkRequestBuilder<DiceRollWorker>().build()

        val diceSumWorker = OneTimeWorkRequestBuilder<DiceSumWorker>()
            .setInputMerger(ArrayCreatingInputMerger::class)
            .build()

        workManager.beginWith(listOf(diceRollWorker1, diceRollWorker2, diceRollWorker3))
            .then(diceSumWorker).enqueue()
    }

    fun OverwritingInputMerger(workManager: WorkManager) {
        val diceRollWorker1 = OneTimeWorkRequestBuilder<DiceRollWorker>().build()
        val diceRollWorker2 = OneTimeWorkRequestBuilder<DiceRollWorker>().build()
        val diceRollWorker3 = OneTimeWorkRequestBuilder<DiceRollWorker>().build()

        val rolledTimeWorker = OneTimeWorkRequestBuilder<DiceRolledTimeWorker>()
            .setInputMerger(OverwritingInputMerger::class)
            .build()

        workManager.beginWith(listOf(diceRollWorker1, diceRollWorker2, diceRollWorker3))
            .then(rolledTimeWorker).enqueue()
    }
}