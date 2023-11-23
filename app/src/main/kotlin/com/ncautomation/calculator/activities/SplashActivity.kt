package com.ncautomation.calculator.activities

import android.content.Intent
import com.ncautomation.commons.activities.BaseSplashActivity

class SplashActivity : BaseSplashActivity() {
    override fun initActivity() {
        startActivity(Intent(this, com.ncautomation.calculator.activities.MainActivity::class.java))
        finish()
    }
    class Orange : BaseSplashActivity() {
        override fun initActivity() {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
