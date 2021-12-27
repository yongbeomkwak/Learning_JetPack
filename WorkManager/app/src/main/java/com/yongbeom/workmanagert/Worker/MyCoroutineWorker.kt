package com.yongbeom.workmanagert.Worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class MyCoroutineWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    /**
     * CoroutineWorker는 반드시 doWork()이 suspend fun 으로 선언되어있다.
        또한 Worker에는 public으로 선언되어 있는 onStopped()이 final로 선언되어있어, override 할 수 없다.
     *
     * */
    override suspend fun doWork(): Result {
        /* 처리해야할 작업에 관한 코드들 */
        delay(500L)
        Log.e("MyCoroutineWorker", "Hello!")
        return Result.success()
    }
}