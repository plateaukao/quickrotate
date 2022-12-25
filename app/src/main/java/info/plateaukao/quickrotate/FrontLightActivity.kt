package info.plateaukao.quickrotate

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import com.onyx.android.sdk.api.device.FrontLightController

class FrontLightActivity : Activity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val dialog = AlertDialog.Builder(this)
            .setView(R.layout.front_light_adjust_layout)
            .show()
        dialog.window?.setLayout(300.dp(this), ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        initViews(dialog)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initViews(dialog: AlertDialog) {
        val seekbarTemperature = dialog.findViewById<SeekBar>(R.id.seekbar_light_temperature)
        val seekbarBrightness = dialog.findViewById<SeekBar>(R.id.seekbar_light_brightness)
        val toggleButton = dialog.findViewById<ImageView>(R.id.iv_frontlight)
        val isLightOn = FrontLightController.isColdLightOn(this)
        toggleButton.setImageResource(if (isLightOn) R.drawable.ic_light_on else R.drawable.ic_light_off)
        toggleButton.setOnClickListener {
            if (FrontLightController.isColdLightOn(this)) {
                enableFrontLight(false)
                toggleButton.setImageResource(R.drawable.ic_light_off)
            } else {
                enableFrontLight(true)
                toggleButton.setImageResource(R.drawable.ic_light_on)
            }
        }
        val warmButton = dialog.findViewById<ImageView>(R.id.iv_temperature)
        warmButton.setOnClickListener { seekbarTemperature.progress = 0 }

        seekbarBrightness.max = 500
        seekbarBrightness.min = 0
        seekbarBrightness.progress = FrontLightController.getColdLightConfigValue(this) + FrontLightController.getWarmLightConfigValue(this)

        var offSet = currentOffset
        seekbarTemperature.max = 100
        seekbarTemperature.min = -100
        seekbarTemperature.progress = offSet

        seekbarBrightness.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                FrontLightController.setColdLightDeviceValue(this@FrontLightActivity, progress / 2)
                FrontLightController.setWarmLightDeviceValue(
                    this@FrontLightActivity,
                    progress/2 + offSet
                )
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        seekbarTemperature.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val coldBrightness =
                    FrontLightController.getColdLightConfigValue(this@FrontLightActivity)
                offSet = progress
                FrontLightController.setWarmLightDeviceValue(
                    this@FrontLightActivity,
                    coldBrightness + offSet
                )
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    private val currentOffset = FrontLightController.getWarmLightConfigValue(this) -
            FrontLightController.getColdLightConfigValue(this)

    private fun enableFrontLight(isEnabled: Boolean) {
        if (isEnabled) {
            FrontLightController.openColdLight()
            FrontLightController.openWarmLight()
        } else {
            FrontLightController.closeColdLight()
            FrontLightController.closeWarmLight()
        }
    }
}

fun Int.dp(context: Context): Int {
    val metrics = context.resources.displayMetrics
    return (this * (metrics.densityDpi / 160f)).toInt()
}

