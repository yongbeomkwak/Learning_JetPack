package com.yongbeom.workmanagert.Worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.text.SimpleDateFormat
import java.util.*

class DiceRollWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.d("DiceRollWorker", "start")

        val random = Random()
        val diceResult = 1 + random.nextInt(6)
        val time = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(Date()).toString()

        Log.e("DiceRollWorker", "Dice roll result = $diceResult!")
        Log.e("DiceRollWorker", "Dice rolled time = $time!")
        return Result.success(workDataOf(Pair("DiceResult", diceResult), Pair("RolledTime", time)))
    }
}