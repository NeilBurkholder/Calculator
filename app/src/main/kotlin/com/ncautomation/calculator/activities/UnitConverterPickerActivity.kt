package com.ncautomation.calculator.activities

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.ncautomation.calculator.R
import com.ncautomation.calculator.adapters.UnitTypesAdapter
import com.ncautomation.calculator.databinding.ActivityUnitConverterPickerBinding
import com.ncautomation.calculator.extensions.config
import com.ncautomation.calculator.helpers.converters.Converter
import com.ncautomation.commons.extensions.viewBinding
import com.ncautomation.commons.helpers.NavigationIcon
import com.ncautomation.commons.views.AutoGridLayoutManager

class UnitConverterPickerActivity : SimpleActivity() {
    private val binding by viewBinding(ActivityUnitConverterPickerBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        isMaterialActivity = true
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        updateMaterialActivityViews(binding.unitConverterPickerCoordinator, null, useTransparentNavigation = false, useTopSearchMenu = false)
        setupMaterialScrollListener(binding.unitTypesGrid, binding.unitConverterPickerToolbar)

        binding.unitTypesGrid.layoutManager = AutoGridLayoutManager(this, resources.getDimensionPixelSize(R.dimen.unit_type_size))
        binding.unitTypesGrid.adapter = UnitTypesAdapter(this, Converter.ALL) {
            Intent(this, UnitConverterActivity::class.java).apply {
                putExtra(UnitConverterActivity.EXTRA_CONVERTER_ID, it)
                startActivity(this)
            }
        }

        binding.unitConverterPickerToolbar.setTitle(R.string.unit_converter)
    }

    override fun onResume() {
        super.onResume()

        setupToolbar(binding.unitConverterPickerToolbar, NavigationIcon.Arrow)

        if (config.preventPhoneFromSleeping) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    override fun onPause() {
        super.onPause()
        if (config.preventPhoneFromSleeping) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }
}
