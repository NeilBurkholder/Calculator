package com.ncautomation.calculator.dialogs

import androidx.appcompat.app.AlertDialog
import com.ncautomation.calculator.R
import com.ncautomation.calculator.activities.SimpleActivity
import com.ncautomation.calculator.adapters.HistoryAdapter
import com.ncautomation.calculator.databinding.DialogHistoryBinding
import com.ncautomation.calculator.extensions.calculatorDB
import com.ncautomation.calculator.helpers.CalculatorImpl
import com.ncautomation.calculator.models.History
import com.ncautomation.commons.extensions.getAlertDialogBuilder
import com.ncautomation.commons.extensions.setupDialogStuff
import com.ncautomation.commons.extensions.toast
import com.ncautomation.commons.helpers.ensureBackgroundThread

class HistoryDialog(activity: SimpleActivity, items: List<History>, calculator: CalculatorImpl) {
    private var dialog: AlertDialog? = null

    init {

        val view = DialogHistoryBinding.inflate(activity.layoutInflater, null, false)

        activity.getAlertDialogBuilder()
            .setPositiveButton(com.ncautomation.commons.R.string.ok, null)
            .setNeutralButton(R.string.clear_history) { _, _ ->
                ensureBackgroundThread {
                    activity.applicationContext.calculatorDB.deleteHistory()
                    activity.toast(R.string.history_cleared)
                }
            }.apply {
                activity.setupDialogStuff(view.root, this, R.string.history) { alertDialog ->
                    dialog = alertDialog
                }
            }

        view.historyList.adapter = HistoryAdapter(activity, items, calculator) {
            dialog?.dismiss()
        }
    }
}
