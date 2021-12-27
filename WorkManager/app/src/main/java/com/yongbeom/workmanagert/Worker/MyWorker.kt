package com.yongbeom.workmanagert.Worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        /* 처리해야할 작업에 관한 코드들 */
        Log.e("MyWorker", "Hello!")
        return Result.success()
    }
}