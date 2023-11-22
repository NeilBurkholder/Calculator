package com.ncautomation.calculator.helpers

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.ncautomation.calculator.extensions.calculatorDB
import com.ncautomation.calculator.models.History
import com.ncautomation.commons.helpers.ensureBackgroundThread

class HistoryHelper(val context: Context) {
    fun getHistory(callback: (calculations: ArrayList<History>) -> Unit) {
        ensureBackgroundThread {
            val notes = context.calculatorDB.getHistory() as ArrayList<History>

            Handler(Looper.getMainLooper()).post {
                callback(notes)
            }
        }
    }

    fun insertOrUpdateHistoryEntry(entry: History) {
        ensureBackgroundThread {
            context.calculatorDB.insertOrUpdate(entry)
        }
    }
}
