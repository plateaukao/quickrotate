package info.plateaukao.quickrotate

import android.hardware.display.DisplayManager
import android.provider.Settings
import android.service.quicksettings.TileService
import android.view.Display
import android.view.Surface
import androidx.core.content.getSystemService


class RotateRightService : TileService() {
    override fun onClick() {
        val defaultDisplay =
            getSystemService<DisplayManager>()?.getDisplay(Display.DEFAULT_DISPLAY) ?: return
        when (defaultDisplay.orientation) {
            Surface.ROTATION_0->
                Settings.System.putInt(
                    contentResolver,
                    Settings.System.USER_ROTATION,
                    Surface.ROTATION_90
                )
            else ->
                Settings.System.putInt(
                    contentResolver,
                    Settings.System.USER_ROTATION,
                    Surface.ROTATION_0
                )
        }
    }

}