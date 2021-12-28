package com.yongbeom.workmanagert.Worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class DiceRolledTimeWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.d("DiceRolledTimeWorker", "start")
        val rolledTime = inputData.getString("RolledTime")

        Log.e("DiceRolledTimeWorker", "last Rolled Time = $rolledTime!")
        return Result.success(workDataOf(Pair("RolledTime", rolledTime)))
    }
}