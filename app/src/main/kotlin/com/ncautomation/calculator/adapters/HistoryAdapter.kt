package com.ncautomation.calculator.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ncautomation.calculator.activities.SimpleActivity
import com.ncautomation.calculator.databinding.HistoryViewBinding
import com.ncautomation.calculator.helpers.CalculatorImpl
import com.ncautomation.calculator.models.History
import com.ncautomation.commons.extensions.copyToClipboard
import com.ncautomation.commons.extensions.getProperTextColor

class HistoryAdapter(val activity: SimpleActivity, val items: List<History>, val calc: CalculatorImpl, val itemClick: () -> Unit) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private var textColor = activity.getProperTextColor()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(HistoryViewBinding.inflate(activity.layoutInflater, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: HistoryViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: History): View {
            itemView.apply {
                binding.itemFormula.text = item.formula
                binding.itemResult.text = item.result
                binding.itemFormula.setTextColor(textColor)
                binding.itemResult.setTextColor(textColor)

                setOnClickListener {
                    calc.addNumberToFormula(item.result)
                    itemClick()
                }

                setOnLongClickListener {
                    activity.baseContext.copyToClipboard(item.result)
                    true
                }
            }

            return itemView
        }
    }
}
