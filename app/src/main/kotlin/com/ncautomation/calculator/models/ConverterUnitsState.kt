package com.ncautomation.calculator.models

import com.ncautomation.calculator.helpers.converters.Converter

data class ConverterUnitsState(
    val topUnit: Converter.Unit,
    val bottomUnit: Converter.Unit,
)
