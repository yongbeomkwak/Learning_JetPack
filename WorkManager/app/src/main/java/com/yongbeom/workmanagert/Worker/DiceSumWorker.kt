package com.yongbeom.workmanagert.Worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class DiceSumWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.d("DiceSumWorker", "start")

        val list = inputData.getIntArray("DiceResult")
        var diceSum = 0

        list?.let {
            for (int in list) {
                diceSum += int
            }
        }

        Log.e("DiceSumWorker", "Dice sum = $diceSum!")
        return Result.success(workDataOf(Pair("DiceSum", diceSum)))
    }
}